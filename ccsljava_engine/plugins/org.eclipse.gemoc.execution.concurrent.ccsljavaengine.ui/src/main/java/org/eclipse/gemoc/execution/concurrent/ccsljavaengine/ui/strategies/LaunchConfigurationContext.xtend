package org.eclipse.gemoc.execution.concurrent.ccsljavaengine.ui.strategies

import java.beans.PropertyChangeListener
import java.util.Set
import org.eclipse.emf.ecore.EPackage

interface LaunchConfigurationContext {
	
	/**
	 * Identification string for metamodels property.
	 */
	val METAMODELS = "metamodels"
	
	/**
	 * Identification string for semantics property.
	 */
	val SEMANTICS = "semantics"
	
	/**
	 * Return the metamodel currently specified in the launch configuration, if any. 
	 * 
	 * Multiple calls will return the same result unless the user has made a change to their selection in the meantime.
	 */
	def Set<EPackage> getMetamodels()
	
	/**
	 * Register a listener to be informed on any changes of the metamodel selected in this launch configuration. 
	 */
	def void addMetamodelChangeListener(PropertyChangeListener pcl)
	
	/**
	 * Return the semantics (rules) currently specified in the launch configuration, if any. 
	 * 
	 * Multiple calls will return the same result unless the user has made a change to their selection in the meantime.
	 */
	def Set<String> getSemantics()

	/**
	 * Register a listener to be informed on any changes of the semantics (rule set) selected in this launch configuration. 
	 */
	def void addSemanticsChangeListener(PropertyChangeListener pcl)	
}