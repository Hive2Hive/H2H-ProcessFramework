package org.hive2hive.processframework.processes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hive2hive.processframework.Process;
import org.hive2hive.processframework.ProcessComponent;
import org.hive2hive.processframework.ProcessState;
import org.hive2hive.processframework.exceptions.InvalidProcessStateException;
import org.hive2hive.processframework.exceptions.ProcessExecutionException;
import org.hive2hive.processframework.exceptions.ProcessRollbackException;

/**
 * A {@link Process} that traverses its components in preorder (i.e., left-to-right).
 * 
 * @author Christian L�thold
 *
 */
public class PreorderProcess extends Process<Void> {

	private List<ProcessComponent<?>> components = new ArrayList<ProcessComponent<?>>();
	
	//private List<Future<RollbackReason>> asyncHandles = new ArrayList<Future<RollbackReason>>();
	//private ProcessExecutionException exception = null;

	@Override
	protected Void doExecute() throws InvalidProcessStateException, ProcessExecutionException {
		
		// don't use iterator, as component list might be modified during execution
		int executionIndex = 0;
		while (executionIndex < components.size() && getState() == ProcessState.EXECUTING) {

			//checkAsyncComponentsForFail(asyncHandles);
			ProcessComponent<?> next = components.get(executionIndex);
			next.execute();
			executionIndex++;

			/*if (next instanceof AsyncComponent) {
				asyncHandles.add(((AsyncComponent) next).getHandle());
			}*/
		}

		// wait for async child components
		//awaitAsync();
		return null;
	}

	@Override
	protected void doRollback() throws InvalidProcessStateException, ProcessRollbackException {

		// don't use iterator, as component list might be modified during rollback
		int rollbackIndex = components.size() - 1;
		
		while (rollbackIndex >= 0 && getState() == ProcessState.ROLLBACKING) {
			ProcessComponent<?> last = components.get(rollbackIndex);
			last.rollback();
			rollbackIndex--;
		}
	}

	@Override
	protected void doAdd(ProcessComponent<?> component) {
		components.add(component);
	}

	@Override
	protected void doAdd(int index, ProcessComponent<?> component) {
		components.add(index, component);
	}

	@Override
	protected void doRemove(ProcessComponent<?> component) {
		components.remove(component);
	}

	@Override
	public List<ProcessComponent<?>> getComponents() {
		return Collections.unmodifiableList(components);
	}

	@Override
	public ProcessComponent<?> getComponent(int index) {
		return components.get(index);
	}

	/*private void awaitAsync() throws ProcessExecutionException {

		if (asyncHandles.isEmpty())
			return;

		if (getState() != ProcessState.EXECUTING)
			return;

		// logger.debug("Awaiting async components for completion.");

		final CountDownLatch latch = new CountDownLatch(1);
		ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
		ScheduledFuture<?> handle = executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {

				// assure still in running state
				if (getState() != ProcessState.EXECUTING) {
					latch.countDown();
					return;
				}

				// check for potential fails
				try {
					checkAsyncComponentsForFail(asyncHandles);
				} catch (ProcessExecutionException e) {
					exception = e;
					latch.countDown();
					return;
				}

				// check for completion
				for (Future<RollbackReason> handle : asyncHandles) {
					if (!handle.isDone())
						return;
				}
				latch.countDown();
			}
		}, 1, 1, TimeUnit.SECONDS);

		// blocking wait for completion or potential fail
		try {
			latch.await();
		} catch (InterruptedException e) {
			// logger.error("Exception while waiting for async components.", e);
		}
		handle.cancel(true);

		if (exception != null) {
			throw exception;
		}
	}

	private static void checkAsyncComponentsForFail(List<Future<RollbackReason>> handles)
			throws ProcessExecutionException {

		if (handles.isEmpty())
			return;

		for (Future<RollbackReason> handle : handles) {

			if (!handle.isDone())
				continue;

			RollbackReason result = null;
			try {
				result = handle.get();
			} catch (InterruptedException e) {
				// logger.error("Error while checking async component.", e);
			} catch (ExecutionException e) {
				throw new ProcessExecutionException("AsyncComponent threw an exception.", e.getCause());
			}

			// initiate rollback if necessary
			if (result != null) {
				throw new ProcessExecutionException(result);
			}
		}
	}*/

}
