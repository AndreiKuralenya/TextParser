package by.bntu.textparcer.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.bntu.textparcer.lexeme.Lexeme;
import by.bntu.textparcer.lexeme.LexemeView;
import by.bntu.textparcer.model.TextComponent;

public class SentenceParser extends ThisParser {

	public List<? extends TextComponent> parse(String text) {
		ArrayList<TextComponent> result = new ArrayList<>();
		Pattern patternWord = Pattern.compile(ResourceManager.getInstance()
				.getString(ResourceManager.WORD));
		Pattern patternPunctuation = Pattern.compile(ResourceManager
				.getInstance().getString(ResourceManager.PUNCTUATION));
		Pattern patternSentence = Pattern.compile(ResourceManager.getInstance()
				.getString(ResourceManager.SENTENCE));
		String[] all = text.split("(?= )");
		for (String candidate : all) {
			Matcher m = patternSentence.matcher(candidate);
			Lexeme newPart;
			Matcher testUnknown = patternSentence.matcher(candidate.trim());
			if (((testUnknown.find()) && (testUnknown.start() == 0))) {
				while (m.find()) {
					newPart = new Lexeme();
					newPart.setTextOfTextComponent(m.group());
					if (m.group().matches(patternWord.pattern())) {
						newPart.setView(LexemeView.WORD);
					} else {
						newPart.setView(LexemeView.PUNCTUATION);
					}
					result.add(newPart);
				}

			} else {
				newPart = new Lexeme(LexemeView.FAMYLESS);
				newPart.setTextOfTextComponent(candidate);
				result.add(newPart);
			}
		}
		return result;
	}
}