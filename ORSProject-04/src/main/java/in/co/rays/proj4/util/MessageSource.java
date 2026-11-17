
package in.co.rays.proj4.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * MessageSource class is used to manage application messages in different
 * languages using ResourceBundle. It supports English and Hindi languages.
 *
 * @author karunaChouhan
 * @version 1.0
 */
public final class MessageSource {

	public static String ENGLISH_LANG_CODE = "en";
	public static String HINDI_LANG_CODE = "hi";
	public static String DEFAULT_LANGUAGE = ENGLISH_LANG_CODE;

	private static MessageSource ms = null;

	private ResourceBundle rb = null;

	/**
	 * Private constructor initializes MessageSource using the default language.
	 */
	private MessageSource() {
		this(DEFAULT_LANGUAGE);
	}

	/**
	 * Private constructor initializes ResourceBundle for the specified language.
	 *
	 * @param lang language code
	 */
	private MessageSource(String lang) {
		rb = ResourceBundle.getBundle("message", new Locale(lang));
	}

	/**
	 * Private constructor initializes MessageSource using the language of the
	 * specified locale.
	 *
	 * @param locale Locale object containing language information
	 */
	private MessageSource(Locale locale) {
		this(locale.getLanguage());
	}

	/**
	 * Returns the MessageSource singleton instance using the default language.
	 *
	 * @return MessageSource singleton object
	 */
	public static MessageSource getInstance() {
		return getInstance(DEFAULT_LANGUAGE);
	}

	/**
	 * Returns the MessageSource singleton instance for the specified language code.
	 *
	 * @param languageCode language code such as en or hi
	 * @return MessageSource singleton object
	 */
	public static MessageSource getInstance(String languageCode) {
		if (ms == null) {
			ms = new MessageSource(languageCode);
		}
		return ms;
	}

	/**
	 * Changes the current language of the message resource bundle.
	 *
	 * @param lang language code
	 */
	public void setLocale(String lang) {
		rb = ResourceBundle.getBundle("message", new Locale(lang));
	}

	/**
	 * Returns the current language code.
	 *
	 * @return current language code
	 */
	public String getLanguage() {
		return rb.getLocale().getLanguage();
	}

	/**
	 * Retrieves a message from the resource bundle using its key.
	 *
	 * @param key message key
	 * @return message associated with the specified key
	 */
	public String get(String key) {
		String val = "";
		try {
			val = rb.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	/**
	 * Main method to test the MessageSource class.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		MessageSource ms = MessageSource.getInstance();
		// ms.setLocale("en");
		String val = ms.get("login.userid");
		System.out.println("-->" + val);
	}
}
