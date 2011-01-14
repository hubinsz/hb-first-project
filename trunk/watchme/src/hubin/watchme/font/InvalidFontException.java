package hubin.watchme.font;

/** Indicates that an invalid font is currently specified */
@SuppressWarnings("serial")
public class InvalidFontException extends Exception {
	public InvalidFontException(String msg) {
		super(msg);
	}
}