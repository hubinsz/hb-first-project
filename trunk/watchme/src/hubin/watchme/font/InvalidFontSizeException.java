package hubin.watchme.font;

/**
 * Indicates that an invalid font size is currently specified
 */
@SuppressWarnings("serial")
public class InvalidFontSizeException extends InvalidFontException {
	public InvalidFontSizeException(String msg) {
		super(msg);
	}
}