package hubin.watchme.systeminterface;

import hubin.watchme.WatchMeMain;

import java.io.File;

/**
 * Various system calls are routed through an instance of this class; this
 * enables test code to intersect itself where appropriate by assigning a
 * different instance of SystemInterface to {@link WatchMeMain}.
 */
public interface SystemInterface {

	/**
	 * Normally, this method should delegate to a file chooser or other
	 * appropriate file selection mechanism. However, it can be overridden by
	 * tests to return temporary files.
	 * 
	 * @return the File selected by the user
	 */
	public File getFileFromUser();

	/**
	 * Used in lieu of System.exit();
	 * 
	 * @param code
	 *            exit status code
	 */
	public void exit(int code);

}
