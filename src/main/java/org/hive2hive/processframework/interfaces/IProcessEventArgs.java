package org.hive2hive.processframework.interfaces;


/**
 * Basic interface for process event arguments.
 * 
 * @author Christian L�thold
 * 
 */
public interface IProcessEventArgs {

	/**
	 * Gets the {@link IProcessComponent} that triggered the event.
	 * 
	 * @return The {@link IProcessComponent} that triggered the event.
	 */
	IProcessComponent<?> getSource();
}
