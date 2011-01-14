package hubin.watchme.font;

/** Indicates that no font style is currently selected */
@SuppressWarnings("serial")
public class NoFontStyleSelectedException extends InvalidFontException {
	public NoFontStyleSelectedException(String msg) {
		super(msg);
	}
}