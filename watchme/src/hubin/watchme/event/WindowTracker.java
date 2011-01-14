package hubin.watchme.event;

import hubin.watchme.config.WatchMeAppAttributes;
import hubin.watchme.systeminterface.SystemInterface;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JTabbedPane;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Will write window position/size to the supplied FollowAppAttributes object.
 * The supplied keys will be used when writing position/size values to the
 * FollowAppAttributes object.
 * 
 */
public class WindowTracker extends WindowAdapter {
	private Logger log = Logger.getLogger(WindowTracker.class.getName());
	protected WatchMeAppAttributes attributes;
	protected JTabbedPane tabbedPane;
	protected SystemInterface systemInterface;

	/**
	 * Construct a new WindowTracker which will write window position/size to
	 * the supplied FollowAppAttributes object. The supplied keys will be used
	 * when writing position/size values to the FollowAppAttributes object.
	 * 
	 * @param attributes
	 *            attributes object to which window size & position should be
	 *            written
	 */
	public WindowTracker(WatchMeAppAttributes attributes,
			JTabbedPane tabbedPane, SystemInterface systemInterface) {
		this.attributes = attributes;
		this.tabbedPane = tabbedPane;
		this.systemInterface = systemInterface;
	}

	/**
	 * Each time this method is invoked, the position/size of the window which
	 * is closing will be written to the FollowAppAttributes object.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		Window window = (Window) e.getSource();
		attributes.setWidth(window.getWidth());
		attributes.setHeight(window.getHeight());
		attributes.setX(window.getX());
		attributes.setY(window.getY());

		if (tabbedPane.getTabCount() > 0) {
			attributes.setSelectedTabIndex(tabbedPane.getSelectedIndex());
		}
		((Window) e.getSource()).dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		try {
			attributes.store();
		} catch (IOException ioe) {
			log.log(Level.SEVERE,
					"Error encountered while storing properties...", ioe);
		} finally {
			systemInterface.exit(0);
		}
	}
}