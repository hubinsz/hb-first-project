package hubin.watchme.gui;

import hubin.watchme.WatchMeMain;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;

import javax.swing.JOptionPane;

/**
 * Action which opens a new file in the Follow application.
 * 
 */
@SuppressWarnings("serial")
public class Open extends FollowAppAction {
	public static final String NAME = "open";

	private File recentFile;

	public Open(WatchMeMain app) {
		super(app, WatchMeMain.getResourceString("action.Open.name"),
				WatchMeMain.getResourceString("action.Open.mnemonic"),
				WatchMeMain.getResourceString("action.Open.accelerator"),
				WatchMeMain.getIcon(Open.class, "action.Open.icon"),
				ActionContext.APP);
	}

	public Open(WatchMeMain app, File recentFile) {
		super(app, recentFile.getAbsolutePath(), WatchMeMain
				.getResourceString("action.Open.mnemonic"), WatchMeMain
				.getResourceString("action.Open.accelerator"),
				ActionContext.APP);
		this.recentFile = recentFile;
	}

	public void actionPerformed(ActionEvent e) {
		File f = null;
		try {
			if (recentFile != null) {
				f = recentFile;
				getApp().openFile(recentFile);
			} else {
				f = getApp().getSystemInterface().getFileFromUser();
				if (f != null) {
					getApp().openFile(f);
				}
			}
		} catch (FileNotFoundException ex) {
			String msg = MessageFormat.format(WatchMeMain
					.getResourceString("message.cmdLineFileNotFound.text"),
					new Object[] { f });
			JOptionPane
					.showMessageDialog(
							getApp().getFrame(),
							msg,
							WatchMeMain
									.getResourceString("message.filesDeletedSinceLastExecution.title"),
							JOptionPane.WARNING_MESSAGE);
		}

	}
}