/**
 */
package org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.Agent;
import org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.HWComputationalResource;
import org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.SigpmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>HW Computational Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.impl.HWComputationalResourceImpl#isIsUnderPreemptiveManagement <em>Is Under Preemptive Management</em>}</li>
 *   <li>{@link org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.impl.HWComputationalResourceImpl#getAllocatedAgents <em>Allocated Agents</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HWComputationalResourceImpl extends HWRessourceImpl implements HWComputationalResource {
	/**
	 * The default value of the '{@link #isIsUnderPreemptiveManagement() <em>Is Under Preemptive Management</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnderPreemptiveManagement()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_UNDER_PREEMPTIVE_MANAGEMENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsUnderPreemptiveManagement() <em>Is Under Preemptive Management</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnderPreemptiveManagement()
	 * @generated
	 * @ordered
	 */
	protected boolean isUnderPreemptiveManagement = IS_UNDER_PREEMPTIVE_MANAGEMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAllocatedAgents() <em>Allocated Agents</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllocatedAgents()
	 * @generated
	 * @ordered
	 */
	protected EList<Agent> allocatedAgents;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HWComputationalResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SigpmlPackage.Literals.HW_COMPUTATIONAL_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsUnderPreemptiveManagement() {
		return isUnderPreemptiveManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsUnderPreemptiveManagement(boolean newIsUnderPreemptiveManagement) {
		boolean oldIsUnderPreemptiveManagement = isUnderPreemptiveManagement;
		isUnderPreemptiveManagement = newIsUnderPreemptiveManagement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__IS_UNDER_PREEMPTIVE_MANAGEMENT, oldIsUnderPreemptiveManagement, isUnderPreemptiveManagement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Agent> getAllocatedAgents() {
		if (allocatedAgents == null) {
			allocatedAgents = new EObjectResolvingEList<Agent>(Agent.class, this, SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__ALLOCATED_AGENTS);
		}
		return allocatedAgents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void isExecuting() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void idle() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__IS_UNDER_PREEMPTIVE_MANAGEMENT:
				return isIsUnderPreemptiveManagement();
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__ALLOCATED_AGENTS:
				return getAllocatedAgents();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__IS_UNDER_PREEMPTIVE_MANAGEMENT:
				setIsUnderPreemptiveManagement((Boolean)newValue);
				return;
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__ALLOCATED_AGENTS:
				getAllocatedAgents().clear();
				getAllocatedAgents().addAll((Collection<? extends Agent>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__IS_UNDER_PREEMPTIVE_MANAGEMENT:
				setIsUnderPreemptiveManagement(IS_UNDER_PREEMPTIVE_MANAGEMENT_EDEFAULT);
				return;
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__ALLOCATED_AGENTS:
				getAllocatedAgents().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__IS_UNDER_PREEMPTIVE_MANAGEMENT:
				return isUnderPreemptiveManagement != IS_UNDER_PREEMPTIVE_MANAGEMENT_EDEFAULT;
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE__ALLOCATED_AGENTS:
				return allocatedAgents != null && !allocatedAgents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE___IS_EXECUTING:
				isExecuting();
				return null;
			case SigpmlPackage.HW_COMPUTATIONAL_RESOURCE___IDLE:
				idle();
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (isUnderPreemptiveManagement: ");
		result.append(isUnderPreemptiveManagement);
		result.append(')');
		return result.toString();
	}

} //HWComputationalResourceImpl
