package hubin.watchme.font;

/** Indicates that no font family is currently selected */
@SuppressWarnings("serial")
public class NoFontFamilySelectedException extends InvalidFontException {
	public NoFontFamilySelectedException(String msg) {
		super(msg);
	}
}