/**
 */
package org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.Application;
import org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.HWPlatform;
import org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.SigpmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.impl.SystemImpl#getOwnedApplication <em>Owned Application</em>}</li>
 *   <li>{@link org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.impl.SystemImpl#getOwnedHWPlatform <em>Owned HW Platform</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemImpl extends NamedElementImpl implements org.eclipse.gemoc.example.moccmlsigpml.model.sigpml.System {
	/**
	 * The cached value of the '{@link #getOwnedApplication() <em>Owned Application</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedApplication()
	 * @generated
	 * @ordered
	 */
	protected Application ownedApplication;

	/**
	 * The cached value of the '{@link #getOwnedHWPlatform() <em>Owned HW Platform</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedHWPlatform()
	 * @generated
	 * @ordered
	 */
	protected HWPlatform ownedHWPlatform;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SigpmlPackage.Literals.SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Application getOwnedApplication() {
		return ownedApplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedApplication(Application newOwnedApplication, NotificationChain msgs) {
		Application oldOwnedApplication = ownedApplication;
		ownedApplication = newOwnedApplication;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SigpmlPackage.SYSTEM__OWNED_APPLICATION, oldOwnedApplication, newOwnedApplication);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedApplication(Application newOwnedApplication) {
		if (newOwnedApplication != ownedApplication) {
			NotificationChain msgs = null;
			if (ownedApplication != null)
				msgs = ((InternalEObject)ownedApplication).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SigpmlPackage.SYSTEM__OWNED_APPLICATION, null, msgs);
			if (newOwnedApplication != null)
				msgs = ((InternalEObject)newOwnedApplication).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SigpmlPackage.SYSTEM__OWNED_APPLICATION, null, msgs);
			msgs = basicSetOwnedApplication(newOwnedApplication, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SigpmlPackage.SYSTEM__OWNED_APPLICATION, newOwnedApplication, newOwnedApplication));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HWPlatform getOwnedHWPlatform() {
		return ownedHWPlatform;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedHWPlatform(HWPlatform newOwnedHWPlatform, NotificationChain msgs) {
		HWPlatform oldOwnedHWPlatform = ownedHWPlatform;
		ownedHWPlatform = newOwnedHWPlatform;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SigpmlPackage.SYSTEM__OWNED_HW_PLATFORM, oldOwnedHWPlatform, newOwnedHWPlatform);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedHWPlatform(HWPlatform newOwnedHWPlatform) {
		if (newOwnedHWPlatform != ownedHWPlatform) {
			NotificationChain msgs = null;
			if (ownedHWPlatform != null)
				msgs = ((InternalEObject)ownedHWPlatform).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SigpmlPackage.SYSTEM__OWNED_HW_PLATFORM, null, msgs);
			if (newOwnedHWPlatform != null)
				msgs = ((InternalEObject)newOwnedHWPlatform).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SigpmlPackage.SYSTEM__OWNED_HW_PLATFORM, null, msgs);
			msgs = basicSetOwnedHWPlatform(newOwnedHWPlatform, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SigpmlPackage.SYSTEM__OWNED_HW_PLATFORM, newOwnedHWPlatform, newOwnedHWPlatform));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SigpmlPackage.SYSTEM__OWNED_APPLICATION:
				return basicSetOwnedApplication(null, msgs);
			case SigpmlPackage.SYSTEM__OWNED_HW_PLATFORM:
				return basicSetOwnedHWPlatform(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SigpmlPackage.SYSTEM__OWNED_APPLICATION:
				return getOwnedApplication();
			case SigpmlPackage.SYSTEM__OWNED_HW_PLATFORM:
				return getOwnedHWPlatform();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SigpmlPackage.SYSTEM__OWNED_APPLICATION:
				setOwnedApplication((Application)newValue);
				return;
			case SigpmlPackage.SYSTEM__OWNED_HW_PLATFORM:
				setOwnedHWPlatform((HWPlatform)newValue);
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
			case SigpmlPackage.SYSTEM__OWNED_APPLICATION:
				setOwnedApplication((Application)null);
				return;
			case SigpmlPackage.SYSTEM__OWNED_HW_PLATFORM:
				setOwnedHWPlatform((HWPlatform)null);
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
			case SigpmlPackage.SYSTEM__OWNED_APPLICATION:
				return ownedApplication != null;
			case SigpmlPackage.SYSTEM__OWNED_HW_PLATFORM:
				return ownedHWPlatform != null;
		}
		return super.eIsSet(featureID);
	}

} //SystemImpl
