package hubin.watchme.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Action which closes the currently followed file.
 */
public class ExternalEditor extends Object {
	private Logger log = Logger.getLogger(ExternalEditor.class.getName());
	private String cmdString = "";

	public ExternalEditor(String string) {
		cmdString = string;
	}

	protected String getCmdString() {
		return (cmdString);
	}

	public String[] toCmdArray(String file) {
		String string = (getCmdString() == null) ? "" : getCmdString().trim();
		String[] result = new String[0]; // Pessimistic.

		if (!string.equals("")) {
			string = string + " "; // space terminate the last part.
			List<String> list = new ArrayList<String>();
			boolean inQuoteSingle = false;
			boolean inQuoteDouble = false;
			boolean inWhitespace = false;
			StringBuffer buffer = new StringBuffer();
			char[] chArray = string.toCharArray();
			for (int i = 0; i < chArray.length; i++) {
				char ch = chArray[i];
				if (inQuoteSingle) {
					if (ch == '\'') {
						inQuoteSingle = false;
					} else {
						buffer.append(ch);
					}
				} else if (inQuoteDouble) {
					if (ch == '"') {
						inQuoteDouble = false;
					} else {
						buffer.append(ch);
					}
				} else if (inWhitespace) {
					if (!Character.isWhitespace(ch)) {
						inWhitespace = false;
						--i; // Re-process this character.
					}
				} else {
					if (ch == '\'') {
						inQuoteSingle = true;
					} else if (ch == '"') {
						inQuoteDouble = true;
					} else if (Character.isWhitespace(ch)) {
						inWhitespace = true;
						list.add(buffer.toString());
						buffer = new StringBuffer();
					} else {
						buffer.append(ch);
					}
				}
			}

			list.add(file);

			result = (String[]) list.toArray(result);
		}

		return (result);
	}

	// ------------------------------------------------------------
	// - Public API

	public void exec(File file) throws Exception {
		String fullPath = file.getAbsolutePath();
		String[] cmd = toCmdArray(fullPath);
		log.info("Exec'ing " + Arrays.asList(cmd) + ".");

		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException ioe) {
			String errmsg = "Could not exec [" + getCmdString() + "] with ["
					+ fullPath + "].";
			log.log(Level.SEVERE, errmsg, ioe);
			throw new Exception("Could not exec the external editor, check it!");
		}
	}
}