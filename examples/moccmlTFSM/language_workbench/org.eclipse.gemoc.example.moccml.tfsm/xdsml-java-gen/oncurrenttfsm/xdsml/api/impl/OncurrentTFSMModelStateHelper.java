/* GENERATED FILE, do not modify manually                                                    *
 * If you need to modify it, copy it first */
package oncurrenttfsm.xdsml.api.impl;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.rtd.modelstate.k3ModelState.ElementState;
import org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.rtd.modelstate.k3ModelState.K3ModelState;
import org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.rtd.modelstate.k3ModelState.K3ModelStateFactory;
import org.eclipse.gemoc.execution.concurrent.ccsljavaengine.extensions.k3.dsa.helper.IK3ModelStateHelper;import org.eclipse.gemoc.executionframework.engine.commons.K3DslHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OncurrentTFSMModelStateHelper implements IK3ModelStateHelper{
	private static class AttributeNameToValue implements Serializable{

		private static final long serialVersionUID = 0;		String name;
		Object value;
		public AttributeNameToValue(String n, Object v) {
			name = n;
			value = v;
		}


		@Override
		public boolean equals(Object obj) {
			if (! (obj instanceof AttributeNameToValue)) {
				return false;
			}
			AttributeNameToValue a2n = (AttributeNameToValue)obj;
			if (this.name.compareTo(a2n.name) != 0) {
				return false;
			}
			if (this.value == null) {
				return a2n.value == null;
			}
			if (!this.value.equals(a2n.value)) {
				return false;
			}
			return true;
		}	}
		K3ModelStateFactory theFactory = K3ModelStateFactory.eINSTANCE; 

	public K3ModelState getK3StateSpaceModelState(EObject model) {
		return getK3ModelState(model, false);
	}
	

	public K3ModelState getK3ModelState(EObject model) {
		return getK3ModelState(model, true);
	}
		
	public K3ModelState getK3ModelState(EObject model, boolean allRTDs) {
		K3ModelState res = theFactory.createK3ModelState();

		Class<?> clazz =null;
		clazz = K3DslHelper.getTarget(org.eclipse.gemoc.example.moccml.tfsm.k3dsa.aspect.IntegerVariableAspect.class);
		if (clazz.isInstance(model)) {
			ElementState elemState = theFactory.createElementState();
			elemState.setModelElement(model);
			res.getOwnedElementstates().add(elemState);
				AttributeNameToValue n2v0 = new AttributeNameToValue("currentValue", OncurrentTFSMRTDAccessor.getcurrentValue(model));
				elemState.getSavedRTDs().add(n2v0);
		}
		clazz = K3DslHelper.getTarget(org.eclipse.gemoc.example.moccml.tfsm.k3dsa.aspect.BooleanVariableAspect.class);
		if (clazz.isInstance(model)) {
			ElementState elemState = theFactory.createElementState();
			elemState.setModelElement(model);
			res.getOwnedElementstates().add(elemState);
				AttributeNameToValue n2v0 = new AttributeNameToValue("currentValue", OncurrentTFSMRTDAccessor.getcurrentValue(model));
				elemState.getSavedRTDs().add(n2v0);
		}
		clazz = K3DslHelper.getTarget(org.eclipse.gemoc.example.moccml.tfsm.k3dsa.aspect.FSMClockAspect.class);
		if (clazz.isInstance(model)) {
			ElementState elemState = theFactory.createElementState();
			elemState.setModelElement(model);
			res.getOwnedElementstates().add(elemState);
				AttributeNameToValue n2v0 = new AttributeNameToValue("numberOfTicks", OncurrentTFSMRTDAccessor.getnumberOfTicks(model));
				elemState.getSavedRTDs().add(n2v0);
		}
		clazz = K3DslHelper.getTarget(org.eclipse.gemoc.example.moccml.tfsm.k3dsa.aspect.TFSMAspect.class);
		if (clazz.isInstance(model)) {
			ElementState elemState = theFactory.createElementState();
			elemState.setModelElement(model);
			res.getOwnedElementstates().add(elemState);
				AttributeNameToValue n2v0 = new AttributeNameToValue("currentState", OncurrentTFSMRTDAccessor.getcurrentState(model));
				elemState.getSavedRTDs().add(n2v0);
		}
		TreeIterator<EObject> allContentIt = model.eAllContents();
		while (allContentIt.hasNext()) {
			EObject elem = allContentIt.next();

			clazz =null;
			clazz = K3DslHelper.getTarget(org.eclipse.gemoc.example.moccml.tfsm.k3dsa.aspect.IntegerVariableAspect.class);
			if (clazz.isInstance(elem)) {
				ElementState elemState = theFactory.createElementState();
				elemState.setModelElement(elem);
				res.getOwnedElementstates().add(elemState);
				AttributeNameToValue n2v0 = new AttributeNameToValue("currentValue", OncurrentTFSMRTDAccessor.getcurrentValue(elem));
				elemState.getSavedRTDs().add(n2v0);
			}
			clazz = K3DslHelper.getTarget(org.eclipse.gemoc.example.moccml.tfsm.k3dsa.aspect.BooleanVariableAspect.class);
			if (clazz.isInstance(elem)) {
				ElementState elemState = theFactory.createElementState();
				elemState.setModelElement(elem);
				res.getOwnedElementstates().add(elemState);
				AttributeNameToValue n2v0 = new AttributeNameToValue("currentValue", OncurrentTFSMRTDAccessor.getcurrentValue(elem));
				elemState.getSavedRTDs().add(n2v0);
			}
			clazz = K3DslHelper.getTarget(org.eclipse.gemoc.example.moccml.tfsm.k3dsa.aspect.FSMClockAspect.class);
			if (clazz.isInstance(elem)) {
				ElementState elemState = theFactory.createElementState();
				elemState.setModelElement(elem);
				res.getOwnedElementstates().add(elemState);
				AttributeNameToValue n2v0 = new AttributeNameToValue("numberOfTicks", OncurrentTFSMRTDAccessor.getnumberOfTicks(elem));
				elemState.getSavedRTDs().add(n2v0);
			}
			clazz = K3DslHelper.getTarget(org.eclipse.gemoc.example.moccml.tfsm.k3dsa.aspect.TFSMAspect.class);
			if (clazz.isInstance(elem)) {
				ElementState elemState = theFactory.createElementState();
				elemState.setModelElement(elem);
				res.getOwnedElementstates().add(elemState);
				AttributeNameToValue n2v0 = new AttributeNameToValue("currentState", OncurrentTFSMRTDAccessor.getcurrentState(elem));
				elemState.getSavedRTDs().add(n2v0);
			}
		}
		return res;
		}

		public void restoreModelState(K3ModelState state) {
		for(ElementState elemState : state.getOwnedElementstates()) {
			for(Object o : elemState.getSavedRTDs()) {
				AttributeNameToValue n2v = (AttributeNameToValue)o;
						Method setter = null;
						setter = getSetter(n2v);
						try {
							setter.invoke(null, elemState.getModelElement(), n2v.value);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
				
			}
		}
	}


	private Method getSetter(AttributeNameToValue n2v) {
		Method setter = null;
		try {
			if(n2v.value != null) {
				setter = OncurrentTFSMRTDAccessor.class.getMethod("set"+n2v.name, EObject.class, n2v.value.getClass());
			}else {
				for (Method m : OncurrentTFSMRTDAccessor.class.getMethods()) {
					if (m.getName().compareTo("set"+n2v.name) ==0 && m.getParameterCount() == 2) {
						setter= m;
						break;
					}
				}
			}
			return setter;
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException e) {
			if(n2v.value != null) {
					List<Class> allTypes = getSuperClasses(n2v.value.getClass());
					allTypes.addAll(Arrays.asList(n2v.value.getClass().getInterfaces()));
					for(Class<?> c : allTypes) {
						try {
							setter = OncurrentTFSMRTDAccessor.class.getMethod("set"+n2v.name, EObject.class, c);
							return setter;
						} catch (NoSuchMethodException | SecurityException | IllegalArgumentException e1) {
						}
					}
					if (setter == null) {
						throw new RuntimeException("no method found for "+n2v.value.getClass().getName()+"::set"+n2v.name);
					}
				}
			}
			return setter;
	}
	
	public static List<Class> getSuperClasses(Class c) {
		List<Class> r = new ArrayList<>();
		List<Class> q = new ArrayList<>();
		q.add(c);
		while (!q.isEmpty()) {
			c = q.remove(0);
			r.add(c);
			if (c.getSuperclass() != null) {
				q.add(c.getSuperclass());
			}
			for (Class i : c.getInterfaces()) {
				q.add(i);
			}
		}
		return r;
	}
};