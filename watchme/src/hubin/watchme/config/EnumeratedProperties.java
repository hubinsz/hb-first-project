package hubin.watchme.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Extension of {@link java.util.Properties} which allows one to specify
 * property values which are Lists of Strings.
 */
@SuppressWarnings("serial")
public class EnumeratedProperties extends Properties {

	/** Delimiter between property name & list member index */
	protected static char delimiter = '.';

	/**
	 * Returns the List value of the property with the supplied key. Note that
	 * one can call getEnumeratedProperty() for a given key successfully if and
	 * only if setEnumeratedProperty() for that key was called some time
	 * beforehand. All members of the list returned will be Strings.
	 * 
	 * @param key
	 *            lookup of the enumerated property to be retrieved.
	 * @return list containing String values
	 */
	public List<String> getEnumeratedProperty(String key) {
		ArrayList<String> values = new ArrayList<String>();
		int i = 0;
		String value;
		while ((value = this.getProperty(key + delimiter + i++)) != null) {
			values.add(value);
		}
		return values;
	}

	/**
	 * Assigns the supplied array of String values to the supplied key.
	 * 
	 * @param key
	 *            property lookup
	 * @param values
	 *            values to be associated with the property lookup
	 */
	public void setEnumeratedProperty(String key, List<String> values) {
		int i = 0;
		for (; i < values.size(); i++) {
			setProperty(key + delimiter + i, values.get(i));
		}
		while (getProperty(key + delimiter + i) != null) {
			remove(key + delimiter + i);
			i++;
		}
	}

}
