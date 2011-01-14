package hubin.watchme.systeminterface;

import hubin.watchme.WatchMeMain;

import java.awt.Cursor;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * Default implementation of {@link SystemInterface} for the Watch Me
 * application.
 */
public class DefaultSystemInterface implements SystemInterface {

	protected WatchMeMain app;

	public DefaultSystemInterface(WatchMeMain app) {
		this.app = app;
	}

	public File getFileFromUser() {
		app.setCursor(Cursor.WAIT_CURSOR);
		JFileChooser chooser = new JFileChooser(app.getAttributes()
				.getLastFileChooserDirectory());
		app.setCursor(Cursor.DEFAULT_CURSOR);
		int returnVal = chooser.showOpenDialog(app.getFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}

	public void exit(int code) {
		System.exit(code);
	}

}
