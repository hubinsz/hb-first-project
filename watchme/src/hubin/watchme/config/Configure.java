package hubin.watchme.config;

import hubin.watchme.WatchMeMain;
import hubin.watchme.font.FontSelectionPanel;
import hubin.watchme.font.InvalidFontException;
import hubin.watchme.gui.FileFollowingPane;
import hubin.watchme.gui.FollowAppAction;
import hubin.watchme.gui.WhatIsThis;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * Action which brings up a dialog allowing one to configure the Follow
 * application.
 */
@SuppressWarnings("serial")
public class Configure extends FollowAppAction {
	public static final String NAME = "configure";

	public Configure(WatchMeMain app) {
		super(app, WatchMeMain.getResourceString("action.Configure.name"),
				WatchMeMain.getResourceString("action.Configure.mnemonic"),
				WatchMeMain.getResourceString("action.Configure.accelerator"),
				WatchMeMain.getIcon(Configure.class, "action.Configure.icon"),
				ActionContext.APP);
	}

	public void actionPerformed(ActionEvent e) {
		getApp().setCursor(Cursor.WAIT_CURSOR);
		if (dialog == null) {
			dialog = new CfgDialog();
		}
		dialog.bufferSize.setText(String.valueOf(getApp().getAttributes()
				.getBufferSize()));
		dialog.latency.setText(String.valueOf(getApp().getAttributes()
				.getLatency()));
		dialog.tabPlacement.setSelectedItem(new TabPlacementValue(getApp()
				.getAttributes().getTabPlacement()));
		dialog.confirmDelete.setValue(getApp().getAttributes().confirmDelete());
		dialog.confirmDeleteAll.setValue(getApp().getAttributes()
				.confirmDeleteAll());
		dialog.autoScroll.setValue(getApp().getAttributes().autoScroll());
		dialog.editor.setText(String.valueOf(getApp().getAttributes()
				.getEditor()));
		dialog.tabSize.setText(String.valueOf(getApp().getAttributes()
				.getTabSize()));
		dialog.fontSelectionPanel.setSelectedFont(getApp().getAttributes()
				.getFont());
		dialog.recentFilesMax.setText(String.valueOf(getApp().getAttributes()
				.getRecentFilesMax()));
		// Quasi-kludge to get around font repainting issue
		dialog.setLocationRelativeTo(getApp().getFrame());
		dialog.setLocation(170, 80);
		// No need to set font; this is taken care of during CfgDialog
		// construction
		dialog.pack();
		dialog.setVisible(true);
		getApp().setCursor(Cursor.DEFAULT_CURSOR);
	}

	private CfgDialog dialog;

	class CfgDialog extends JDialog {
		protected JRootPane createRootPane() {
			KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
			JRootPane rootPane = new JRootPane();
			rootPane.registerKeyboardAction(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					dialog.close.doClick();
				}
			}, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
			return rootPane;
		}

		CfgDialog() {
			super(Configure.this.getApp().getFrame(), WatchMeMain
					.getResourceString("dialog.Configure.title"), true);
			JComponent contentPane = (JComponent) getContentPane();
			contentPane.setBorder(BorderFactory.createEmptyBorder(12, 12, 11,
					11));

			JPanel configPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipadx = 4;

			// buffer size
			gbc.gridy = 0;
			configPanel.add(new JLabel(WatchMeMain
					.getResourceString("dialog.Configure.bufferSize.label")),
					gbc);
			bufferSize = new JTextField();
			bufferSize.setHorizontalAlignment(JTextField.RIGHT);
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			configPanel.add(bufferSize, gbc);
			JButton bufferSizeInfo = new WhatIsThis(getApp(), WatchMeMain
					.getResourceString("WhatIsThis.bufferSize.title"),
					WatchMeMain.getResourceString("WhatIsThis.bufferSize.text"));
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			configPanel.add(bufferSizeInfo, gbc);

			// latency
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 4;
			configPanel.add(new JLabel(WatchMeMain
					.getResourceString("dialog.Configure.latency.label")), gbc);
			latency = new JTextField();
			latency.setHorizontalAlignment(JTextField.RIGHT);
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			configPanel.add(latency, gbc);
			JButton latencyInfo = new WhatIsThis(getApp(), WatchMeMain
					.getResourceString("WhatIsThis.latency.title"), WatchMeMain
					.getResourceString("WhatIsThis.latency.text"));
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			configPanel.add(latencyInfo, gbc);

			// tab placement
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 4;
			configPanel.add(new JLabel(WatchMeMain
					.getResourceString("dialog.Configure.tabPlacement.label")),
					gbc);
			tabPlacement = new JComboBox(ALL_TAB_PLACEMENT_VALUES);
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			configPanel.add(tabPlacement, gbc);
			JButton tabPlacementInfo = new WhatIsThis(getApp(), WatchMeMain
					.getResourceString("WhatIsThis.tabPlacement.title"),
					WatchMeMain
							.getResourceString("WhatIsThis.tabPlacement.text"));
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			configPanel.add(tabPlacementInfo, gbc);

			// confirm delete
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 4;
			configPanel
					.add(
							new JLabel(
									WatchMeMain
											.getResourceString("dialog.Configure.confirmDelete.label")),
							gbc);
			confirmDelete = new BooleanComboBox(
					WatchMeMain
							.getResourceString("dialog.Configure.confirmDelete.yes.displayValue"),
					WatchMeMain
							.getResourceString("dialog.Configure.confirmDelete.no.displayValue"));
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			configPanel.add(confirmDelete, gbc);
			JButton confirmDeleteInfo = new WhatIsThis(getApp(), WatchMeMain
					.getResourceString("WhatIsThis.confirmDelete.title"),
					WatchMeMain
							.getResourceString("WhatIsThis.confirmDelete.text"));
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			configPanel.add(confirmDeleteInfo, gbc);

			// confirm delete all
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 4;
			configPanel
					.add(
							new JLabel(
									WatchMeMain
											.getResourceString("dialog.Configure.confirmDeleteAll.label")),
							gbc);
			confirmDeleteAll = new BooleanComboBox(
					WatchMeMain
							.getResourceString("dialog.Configure.confirmDeleteAll.yes.displayValue"),
					WatchMeMain
							.getResourceString("dialog.Configure.confirmDeleteAll.no.displayValue"));
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			configPanel.add(confirmDeleteAll, gbc);
			JButton confirmDeleteAllInfo = new WhatIsThis(
					getApp(),
					WatchMeMain
							.getResourceString("WhatIsThis.confirmDeleteAll.title"),
					WatchMeMain
							.getResourceString("WhatIsThis.confirmDeleteAll.text"));
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			configPanel.add(confirmDeleteAllInfo, gbc);

			// autoscroll
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 4;
			configPanel.add(new JLabel(WatchMeMain
					.getResourceString("dialog.Configure.autoScroll.label")),
					gbc);
			autoScroll = new BooleanComboBox(
					WatchMeMain
							.getResourceString("dialog.Configure.autoScroll.yes.displayValue"),
					WatchMeMain
							.getResourceString("dialog.Configure.autoScroll.no.displayValue"));
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			configPanel.add(autoScroll, gbc);
			JButton autoScrollInfo = new WhatIsThis(getApp(), WatchMeMain
					.getResourceString("WhatIsThis.autoScroll.title"),
					WatchMeMain.getResourceString("WhatIsThis.autoScroll.text"));
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			configPanel.add(autoScrollInfo, gbc);

			// external editor
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 4;
			configPanel.add(new JLabel(WatchMeMain
					.getResourceString("dialog.Configure.editor.label")), gbc);
			editor = new JTextField();
			editor.setHorizontalAlignment(JTextField.LEFT);
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			configPanel.add(editor, gbc);
			JButton editorInfo = new WhatIsThis(getApp(), WatchMeMain
					.getResourceString("WhatIsThis.editor.title"), WatchMeMain
					.getResourceString("WhatIsThis.editor.text"));
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			configPanel.add(editorInfo, gbc);

			// tabSize
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 4;
			configPanel.add(new JLabel(WatchMeMain
					.getResourceString("dialog.Configure.tabSize.label")), gbc);
			tabSize = new JTextField();
			tabSize.setHorizontalAlignment(JTextField.RIGHT);
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			configPanel.add(tabSize, gbc);
			JButton tabSizeInfo = new WhatIsThis(getApp(), WatchMeMain
					.getResourceString("WhatIsThis.tabSize.title"), WatchMeMain
					.getResourceString("WhatIsThis.tabSize.text"));
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			configPanel.add(tabSizeInfo, gbc);

			// recentFilesMax
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.ipadx = 4;
			configPanel
					.add(
							new JLabel(
									WatchMeMain
											.getResourceString("dialog.Configure.recentFilesMax.label")),
							gbc);
			recentFilesMax = new JTextField();
			recentFilesMax.setHorizontalAlignment(JTextField.RIGHT);
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			configPanel.add(recentFilesMax, gbc);
			JButton recentFilesMaxInfo = new WhatIsThis(
					getApp(),
					WatchMeMain
							.getResourceString("WhatIsThis.recentFilesMax.title"),
					WatchMeMain
							.getResourceString("WhatIsThis.recentFilesMax.text"));
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			configPanel.add(recentFilesMaxInfo, gbc);

			// font selection
			fontSelectionPanel = new CfgFontSelectionPanel();
			// Must change border to top=0 because of default top in titled
			// border
			fontSelectionPanel.setBorder(BorderFactory.createEmptyBorder(0, 12,
					11, 11));
			JPanel fontPanelHolder = new JPanel(new BorderLayout());
			fontPanelHolder.add(fontSelectionPanel, BorderLayout.CENTER);
			fontPanelHolder.setBorder(BorderFactory
					.createTitledBorder(WatchMeMain
							.getResourceString("dialog.Configure.font.label")));
			gbc.gridx = 0;
			gbc.gridy++;
			gbc.gridwidth = 3;
			gbc.fill = GridBagConstraints.BOTH;
			configPanel.add(fontPanelHolder, gbc);

			contentPane.add(configPanel, BorderLayout.CENTER);

			// Save button
			save = new JButton(WatchMeMain
					.getResourceString("dialog.Configure.save.label"));
			save.setMnemonic(WatchMeMain.getResourceString(
					"dialog.Configure.save.mnemonic").charAt(0));
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Validate fields
					StringBuffer invalidFieldsMessage = new StringBuffer();
					if (!isPositiveInteger(bufferSize.getText())) {
						invalidFieldsMessage
								.append(WatchMeMain
										.getResourceString("dialog.Configure.bufferSizeInvalid.text"));
						invalidFieldsMessage
								.append(WatchMeMain.MESSAGE_LINE_SEPARATOR);
						invalidFieldsMessage
								.append(WatchMeMain.MESSAGE_LINE_SEPARATOR);
					}
					if (!isPositiveInteger(latency.getText())) {
						invalidFieldsMessage
								.append(WatchMeMain
										.getResourceString("dialog.Configure.latencyInvalid.text"));
						invalidFieldsMessage
								.append(WatchMeMain.MESSAGE_LINE_SEPARATOR);
						invalidFieldsMessage
								.append(WatchMeMain.MESSAGE_LINE_SEPARATOR);
					}
					if (!isPositiveInteger(recentFilesMax.getText())) {
						invalidFieldsMessage
								.append(WatchMeMain
										.getResourceString("dialog.Configure.recentFilesMaxInvalid.text"));
						invalidFieldsMessage
								.append(WatchMeMain.MESSAGE_LINE_SEPARATOR);
						invalidFieldsMessage
								.append(WatchMeMain.MESSAGE_LINE_SEPARATOR);
					}
					try {
						fontSelectionPanel.getSelectedFont();
					} catch (InvalidFontException ife) {
						invalidFieldsMessage
								.append(WatchMeMain
										.getResourceString("dialog.Configure.fontInvalid.text"));
						invalidFieldsMessage
								.append(WatchMeMain.MESSAGE_LINE_SEPARATOR);
						invalidFieldsMessage
								.append(WatchMeMain.MESSAGE_LINE_SEPARATOR);
					}

					if (invalidFieldsMessage.length() > 0) {
						JOptionPane
								.showMessageDialog(
										getApp().getFrame(),
										invalidFieldsMessage.toString(),
										WatchMeMain
												.getResourceString("dialog.Configure.invalidFieldsDialog.title"),
										JOptionPane.ERROR_MESSAGE);
					} else {
						getApp().getAttributes().setBufferSize(
								bufferSize.getText());
						getApp().getAttributes().setLatency(latency.getText());
						getApp().getAttributes().setTabPlacement(
								((TabPlacementValue) tabPlacement
										.getSelectedItem()).value);
						getApp().getAttributes().setConfirmDelete(
								confirmDelete.getValue());
						getApp().getAttributes().setConfirmDeleteAll(
								confirmDeleteAll.getValue());
						getApp().getAttributes().setAutoScroll(
								autoScroll.getValue());
						getApp().getAttributes().setEditor(editor.getText());
						getApp().getAttributes().setTabSize(tabSize.getText());
						getApp().getAttributes().setRecentFilesMax(
								recentFilesMax.getText());
						// getApp().refreshRecentFilesMenu();
						Font selectedFont;
						try {
							selectedFont = fontSelectionPanel.getSelectedFont();
						} catch (InvalidFontException ife) {
							// This shouldn't happen if the error catching at
							// the beginning
							// of actionPerformed() worked correctly
							throw new RuntimeException(
									"Programmatic error; supposedly impossible scenario has occurred.");
						}
						getApp().getAttributes().setFont(selectedFont);
						for (FileFollowingPane pane : getApp()
								.getFileToFollowingPaneMap().values()) {
							pane.getFileFollower().setBufferSize(
									getApp().getAttributes().getBufferSize());
							pane.getFileFollower().setLatency(
									getApp().getAttributes().getLatency());
							pane.getTextPane().setFont(selectedFont);
							pane.setAutoPositionCaret(getApp().getAttributes()
									.autoScroll());
							pane.getTextPane().setTabSize(
									getApp().getAttributes().getTabSize());
							getApp().getTabbedPane().invalidate();
							getApp().getTabbedPane().repaint();
						}
						getApp().getTabbedPane().setTabPlacement(
								getApp().getAttributes().getTabPlacement());
						getApp().getTabbedPane().invalidate();
					}
				}
			});

			// Restore Defaults button
			restoreDefaults = new JButton(
					WatchMeMain
							.getResourceString("dialog.Configure.restoreDefaults.label"));
			restoreDefaults.setMnemonic(WatchMeMain.getResourceString(
					"dialog.Configure.restoreDefaults.mnemonic").charAt(0));
			restoreDefaults.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						bufferSize.setText(String.valueOf(getApp()
								.getAttributes().getDefaultAttributes()
								.getBufferSize()));
						latency.setText(String.valueOf(getApp().getAttributes()
								.getDefaultAttributes().getLatency()));
						tabPlacement.setSelectedItem(new TabPlacementValue(
								getApp().getAttributes().getDefaultAttributes()
										.getTabPlacement()));
						confirmDelete.setValue(getApp().getAttributes()
								.getDefaultAttributes().confirmDelete());
						confirmDeleteAll.setValue(getApp().getAttributes()
								.getDefaultAttributes().confirmDeleteAll());
						autoScroll.setValue(getApp().getAttributes()
								.getDefaultAttributes().autoScroll());
						editor.setText(String.valueOf(getApp().getAttributes()
								.getDefaultAttributes().getEditor()));
						fontSelectionPanel.setSelectedFont(getApp()
								.getAttributes().getDefaultAttributes()
								.getFont());
						recentFilesMax.setText(String.valueOf(getApp()
								.getAttributes().getDefaultAttributes()
								.getRecentFilesMax()));
					} catch (IOException ioe) {
						JOptionPane
								.showMessageDialog(
										getApp().getFrame(),
										WatchMeMain
												.getResourceString("dialog.Configure.cantRestoreDefaults.text"),
										WatchMeMain
												.getResourceString("dialog.Configure.cantRestoreDefaults.title"),
										JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			// Close button
			close = new JButton(WatchMeMain
					.getResourceString("dialog.Configure.close.label"));
			close.setMnemonic(WatchMeMain.getResourceString(
					"dialog.Configure.close.mnemonic").charAt(0));
			close.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			buttonPanel.add(save);
			buttonPanel.add(restoreDefaults);
			buttonPanel.add(close);
			contentPane.add(buttonPanel, BorderLayout.SOUTH);
		}

		private boolean isPositiveInteger(String value) {
			try {
				int intValue = Integer.parseInt(value);
				if (intValue < 1) {
					return false;
				}
				return true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}

		JTextField bufferSize;
		JTextField latency;
		JComboBox tabPlacement;
		BooleanComboBox confirmDelete;
		BooleanComboBox confirmDeleteAll;
		BooleanComboBox autoScroll;
		JTextField editor;
		JTextField tabSize;
		JTextField recentFilesMax;
		CfgFontSelectionPanel fontSelectionPanel;
		JButton save;
		JButton restoreDefaults;
		JButton close;
	}

	private class TabPlacementValue {
		public TabPlacementValue(int value) {
			this.value = value;
			switch (value) {
			case JTabbedPane.TOP:
				displayValue = WatchMeMain
						.getResourceString("dialog.Configure.tabPlacement.Top.displayValue");
				break;
			case JTabbedPane.BOTTOM:
				displayValue = WatchMeMain
						.getResourceString("dialog.Configure.tabPlacement.Bottom.displayValue");
				break;
			case JTabbedPane.LEFT:
				displayValue = WatchMeMain
						.getResourceString("dialog.Configure.tabPlacement.Left.displayValue");
				break;
			case JTabbedPane.RIGHT:
				displayValue = WatchMeMain
						.getResourceString("dialog.Configure.tabPlacement.Right.displayValue");
				break;
			default:
				throw new IllegalArgumentException(
						"int value must be one of the tab placement values from JTabbedPane");
			}
		}

		public int value;

		public String displayValue;

		public String toString() {
			return displayValue;
		}

		public boolean equals(Object o) {
			if (o != null && o.getClass() == getClass()) {
				return value == ((TabPlacementValue) o).value;
			}
			return false;
		}
	}

	private class CfgFontSelectionPanel extends FontSelectionPanel {
		CfgFontSelectionPanel() {
			super(Configure.this.getApp().getAttributes().getFont(),
					getStyleDisplayValues(), new int[] { 8, 9, 10, 12, 14, 16,
							18, 20, 22, 24, 26, 28, 36 });
			this.fontFamilyList.setVisibleRowCount(5);
		}
	}

	private static String[] getStyleDisplayValues() {
		return new String[] {
				WatchMeMain
						.getResourceString("dialog.Configure.font.plain.displayValue"),
				WatchMeMain
						.getResourceString("dialog.Configure.font.bold.displayValue"),
				WatchMeMain
						.getResourceString("dialog.Configure.font.italic.displayValue"),
				WatchMeMain
						.getResourceString("dialog.Configure.font.boldItalic.displayValue") };
	}

	private TabPlacementValue TOP = new TabPlacementValue(JTabbedPane.TOP);

	private TabPlacementValue BOTTOM = new TabPlacementValue(JTabbedPane.BOTTOM);

	private TabPlacementValue LEFT = new TabPlacementValue(JTabbedPane.LEFT);

	private TabPlacementValue RIGHT = new TabPlacementValue(JTabbedPane.RIGHT);

	private TabPlacementValue[] ALL_TAB_PLACEMENT_VALUES = new TabPlacementValue[] {
			TOP, BOTTOM, LEFT, RIGHT };

	static class BooleanComboBox extends JComboBox {
		BooleanComboBox(String trueDisplayValue, String falseDisplayValue) {
			super(new String[] { trueDisplayValue, falseDisplayValue });
		}

		public void setValue(boolean value) {
			if (value == true) {
				this.setSelectedIndex(0);
			} else {
				this.setSelectedIndex(1);
			}
		}

		public boolean getValue() {
			return (this.getSelectedIndex() == 0);
		}
	}
}