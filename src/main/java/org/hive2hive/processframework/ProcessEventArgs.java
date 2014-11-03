package org.hive2hive.processframework;

import org.hive2hive.processframework.interfaces.IProcessComponent;
import org.hive2hive.processframework.interfaces.IProcessEventArgs;

/**
 * Default process event arguments.
 * 
 * @author Christian L�thold
 *
 */
public class ProcessEventArgs implements IProcessEventArgs {

	private IProcessComponent<?> originalSource;

	public ProcessEventArgs(IProcessComponent<?> originalSource) {
		this.originalSource = originalSource;
	}

	@Override
	public IProcessComponent<?> getOriginalSource() {
		return originalSource;
	}

}
