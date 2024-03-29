/*******************************************************************************
 * Copyright (c) 2017 INRIA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     INRIA - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.execution.concurrent.ccsljavaxdsml.ui.builder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.commons.eclipse.core.resources.IFileUtils;
import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;
import org.eclipse.gemoc.execution.concurrent.ccsljavaxdsml.api.extensions.languages.MoccmlLanguageAdditionExtensionPoint;
import org.eclipse.gemoc.execution.concurrent.ccsljavaxdsml.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtensionPoint;
import org.eclipse.gemoc.xdsmlframework.ide.ui.builder.pde.PluginXMLHelper;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.TypeNameMatch;
import org.eclipse.jdt.core.search.TypeNameMatchRequestor;
import org.eclipse.jdt.internal.core.SourceField;
import org.eclipse.jdt.launching.JavaRuntime;
import org.jdom2.Element;
import org.osgi.framework.BundleException;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.rtd.api.Containment;
import org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.rtd.api.Containment.ContainmentStrategy;

//import fr.inria.diverse.melange.metamodel.melange.Language;
//import fr.inria.diverse.melange.metamodel.melange.ModelTypingSpace;

public class MoccmlLanguageProjectBuilder extends IncrementalProjectBuilder {

	private Set<String> setAspectsWithRTDs = null;
	HashMap<String, String> mapAspectizedClass = new HashMap<String, String>(); // key the aspectClass name, value the aspectized class 
	Multimap<String, SourceField> mapAspectProperties = null;
	Multimap<String, Field> mapAspectFieldProperties = null;
	HashMap<SourceField, Containment.ContainmentStrategy> mapFieldContainmentStrategy = null;

	public MoccmlLanguageProjectBuilder() {
		return;
	}

	class LanguageProjectDeltaVisitor implements IResourceDeltaVisitor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse
		 * .core.resources.IResourceDelta)
		 */
		@Override
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				updateProjectPluginConfiguration(resource);
				checkConsistency(resource);
				break;
			case IResourceDelta.REMOVED:
				updateProjectPluginConfiguration(resource);
				checkConsistency(resource);
				break;
			case IResourceDelta.CHANGED:
				updateProjectPluginConfiguration(resource);
				checkConsistency(resource);
				break;
			}
			// return true to continue visiting children.
			return true;
		}

	}

	class LanguageProjectResourceVisitor implements IResourceVisitor {

		@Override
		public boolean visit(IResource resource) {
			updateProjectPluginConfiguration(resource);
			checkConsistency(resource);
			// return true to continue visiting children.
			return true;
		}

	}

	public static final String BUILDER_ID = Activator.PLUGIN_ID + ".MoccmlLanguageProjectBuilder";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int,
	 * java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	public void checkConsistency(IResource resource) {
		// TODO DVK, check consistency of plugin.xml according to existence of
		// projects referenced in the xdsml
	}

	/**
	 * Update plugin.xml according to the model
	 * 
	 * @param resource
	 */
	private void updateProjectPluginConfiguration(IResource resource) {
		if (resource instanceof IFile && resource.getFileExtension().equals("dsl")) {
			IFile file = (IFile) resource;
			IProject project = file.getProject();
			// try {
			if (file.exists()) {
				// Load .dsl file
				URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
				ResourceSet rs = new ResourceSetImpl();
				Resource res = rs.getResource(uri, true);

				Dsl root = (Dsl) res.getContents().get(0);
				String packageName = root.getName();

				ManifestChanger manifestChanger = new ManifestChanger(project);

				// Browse declared Languages
				String languageName = "null";
				String eclFileName = "null";

				for (Entry anEntry : root.getEntries()) {
					if (anEntry.getKey().compareTo("name") == 0) {
						languageName = anEntry.getValue();
						// update entry in plugin.xml
						try {
							setPluginLanguageNameAndFilePath(project, file, languageName);
							updateCodeExecutorClass(project, languageName, manifestChanger);
							updateModelLoaderClass(project, languageName, null);
							updateSolverClass(project, languageName, null);
						} catch (BundleException | IOException | CoreException e) {
							Activator.error(e.getMessage(), e);
						}
					}

					if (anEntry.getKey().compareTo("ecl") == 0) {
						eclFileName = anEntry.getValue();
						updateQVTO(project, languageName, eclFileName, null);
					}

					if (anEntry.getKey().compareTo("k3") == 0) {
						try {
							String k3AspectListString = anEntry.getValue();
							populateK3AspectMaps(k3AspectListString);
							createLanguageSpecificDSAHelper(k3AspectListString, project, languageName, manifestChanger);
							createLanguageSpecificModelStateHelper(k3AspectListString, project, languageName,
									manifestChanger);
						} catch (ClassNotFoundException | CoreException | BundleException | IOException e) {
							Activator.error(e.getMessage(), e);
						}
					}

				}

				try {
					manifestChanger.addPluginDependency(
							org.eclipse.gemoc.executionframework.extensions.sirius.Activator.PLUGIN_ID);
					manifestChanger.addPluginDependency(
							"org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.rtd.modelstate");
					manifestChanger.commit();
				} catch (BundleException | IOException | CoreException e) {
					Activator.error(e.getMessage(), e);
				}
			}
		}
	}

	private void createLanguageSpecificModelStateHelper(String value, IProject project, String fullLanguageName,
			ManifestChanger manifestChanger) {
		// create the java class
		int lastDot = fullLanguageName.lastIndexOf(".");
		if (lastDot == -1)
			lastDot = 0;
		String languageName = fullLanguageName.substring(lastDot + 1);
		String languageToUpperFirst = getLanguageNameWithFirstUpper(languageName);
		String packageName = getPackageName(languageName);
		String folderName = getFolderName(languageName);

		String fileContent = BuilderTemplates.MODEL_STATE_CLASS_TEMPLATE;
		fileContent = fileContent.replaceAll(Pattern.quote("${package.name}"), packageName);
		fileContent = fileContent.replaceAll(Pattern.quote("${language.name.toupperfirst}"), languageToUpperFirst);

		StringBuilder sbContent = new StringBuilder();
		StringBuilder sbExtraImport = new StringBuilder();

		sbContent.append("\n\tpublic K3ModelState getK3StateSpaceModelState(EObject model) {\n"
				+ "		return getK3ModelState(model, false);\n"
				+ "	}\n"
				+ "	\n\tpublic K3ModelState getK3ModelState(EObject model) {\n"
				+ "		return getK3ModelState(model, true);\n"
				+ "	}\n"
				+ "		\n"
				+ "	public K3ModelState getK3ModelState(EObject model, boolean allRTDs) {\n"
				+ "		K3ModelState res = theFactory.createK3ModelState();\n" + "\n"
				+ "		// consider indirectly referenced models (ugly and probably not efficient)\n"
				+ "		ArrayList<EObject> allElements = new ArrayList<EObject>();\n"
				+ "		model.eAllContents().forEachRemaining(x -> allElements.add(x));\n"
				+ "		Map<EObject, Collection<Setting>> f = EcoreUtil.CrossReferencer.find(allElements);\n"
				+ "		HashSet<Resource> consideredResources = new HashSet<Resource>();\n"
				+ "		consideredResources.add(model.eResource());\n"
				+ "		f.keySet().forEach(eo -> consideredResources.add(eo.eResource()));\n"
				+ "		\n"
				+ "		for(Resource resource : consideredResources) {\n"
				+ "			TreeIterator<EObject> allContentIt = resource.getAllContents();\n"
				+ "			while (allContentIt.hasNext()) {\n"
				+ "				EObject elem = allContentIt.next();\n" + "\n");

		sbContent.append("				Class<?> clazz =null;\n");
		for (String aspectName : setAspectsWithRTDs) {
			sbContent.append("				clazz = K3DslHelper.getTarget(" + aspectName + ".class);\n"
					+ "				if (clazz.isInstance(elem)) {\n"
					+ "					ElementState elemState = theFactory.createElementState();\n"
					+ "					elemState.setModelElement(elem);\n"
					+ "					res.getOwnedElementstates().add(elemState);\n"
					+ "					Object propertyValue = null;\n");
			int i = 0;
			for (SourceField property : mapAspectProperties.get(aspectName)) {
				String indent = "";
				if (property.getAnnotation("NotInStateSpace") .exists()) {
					sbContent.append("\t\t\t\t\tif (allRTDs) {  //property not in state space:"+ property.getElementName()+"\n");
					indent = "\t";
				}
				IAnnotation annotation = property.getAnnotation("Containment");
				if(annotation != null) {
					sbContent.append("					// Annotation "+mapFieldContainmentStrategy.get(property)+"\n");
				}
				sbContent.append(indent+ "					propertyValue = " + languageToUpperFirst + "RTDAccessor.saveProperty_" + property.getElementName() + "(("+ mapAspectizedClass.get(aspectName)+")elem);\n");

				sbContent.append(indent+ "					AttributeNameToValue n2v" + i + " = new AttributeNameToValue(\"" + property.getElementName() + "\", propertyValue);\n");
				
				sbContent.append(indent + "					elemState.getSavedRTDs().add(n2v" + i + ");\n");
				i++;
				if (property.getAnnotation("NotInStateSpace") .exists()) {
					sbContent.append("\t\t\t\t\t}\n");
				}
			}
			sbContent.append("				}\n");
		}
		sbContent.append("\t\t\t}\n\t\t}\n\t\treturn res;\n\t}\n");
		
		fileContent = fileContent.replaceAll(Pattern.quote("${saveAndRestoreMethod}"), sbContent.toString());
		fileContent = fileContent.replaceAll(Pattern.quote("${extraImports}"), sbExtraImport.toString());

		IFile file = project.getFile(Activator.EXTENSION_GENERATED_CLASS_FOLDER_NAME + folderName + "/"
				+ languageToUpperFirst + Activator.MODEL_STATE_CLASS_NAMEPART + ".java");
		writeFile(file, fileContent);

	}

	/**
	 * analyze K3 aspects in order to fill maps for future processing
	 * Maps filed by this method:
	 * <ul>
	 * <li>setAspectsWithRTDs
	 * <li>mapAspectProperties
	 * <li>mapAspectizedClass
	 * </ul>
	 * @param allAspects
	 * @throws JavaModelException 
	 */
	@SuppressWarnings("restriction")
	protected void populateK3AspectMaps(String allAspects) throws CoreException, BundleException, IOException {
		setAspectsWithRTDs = new HashSet<String>();
		mapAspectProperties = ArrayListMultimap.create();
		mapAspectFieldProperties  = ArrayListMultimap.create();
		mapAspectizedClass = new HashMap<String, String>();
		mapFieldContainmentStrategy =  new HashMap<SourceField, Containment.ContainmentStrategy>();
		
		
		for (String aspectClassName : allAspects.trim().split(",")) {
			
			int dot = aspectClassName.lastIndexOf('.');
			String aspectPropertiesClassName = aspectClassName + aspectClassName.substring(dot + 1) + "Properties";
			char[][] qualifications;
			String simpleName;
			if (dot != -1) {
				qualifications = new char[][] { aspectPropertiesClassName.substring(0, dot).toCharArray() };
				simpleName = aspectPropertiesClassName.substring(dot + 1);
			} else {
				qualifications = null;
				simpleName = aspectPropertiesClassName;
			}
			char[][] typeNames = new char[][] { simpleName.toCharArray() };

			IType aspectIType = findAnyTypeInWorkspace(qualifications, typeNames);
			if (aspectIType == null) {
				System.err.println("type \"" + simpleName + "\" not found");
				continue;
			}

			IJavaProject aspectProject = aspectIType.getJavaProject();
			String[] classPathEntries = JavaRuntime.computeDefaultRuntimeClassPath(aspectProject);

			List<URL> urlList = new ArrayList<URL>();
			for (int i = 0; i < classPathEntries.length; i++) {
				String entry = classPathEntries[i];
				IPath path = new Path(entry);
				URL url = path.toFile().toURI().toURL();
				urlList.add(url);
			}
			try {
				// TODO optimise classloader use
				ClassLoader parentClassLoader = aspectProject.getClass().getClassLoader();
				URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
				URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
				Class<?> aspectClass = classLoader.loadClass(aspectIType.getFullyQualifiedName());
	
				String aspectizedClassName;
				try {
					aspectizedClassName = getAspectizedClassCanonicalName(classLoader, aspectClassName);
					// store aspect/aspectizedClass map for future use
					mapAspectizedClass.put(aspectClassName, aspectizedClassName);
					IJavaElement[] allChildren = aspectIType.getChildren();
					for (int i = 0; i < allChildren.length; i++) {
						IJavaElement javaElem = allChildren[i];
						if (javaElem instanceof SourceField) {
							setAspectsWithRTDs.add(aspectClassName);
							SourceField sf = (SourceField) javaElem;
							mapAspectProperties.put(aspectClassName, sf);
							try {
								
								Field f = aspectClass.getField(sf.getElementName());
								mapAspectFieldProperties.put(aspectClassName, f);
								Optional<Annotation> annotation = Arrays.asList(f.getAnnotations()).stream()
										.filter(a ->  a.annotationType().getCanonicalName().equals(org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.rtd.api.Containment.class.getCanonicalName())).findFirst();
								if(annotation.isPresent()) {
									// pb of classpath, recreate containment information via reflexivity
									Method m = annotation.get().annotationType().getMethod("value");
									Object o = m.invoke(annotation.get());
									switch (o.toString()) {
									case "CONTAINER":
										mapFieldContainmentStrategy.put(sf, ContainmentStrategy.CONTAINER);
										break;
									case "REFERENCE" :	
										mapFieldContainmentStrategy.put(sf, ContainmentStrategy.REFERENCE);
										break;
									case "MAP_KEYCONTAINMENT_VALUEREFERENCE" :	
										mapFieldContainmentStrategy.put(sf, ContainmentStrategy.MAP_KEYCONTAINMENT_VALUEREFERENCE);
										break;
									case "MAP_KEYREFERENCE_VALUECONTAINMENT" :	
										mapFieldContainmentStrategy.put(sf, ContainmentStrategy.MAP_KEYREFERENCE_VALUECONTAINMENT);
										break;
									default:
										mapFieldContainmentStrategy.put(sf, ContainmentStrategy.CONTAINER);
										break;
									}
								} else {
									mapFieldContainmentStrategy.put(sf, ContainmentStrategy.CONTAINER);
								}
								
							} catch (NoSuchFieldException e) {
								Activator.error("Cannot generate getter and setter for aspect property "+ aspectIType.getFullyQualifiedName() + " " +sf.getElementName() , e);
							}
						}
					}
			
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e1) {
					Activator.error("Cannot generate getter and setter for aspect "+ aspectIType.getFullyQualifiedName() , e1);
				}
				// warning after this point the access to the field and annotations will not work anymore
				classLoader.close();
			}
			catch (ClassNotFoundException e2) {
				Activator.error("Cannot generate getter and setter for aspect "+ aspectIType.getFullyQualifiedName() , e2);
				
			}
		}
	}
	
	
	@SuppressWarnings("restriction")
	protected void createLanguageSpecificDSAHelper(String allAspects, IProject project, String fullLanguageName,
			ManifestChanger manifestChanger)
			throws CoreException, BundleException, IOException, ClassNotFoundException {
		// create the java class
		int lastDot = fullLanguageName.lastIndexOf(".");
		if (lastDot == -1)
			lastDot = 0;
		String languageName = fullLanguageName.substring(lastDot + 1);
		String languageToUpperFirst = getLanguageNameWithFirstUpper(languageName);
		String packageName = getPackageName(languageName);
		String folderName = getFolderName(languageName);

		String fileContent = BuilderTemplates.RTD_ACCESSOR_CLASS_TEMPLATE;
		fileContent = fileContent.replaceAll(Pattern.quote("${package.name}"), packageName);
		fileContent = fileContent.replaceAll(Pattern.quote("${language.name.toupperfirst}"), languageToUpperFirst);

		StringBuilder sbContent = new StringBuilder();
		StringBuilder sbExtraImport = new StringBuilder();

		for (String aspectName : setAspectsWithRTDs) {
			String aspectizedClassName = mapAspectizedClass.get(aspectName);
			for(Field property : mapAspectFieldProperties.get(aspectName)) {
				String fieldName = property.getName();
				String fieldTypeName = property.getType().getTypeName();
				
				sbContent.append("  public static " + fieldTypeName + " get" + fieldName
				+ "(" + aspectizedClassName + " eObject) {\n"
				+ "     "+fieldTypeName+" theProperty = ("+fieldTypeName+")getAspectProperty(eObject, \"" + fullLanguageName + "\", \""
				+ aspectName + "\", \"" + fieldName + "\");\n"
				+ "		return theProperty;\n}\n");
								
				// save/restore property by reference or copy
				String savePropertyStrategy = "";
				Optional<SourceField> propertySourceField = mapAspectProperties.get(aspectName).stream().filter(p -> p.getElementName().equals(fieldName)).findFirst();
				ContainmentStrategy propertyContainmentStragety = propertySourceField.isEmpty() ? ContainmentStrategy.CONTAINER : mapFieldContainmentStrategy.get(propertySourceField.get());
				switch (propertyContainmentStragety) {
				case REFERENCE:
					// nothing to do this is a reference
					savePropertyStrategy = "// Reference property\n";
					break;
				case MAP_KEYCONTAINMENT_VALUEREFERENCE:
					Activator.error("MAP_KEYCONTAINMENT_VALUEREFERENCE not implemented, probable issue with property "+ fieldName , new Exception("MAP_KEYCONTAINMENT_VALUEREFERENCE not implemented, probable issue with property "+ fieldName));
					break;
				case MAP_KEYREFERENCE_VALUECONTAINMENT:
					Activator.error("MAP_KEYREFERENCE_VALUECONTAINMENT not implemented, probable issue with property "+ fieldName , new Exception("MAP_KEYREFERENCE_VALUECONTAINMENT not implemented, probable issue with property "+ fieldName));
					break;
				default: // CONTAINER
					savePropertyStrategy ="propertyValue = propertyValue == null ? null : ("+fieldTypeName+")Copier.clone(propertyValue);\n";
					break;
				}
				sbContent.append("  public static " + fieldTypeName + " saveProperty_" + fieldName
				+ "(" + aspectizedClassName + " eObject) {\n"
				+ "		"+fieldTypeName+" propertyValue = ("+fieldTypeName+")getAspectProperty(eObject, \"" + fullLanguageName + "\", \""+ aspectName + "\", \"" + fieldName + "\");\n"
				+ "		" + savePropertyStrategy	
				+ "		return propertyValue;\n}\n");
				
				sbContent.append("	public static boolean set" + fieldName + "(" + aspectizedClassName + " eObject, "
				+ fieldTypeName + " newValue) {\n" 
				+ "		return setAspectProperty(eObject, \""+ fullLanguageName + "\", \"" + aspectName + "\", \"" + fieldName+ "\", newValue);\n	}\n");
				
				sbContent.append("	public static boolean restoreProperty_" + fieldName + "(" + aspectizedClassName + " eObject, "
				+ fieldTypeName + " newValue) {\n" 
				+ "		"+fieldTypeName+" propertyValue = newValue;\n"
				+ "		"+savePropertyStrategy
				+ "		return setAspectProperty(eObject, \""+ fullLanguageName + "\", \"" + aspectName + "\", \"" + fieldName+ "\", propertyValue);\n	}\n");
			}
		}

		fileContent = fileContent.replaceAll(Pattern.quote("${allGettersAndSetters}"), sbContent.toString());

		fileContent = fileContent.replaceAll(Pattern.quote("${extraImports}"), sbExtraImport.toString());

		IFile file = project.getFile(Activator.EXTENSION_GENERATED_CLASS_FOLDER_NAME + folderName + "/"
				+ languageToUpperFirst + Activator.RTD_ACCESSOR_CLASS_NAMEPART + ".java");
		writeFile(file, fileContent);

		IFile AspectsFile = project.getFile(Activator.EXTENSION_GENERATED_CLASS_FOLDER_NAME + folderName + "/"
				+ languageToUpperFirst + Activator.RTD_ASPECTS_CLASS_NAMEPART + ".list");

		StringBuilder sbAspects = new StringBuilder();
		setAspectsWithRTDs.stream().forEachOrdered(s -> sbAspects.append(s + "\n"));
		writeFile(AspectsFile, sbAspects.toString());

		// add the rtd accessor package to exported packages
		manifestChanger.addExportPackage(packageName);
	}

	private static IType findAnyTypeInWorkspace(char[][] qualifications, char[][] typeNames) throws JavaModelException {
		class ResultException extends RuntimeException {
			private static final long serialVersionUID = 1L;
			private final IType fType;

			public ResultException(IType type) {
				fType = type;
			}
		}
		TypeNameMatchRequestor requestor = new TypeNameMatchRequestor() {
			@Override
			public void acceptTypeNameMatch(TypeNameMatch match) {
				throw new ResultException(match.getType());
			}
		};
		try {
			new SearchEngine().searchAllTypeNames(qualifications, typeNames, SearchEngine.createWorkspaceScope(),
					requestor, IJavaSearchConstants.WAIT_UNTIL_READY_TO_SEARCH, null);
		} catch (ResultException e) {
			return e.fType;
		}
		return null;
	}

	/**
	 * create or replace existing CodeExecutorClass by an implementation that is
	 * able to execute method from the concrete DSA
	 * 
	 * @param project
	 * @param ld
	 * @param manifestChanger
	 * @throws CoreException
	 * @throws IOException
	 * @throws BundleException
	 */
	protected void updateCodeExecutorClass(IProject project, String fullLanguageName, ManifestChanger manifestChanger)
			throws BundleException, IOException, CoreException {
		// create the java class
		int lastDot = fullLanguageName.lastIndexOf(".");
		if (lastDot == -1)
			lastDot = 0;
		String languageName = fullLanguageName.substring(lastDot + 1);
		String languageToUpperFirst = getLanguageNameWithFirstUpper(languageName);
		String packageName = getPackageName(languageName);
		String folderName = getFolderName(languageName);

		String fileContent = BuilderTemplates.CODEEXECUTOR_CLASS_TEMPLATE;
		fileContent = fileContent.replaceAll(Pattern.quote("${package.name}"), packageName);
		fileContent = fileContent.replaceAll(Pattern.quote("${language.name.toupperfirst}"), languageToUpperFirst);

		StringBuilder sbContent = new StringBuilder();
		StringBuilder sbImplementContent = new StringBuilder();
		StringBuilder sbAdditionalOperations = new StringBuilder();

		sbContent.append("// add Melange or K3 DSA specific executors\n");
		sbContent.append(
				"\t\taddExecutor(new org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.dsa.impl.K3DSLCodeExecutor(this,\n");
		sbContent.append("\t\t\t\"" + fullLanguageName + "\"));\n");

		sbImplementContent.append(
				"\n\t\timplements org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.dsa.api.IK3DSAExecutorClassLoader ");

		sbAdditionalOperations.append("@Override\n"
				+ "	public Class<?> getClassForName(String className) throws ClassNotFoundException {\n"
				+ "		return Class.forName(className);\n" + "	}\n" + "	@Override\n"
				+ "	public java.io.InputStream getResourceAsStream(String resourceName) {\n"
				+ "		//this.getClass().getResourceAsStream(resourceName);\n"
				+ "		return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);\n"
				+ "	}");

		sbContent.append("\t\t// fall back executor : search classic java method\n");
		sbContent.append(
				"\t\taddExecutor(new org.eclipse.gemoc.execution.concurrent.ccsljavaengine.dsa.executors.JavaCodeExecutor());");
		fileContent = fileContent.replaceAll(Pattern.quote("${constructor.content}"), sbContent.toString());

		fileContent = fileContent.replaceAll(Pattern.quote("${implements.content}"), sbImplementContent.toString());

		fileContent = fileContent.replaceAll(Pattern.quote("${additional.operations}"),
				sbAdditionalOperations.toString());

		IFile file = project.getFile(Activator.EXTENSION_GENERATED_CLASS_FOLDER_NAME + folderName + "/"
				+ languageToUpperFirst + Activator.CODEEXECUTOR_CLASS_NAMEPART + ".java");

		writeFile(file, fileContent);
		// ResourceUtil.writeFile(file, fileContent);

		// update plugin.xml with the generated codeexecutor
		setPluginCodeExecutorValue(project,
				packageName + "." + languageToUpperFirst + Activator.CODEEXECUTOR_CLASS_NAMEPART);

		// add the codeexutor package to exported packages
		manifestChanger.addExportPackage(packageName);

	}

	protected void setPluginCodeExecutorValue(IProject project, String value) {
		// update plugin.xml
		IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		Element gemocLanguageAdditionExtensionPoint = helper.getOrCreateExtensionPoint(
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT);
		helper.updateAttributeInExtensionPoint(gemocLanguageAdditionExtensionPoint,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_ADDITION_DEF,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_XDSML_DEF_CODEEXECUTOR_ATT,
				value);
		helper.saveDocument(pluginfile);
	}

	protected void setPluginLanguageNameAndFilePath(IProject project, final IFile dslFile, String languageName) {
		IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);

		// update language definition
		Element gemocLangExtensionPoint = helper
				.getOrCreateExtensionPoint(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT);
		helper.updateXDSMLDefinitionInExtensionPoint(gemocLangExtensionPoint, languageName);
		helper.updateXDSMLDefinitionAttributeInExtensionPoint(gemocLangExtensionPoint,
				LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_XDSML_FILE_PATH_ATT,
				dslFile.getFullPath().toString());

		// update moccml addition
		Element moccmlAdditionExtensionPoint = helper.getOrCreateExtensionPoint(
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT);
		helper.updateAttributeInExtensionPoint(moccmlAdditionExtensionPoint,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_ADDITION_DEF,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_NAME_ATT, languageName);
		helper.updateAttributeInExtensionPoint(moccmlAdditionExtensionPoint,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_ADDITION_DEF,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_REFERENCEDX_XDSML_ATT,
				languageName);

		helper.saveDocument(pluginfile);
	}

	protected void updateDependenciesWithProject(ManifestChanger connection, String projectName)
			throws BundleException, IOException, CoreException {
		connection.addPluginDependency(projectName);
		// TODO find a way to remove possible old domain model dependencies
	}

	
	protected void updateMapAspectizedClass(URLClassLoader classLoader, String aspectClassName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		
		Class<?> aspectClass = classLoader.loadClass(aspectClassName);
		// we are working in a separate classloader so a simple
		// Class<?> aspectizedClass = aspectClass.getAnnotation(fr.inria.diverse.k3.al.annotationprocessor.Aspect.class).className();
		// will not work
		// use reflexivity instead in order to get the values
		for( Annotation annot : aspectClass.getAnnotations()) {
			if(annot.annotationType().getCanonicalName().equals("fr.inria.diverse.k3.al.annotationprocessor.Aspect")) {	
				Method methodClassName = annot.getClass().getMethod("className");
				Object o = methodClassName.invoke(annot);
				Method methodGetCanonicalName = o.getClass().getMethod("getCanonicalName");
				String aspectizedClassName = (String)methodGetCanonicalName.invoke(o);
				mapAspectizedClass.put(aspectClassName, aspectizedClassName);
			}
		}
	}
	
	/**
	 * returns the name of the aspectized class from an aspect class
	 * as this builder isn't working in the same class loader as the searched aspect/aspectized class this methods requires a dedicated class loader
	 * @param classLoader used to look for the classes
	 * @param aspectClassName
	 * @return
	 */
	public String getAspectizedClassCanonicalName(URLClassLoader classLoader, String aspectClassName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		Class<?> aspectClass = classLoader.loadClass(aspectClassName);
		// we are working in a separate classloader so a simple
		// Class<?> aspectizedClass = aspectClass.getAnnotation(fr.inria.diverse.k3.al.annotationprocessor.Aspect.class).className();
		// will not work
		// use reflexivity instead in order to get the values
		for( Annotation annot : aspectClass.getAnnotations()) {
			if(annot.annotationType().getCanonicalName().equals("fr.inria.diverse.k3.al.annotationprocessor.Aspect")) {	
				Method methodClassName = annot.getClass().getMethod("className");
				Object o = methodClassName.invoke(annot);
				Method methodGetCanonicalName = o.getClass().getMethod("getCanonicalName");
				String aspectizedClassName = (String)methodGetCanonicalName.invoke(o);
				return aspectizedClassName;
			}
		}
		return null;
	}
	
	// protected void updateDependenciesWithDSAProject(ManifestChanger
	// connection, DSAProject dsaPoject) throws BundleException, IOException,
	// CoreException {
	// updateDependenciesWithProject(connection, dsaPoject.getProjectName());
	// if (dsaPoject.getCodeExecutorClass() == null ||
	// dsaPoject.getCodeExecutorClass().isEmpty()) {
	// // a k3 code executor has been generated so add the required dependency
	// connection.addPluginDependency(org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.Activator.PLUGIN_ID);
	// }
	// }
	// protected void updateDependenciesWithDSEProject(ManifestChanger
	// connection, DSEProject dsePoject) throws BundleException, IOException,
	// CoreException {
	// String dseProjectName = dsePoject.getProjectName();
	// if(dseProjectName != null && !dseProjectName.isEmpty()){
	// updateDependenciesWithProject(connection, dsePoject.getProjectName());
	// String solverClassName = dsePoject.getSolverClass();
	// if(solverClassName == null || solverClassName.isEmpty()){
	// connection.addPluginDependency(org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.timesquare.Activator.PLUGIN_ID);
	// }
	// }
	// }

	/**
	 * create or replace existing ModelLoaderClass by an implementation that is
	 * able to load models of the domain
	 * 
	 * @param project
	 * @param ld
	 */
	protected void updateModelLoaderClass(IProject project, String languageName, String modelLoaderClass) {
		// update plugin.xml
		IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);

		Element gemocExtensionPoint = helper
				.getOrCreateExtensionPoint(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT);
		helper.updateXDSMLDefinitionAttributeInExtensionPoint(gemocExtensionPoint,
				LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_LOADMODEL_ATT,
				modelLoaderClass != null ? modelLoaderClass
						: "org.eclipse.gemoc.executionframework.extensions.sirius.modelloader.DefaultModelLoader");
		helper.saveDocument(pluginfile);

	}

	public static String getLanguageNameWithFirstUpper(String languageDefinitionName) {
		return languageDefinitionName.substring(0, 1).toUpperCase() + languageDefinitionName.substring(1);
	}

	public static String getPackageName(String languageDefinitionName) {
		return languageDefinitionName.toLowerCase() + ".xdsml.api.impl";
	}

	public static String getFolderName(String languageDefinitionName) {
		return getPackageName(languageDefinitionName).replaceAll("\\.", "/");
	}

	public static void writeFile(IFile file, String fileContent) {
		try {
			IFileUtils.writeInFile(file, fileContent, new NullProgressMonitor());
		} catch (CoreException | IOException e) {
			Activator.error(e.getMessage(), e);
		}
	}

	/**
	 * create or replace existing CodeExecutorClass by an implementation that is
	 * able to execute method from the concrete DSA
	 * 
	 * @param project
	 * @param ld
	 */
	protected void updateSolverClass(IProject project, String languageName, String solverClassName) {
		String computedSolverClassName = "";
		if (solverClassName == null || solverClassName.isEmpty()) {
			computedSolverClassName = "org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.timesquare.moc.impl.CcslSolver";
		} else {
			computedSolverClassName = solverClassName;
		}
		// update plugin.xml
		IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);

		// update moccml addition
		Element moccmlAdditionExtensionPoint = helper.getOrCreateExtensionPoint(
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT);
		helper.updateAttributeInExtensionPoint(moccmlAdditionExtensionPoint,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_ADDITION_DEF,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_XDSML_DEF_SOLVER_ATT,
				computedSolverClassName);

		helper.saveDocument(pluginfile);
	}

	protected void updateQVTO(final IProject project, String languageName, String eclFileLocationUri,
			final String qvtoFileLocationUri) {
		String computedQVTOLocationURI = "";
		if (qvtoFileLocationUri == null || qvtoFileLocationUri.isEmpty()) {

			if (eclFileLocationUri.startsWith("/")) {
				eclFileLocationUri = eclFileLocationUri.substring(1);
			}

			int endProjectIndex = eclFileLocationUri.indexOf("/");
			if (endProjectIndex != -1) {
				String eclProject = eclFileLocationUri.substring(0, endProjectIndex);

				// search the relevant qvto in the dse project
				IProject dseProject = ResourcesPlugin.getWorkspace().getRoot().getProject(eclProject);
				IFolder qvtoFolder = dseProject.getFolder("qvto-gen/modeling");
				if (qvtoFolder != null) {
					FileFinderVisitor fileFinder = new FileFinderVisitor("qvto");
					try {
						qvtoFolder.accept(fileFinder);
						IFile qvtoFile = fileFinder.getFile();
						if (qvtoFile != null) {
							computedQVTOLocationURI = qvtoFile.getFullPath().toString();
						}
					} catch (CoreException e) {
					}
				}
			}
		} else {
			computedQVTOLocationURI = qvtoFileLocationUri;
		}

		// update plugin.xml
		IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		// update moccml addition
		Element moccmlAdditionExtensionPoint = helper.getOrCreateExtensionPoint(
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT);
		helper.updateAttributeInExtensionPoint(moccmlAdditionExtensionPoint,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_ADDITION_DEF,
				MoccmlLanguageAdditionExtensionPoint.MOCCML_LANGUAGE_ADDITION_EXTENSION_POINT_XDSML_DEF_TO_CCSL_QVTO_FILE_PATH_ATT,
				computedQVTOLocationURI);
		helper.saveDocument(pluginfile);
	}

	protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
		try {
			// need to check if build is realy required because it seems to be
			// called on each eclipse startup ? more debug analysis required ...
			getProject().accept(new LanguageProjectResourceVisitor());
		} catch (CoreException e) {
		}
	}

	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new LanguageProjectDeltaVisitor());
	}

}
