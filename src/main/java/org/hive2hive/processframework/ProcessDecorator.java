package org.hive2hive.processframework;

import java.util.List;

import org.hive2hive.processframework.interfaces.IProcessComponent;
import org.hive2hive.processframework.interfaces.IProcessComponentListener;

/**
 * Abstract base class for all decorators that decorate {@link IProcessComponent}s.
 * These decorators provide additional behavior or state to existing implementations of
 * {@link IProcessComponent}s.
 * This abstract base class just delegates all calls to the wrapped/decorated {@link IProcessComponent}
 * implementation.
 * 
 * @author Christian Lüthold
 * 
 * @param <T> The type of the result computed by the wrapped/decorated {@code IProcessComponent}.
 */
public abstract class ProcessDecorator<T> extends ProcessComponent<T> {

	protected final IProcessComponent<?> decoratedComponent;

	/**
	 * Creates a {@code ProcessDecorator} that wraps/decorates the provided {@link IProcessComponent}
	 * implementation.
	 * 
	 * @param decoratedComponent The {@link IProcessComponent} implementation to be wrapped/decorated by this
	 *            {@code ProcessDecorator}.
	 */
	protected ProcessDecorator(IProcessComponent<?> decoratedComponent) {
		this.decoratedComponent = decoratedComponent;

		// decorators should always require rollback
		setRequiresRollback(true);
	}

	/**
	 * Gets the {@link IProcessComponent} decorated by this {@code ProcessDecorator}.
	 * 
	 * @return The {@link IProcessComponent} decorated by this {@code ProcessDecorator}.
	 */
	public IProcessComponent<?> getDecoratedComponent() {
		return decoratedComponent;
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public ProcessComposite<?> getParent() {
		return decoratedComponent.getParent();
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public void setName(String name) {
		decoratedComponent.setName(name);
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public String getName() {
		return decoratedComponent.getName();
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public String getID() {
		return decoratedComponent.getID();
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public double getProgress() {
		return decoratedComponent.getProgress();
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public ProcessState getState() {
		return decoratedComponent.getState();
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public synchronized void attachListener(IProcessComponentListener listener) {
		decoratedComponent.attachListener(listener);
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public synchronized void detachListener(IProcessComponentListener listener) {
		decoratedComponent.detachListener(listener);
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public List<IProcessComponentListener> getListeners() {
		return decoratedComponent.getListeners();
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public String toString() {
		return decoratedComponent.toString();
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public boolean equals(Object obj) {
		return decoratedComponent.equals(obj);
	}

	/**
	 * Default decorator implementation:
	 * Just delegates the call to the wrapped/decorated {@link IProcessComponent} implementation.
	 */
	@Override
	public int hashCode() {
		return decoratedComponent.hashCode();
	}

}