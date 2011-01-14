package hubin.watchme.io;

import hubin.watchme.search.SearchEngine;

import javax.swing.text.JTextComponent;

/**
 * An {@link OutputDestination} that filters what is shown.
 */
public class FilteredDestination extends JTextComponentDestination {
	public FilteredDestination(JTextComponent jTextArea, SearchEngine se, String filterTerm,
			boolean autoPositionCaret) {
		super(jTextArea, autoPositionCaret);
	}

	public void print(String s) {
		// print only if s contains filterTerm
		super.print(s);
	}
}