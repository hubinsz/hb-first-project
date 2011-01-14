package hubin.watchme.gui;

import hubin.watchme.WatchMeMain;

import java.awt.event.ActionEvent;
import java.text.MessageFormat;

import javax.swing.JOptionPane;

/**
 * Action which closes the currently followed file.
 */
@SuppressWarnings("serial")
public class Edit extends FollowAppAction {
	public static final String NAME = "edit";

	public Edit(WatchMeMain app) {
		super(app, WatchMeMain.getResourceString("action.Edit.name"),
				WatchMeMain.getResourceString("action.Edit.mnemonic"),
				WatchMeMain.getResourceString("action.Edit.accelerator"),
				WatchMeMain.getIcon(Edit.class, "action.Edit.icon"),
				ActionContext.SINGLE_FILE);
	}

	public void actionPerformed(ActionEvent e) {
		FileFollowingPane fileFollowingPane = getApp()
				.getSelectedFileFollowingPane();

		String config = getApp().getAttributes().getEditor();
		if ("".equalsIgnoreCase(config)) {
			// user have not set the external edit yet
			JOptionPane
					.showMessageDialog(
							getApp().getFrame(),
							WatchMeMain
									.getResourceString("message.external.editor.not.set.text"),
							WatchMeMain
									.getResourceString("message.external.editor.not.set.title"),
							JOptionPane.WARNING_MESSAGE);

		} else {
			// assume the setting is right
			ExternalEditor editor = new ExternalEditor(config);
			try {
				editor.exec(fileFollowingPane.getFollowedFile());
			} catch (Exception e1) {
				e1.printStackTrace();
				String msg = MessageFormat
						.format(
								WatchMeMain
										.getResourceString("message.external.editor.cannot.execute.text"),
								new Object[] { config });
				JOptionPane
						.showMessageDialog(
								getApp().getFrame(),
								msg,
								WatchMeMain
										.getResourceString("message.external.editor.cannot.execute.title"),
								JOptionPane.WARNING_MESSAGE);

			}

		}

	}
}