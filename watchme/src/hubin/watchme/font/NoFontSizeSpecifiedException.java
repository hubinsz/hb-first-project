package hubin.watchme.font;

/** Indicates that no font size is currently specified */
@SuppressWarnings("serial")
public class NoFontSizeSpecifiedException extends InvalidFontException {
	public NoFontSizeSpecifiedException(String msg) {
		super(msg);
	}
}