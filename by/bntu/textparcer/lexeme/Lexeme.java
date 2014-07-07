package by.bntu.textparcer.lexeme;

import java.util.logging.Logger;

import by.bntu.textparcer.model.TextComponent;
import by.bntu.textparcer.lexeme.LexemeView;

public class Lexeme implements TextComponent {
	 private static final Logger LOG = Logger.getAnonymousLogger();
	private LexemeView view = LexemeView.FAMYLESS;
	private String textOfTextComponent;

	public Lexeme() {
	}

	public Lexeme(LexemeView view) {
		if (view != null) {
			this.view = view;
		} else {
			LOG.warning("LexemeView can not be empty");
		}
	}

	public String getTextOfTextComponent() {
		return (textOfTextComponent != null) ? textOfTextComponent.trim() : "";
	}

	public void setTextOfTextComponent(String textOfTextComponent) {
		if (textOfTextComponent != null) {
			this.textOfTextComponent = textOfTextComponent;
		} else {
			LOG.warning("Text of textComponent can not be empty");
		}
	}

	public LexemeView getView() {
		return view;
	}

	public void setView(LexemeView view) {
		if (view != null) {
			this.view = view;
		} else {
			LOG.warning("LexemeView can not be empty");
		}
	}

	public String toString() {
		return (textOfTextComponent != null) ? textOfTextComponent : "";
	}

}