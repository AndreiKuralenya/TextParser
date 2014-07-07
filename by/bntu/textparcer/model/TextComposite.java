package by.bntu.textparcer.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

public class TextComposite implements TextComponent {
	private static final Logger LOG = Logger.getAnonymousLogger();
	public ArrayList<TextComponent> textComponents = new ArrayList<>();
	private int TextComponent;

	public TextComposite() {
	}

	public void add(TextComponent textComponent) {
		if (textComponent != null) {
			textComponents.add(textComponent);
		} else {
			LOG.warning("TextComponent can not be empty");
		}
	}

	public void remove(TextComponent textComponent) {
		if (textComponent != null) {
			if (textComponents.remove(textComponent)) {
				LOG.info("TextComponent " + textComponent + " removed");
			} else {
				LOG.info("TextComponent " + textComponent + " didn't remove");
			}
		} else {
			LOG.warning("TextComponent can not be be empty");
		}
	}

	public boolean addAll(Collection<? extends TextComponent> textComponents) {
		return this.textComponents.addAll(textComponents);
	}

	public Iterator<TextComponent> iterator() {
		return textComponents.iterator();
	}
}
/*
 * public String toString() { StringBuilder result = new StringBuilder();
 * textComponents.forEach((TextComponent value) ->
 * result.append(value.toString())); return result.toString(); }
 * 
 * }
 */