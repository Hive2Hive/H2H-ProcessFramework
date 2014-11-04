package org.hive2hive.processframework;

import org.hive2hive.processframework.exceptions.InvalidProcessStateException;
import org.hive2hive.processframework.exceptions.ProcessRollbackException;

/**
 * Abstract base class for all normal {@link ProcessComponent}s (leaf).
 * These normal components represent a specific operation and do not contain other {@link ProcessComponent}s.
 * 
 * @author Christian L�thold
 *
 * @param <T> The type of the result computed by this {@code ProcessStep}.
 */
public abstract class ProcessStep<T> extends ProcessComponent<T> {

	@Override
	protected T doRollback() throws InvalidProcessStateException, ProcessRollbackException {
		// do nothing by default
		return null;
	}

	@Override
	protected void doPause() throws InvalidProcessStateException {
		// do nothing by default
	}

	@Override
	protected void doResumeExecution() throws InvalidProcessStateException {
		// do nothing by default
	}

	@Override
	protected void doResumeRollback() throws InvalidProcessStateException {
		// do nothing by default
	}
}
