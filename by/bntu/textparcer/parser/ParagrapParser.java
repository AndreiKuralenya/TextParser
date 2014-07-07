package by.bntu.textparcer.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.bntu.textparcer.lexeme.Lexeme;
import by.bntu.textparcer.model.TextComponent;
import by.bntu.textparcer.model.TextComposite;

public class ParagrapParser extends ThisParser {

	public List<? extends TextComponent> parse(String text) {
		ArrayList<TextComponent> result = new ArrayList<>();
		Pattern patternSentence = Pattern.compile(ResourceManager.getInstance()
				.getString(ResourceManager.SENTENCE));
		Matcher mat = patternSentence.matcher(text);
		while (mat.find()) {
			if (getNextThisParser() != null) {
				TextComposite sentence = new TextComposite();
				sentence.addAll(getNextThisParser().parse(mat.group()));
				result.add(sentence);
			} else {
				Lexeme famylessLexeme = new Lexeme();
				famylessLexeme.setTextOfTextComponent(mat.group());
				result.add(famylessLexeme);
			}
		}
		return result;
	}
}