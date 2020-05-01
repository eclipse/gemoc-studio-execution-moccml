/**
 */
package org.eclipse.gemoc.example.moccml.tfsm.tfsm.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.gemoc.example.moccml.tfsm.tfsm.util.TfsmAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TfsmItemProviderAdapterFactory extends TfsmAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TfsmItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.TFSM} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TFSMItemProvider tfsmItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.TFSM}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTFSMAdapter() {
		if (tfsmItemProvider == null) {
			tfsmItemProvider = new TFSMItemProvider(this);
		}

		return tfsmItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.State} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateItemProvider stateItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.State}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStateAdapter() {
		if (stateItemProvider == null) {
			stateItemProvider = new StateItemProvider(this);
		}

		return stateItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.Transition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionItemProvider transitionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.Transition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTransitionAdapter() {
		if (transitionItemProvider == null) {
			transitionItemProvider = new TransitionItemProvider(this);
		}

		return transitionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.TemporalGuard} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemporalGuardItemProvider temporalGuardItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.TemporalGuard}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTemporalGuardAdapter() {
		if (temporalGuardItemProvider == null) {
			temporalGuardItemProvider = new TemporalGuardItemProvider(this);
		}

		return temporalGuardItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.EventGuard} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventGuardItemProvider eventGuardItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.EventGuard}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEventGuardAdapter() {
		if (eventGuardItemProvider == null) {
			eventGuardItemProvider = new EventGuardItemProvider(this);
		}

		return eventGuardItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.FSMEvent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FSMEventItemProvider fsmEventItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.FSMEvent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFSMEventAdapter() {
		if (fsmEventItemProvider == null) {
			fsmEventItemProvider = new FSMEventItemProvider(this);
		}

		return fsmEventItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.FSMClock} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FSMClockItemProvider fsmClockItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.FSMClock}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFSMClockAdapter() {
		if (fsmClockItemProvider == null) {
			fsmClockItemProvider = new FSMClockItemProvider(this);
		}

		return fsmClockItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.TimedSystem} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimedSystemItemProvider timedSystemItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.TimedSystem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTimedSystemAdapter() {
		if (timedSystemItemProvider == null) {
			timedSystemItemProvider = new TimedSystemItemProvider(this);
		}

		return timedSystemItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.EvaluateGuard} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EvaluateGuardItemProvider evaluateGuardItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.EvaluateGuard}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEvaluateGuardAdapter() {
		if (evaluateGuardItemProvider == null) {
			evaluateGuardItemProvider = new EvaluateGuardItemProvider(this);
		}

		return evaluateGuardItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.IntegerVariable} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntegerVariableItemProvider integerVariableItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.IntegerVariable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIntegerVariableAdapter() {
		if (integerVariableItemProvider == null) {
			integerVariableItemProvider = new IntegerVariableItemProvider(this);
		}

		return integerVariableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.BooleanVariable} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanVariableItemProvider booleanVariableItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.BooleanVariable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBooleanVariableAdapter() {
		if (booleanVariableItemProvider == null) {
			booleanVariableItemProvider = new BooleanVariableItemProvider(this);
		}

		return booleanVariableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.OpaqueBooleanExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OpaqueBooleanExpressionItemProvider opaqueBooleanExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.OpaqueBooleanExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOpaqueBooleanExpressionAdapter() {
		if (opaqueBooleanExpressionItemProvider == null) {
			opaqueBooleanExpressionItemProvider = new OpaqueBooleanExpressionItemProvider(this);
		}

		return opaqueBooleanExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.OpaqueIntegerExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OpaqueIntegerExpressionItemProvider opaqueIntegerExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.OpaqueIntegerExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOpaqueIntegerExpressionAdapter() {
		if (opaqueIntegerExpressionItemProvider == null) {
			opaqueIntegerExpressionItemProvider = new OpaqueIntegerExpressionItemProvider(this);
		}

		return opaqueIntegerExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.IntegerCalculationExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntegerCalculationExpressionItemProvider integerCalculationExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.IntegerCalculationExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIntegerCalculationExpressionAdapter() {
		if (integerCalculationExpressionItemProvider == null) {
			integerCalculationExpressionItemProvider = new IntegerCalculationExpressionItemProvider(this);
		}

		return integerCalculationExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.IntegerComparisonExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntegerComparisonExpressionItemProvider integerComparisonExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.IntegerComparisonExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIntegerComparisonExpressionAdapter() {
		if (integerComparisonExpressionItemProvider == null) {
			integerComparisonExpressionItemProvider = new IntegerComparisonExpressionItemProvider(this);
		}

		return integerComparisonExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.BooleanUnaryExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanUnaryExpressionItemProvider booleanUnaryExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.BooleanUnaryExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBooleanUnaryExpressionAdapter() {
		if (booleanUnaryExpressionItemProvider == null) {
			booleanUnaryExpressionItemProvider = new BooleanUnaryExpressionItemProvider(this);
		}

		return booleanUnaryExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.BooleanBinaryExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanBinaryExpressionItemProvider booleanBinaryExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gemoc.example.moccml.tfsm.tfsm.BooleanBinaryExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBooleanBinaryExpressionAdapter() {
		if (booleanBinaryExpressionItemProvider == null) {
			booleanBinaryExpressionItemProvider = new BooleanBinaryExpressionItemProvider(this);
		}

		return booleanBinaryExpressionItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (tfsmItemProvider != null) tfsmItemProvider.dispose();
		if (stateItemProvider != null) stateItemProvider.dispose();
		if (transitionItemProvider != null) transitionItemProvider.dispose();
		if (temporalGuardItemProvider != null) temporalGuardItemProvider.dispose();
		if (eventGuardItemProvider != null) eventGuardItemProvider.dispose();
		if (fsmEventItemProvider != null) fsmEventItemProvider.dispose();
		if (fsmClockItemProvider != null) fsmClockItemProvider.dispose();
		if (timedSystemItemProvider != null) timedSystemItemProvider.dispose();
		if (evaluateGuardItemProvider != null) evaluateGuardItemProvider.dispose();
		if (integerVariableItemProvider != null) integerVariableItemProvider.dispose();
		if (booleanVariableItemProvider != null) booleanVariableItemProvider.dispose();
		if (opaqueBooleanExpressionItemProvider != null) opaqueBooleanExpressionItemProvider.dispose();
		if (opaqueIntegerExpressionItemProvider != null) opaqueIntegerExpressionItemProvider.dispose();
		if (integerCalculationExpressionItemProvider != null) integerCalculationExpressionItemProvider.dispose();
		if (integerComparisonExpressionItemProvider != null) integerComparisonExpressionItemProvider.dispose();
		if (booleanUnaryExpressionItemProvider != null) booleanUnaryExpressionItemProvider.dispose();
		if (booleanBinaryExpressionItemProvider != null) booleanBinaryExpressionItemProvider.dispose();
	}

}
