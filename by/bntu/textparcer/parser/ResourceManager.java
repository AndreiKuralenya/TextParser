package by.bntu.textparcer.parser;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ResourceManager {
	private static final Logger LOG = Logger.getAnonymousLogger();
	public static final String WORD = "properties.word";
	public static final String SYMBOL = "properties.regex.symbol";
	public static final String SENTENCE = "properties.sentence";
	public static final String PUNCTUATION = "properties.punctuation";
	private static final String resourceName = "res.prop";
	private ResourceBundle resBundle;
	private Lock lock = new ReentrantLock();

	private ResourceManager() {
		resBundle = ResourceBundle.getBundle(resourceName);
	}

	public static ResourceManager getInstance() {
		return LazyResourceManagerHolder.singletonInstance;
	}

	public void changeLocale(Locale locale) {
		if (locale != null) {
			lock.lock();
			resBundle = ResourceBundle.getBundle(resourceName, locale);
			lock.unlock();
		} else {
			LOG.warning("Locale can not be null");
		}

	}

	public String getString(String key) {
		return resBundle.getString(key);
	}

	private static class LazyResourceManagerHolder {
		public static ResourceManager singletonInstance = new ResourceManager();
	}

}