package by.bntu.textparcer.parser;

import java.awt.Component;
import java.awt.Composite;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.bntu.textparcer.lexeme.Lexeme;
import by.bntu.textparcer.lexeme.LexemeView;
import by.bntu.textparcer.model.TextComponent;
import by.bntu.textparcer.model.TextComposite;

public class BookParser extends ThisParser {
	public List<? extends TextComponent> parse(String text) {
		ArrayList<Component> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder(text);
		Pattern pattern = Pattern.compile(ResourceManager.getInstance()
				.getString(ResourceManager.SYMBOL));
		Matcher mat = pattern.matcher(sb.toString());
		while (mat.find()) {
			if (getNextThisParser() != null) {
				TextComposite paragraph = new TextComposite();
				String stringParagraph = sb.substring(0, mat.start());
				paragraph.addAll(getNextThisParser().parse(stringParagraph));
				result.addAll(paragraph);
			} else {
				Lexeme famylessLexeme = new Lexeme();
				String stringParagraph = sb.substring(0, mat.start());
				famylessLexeme.setTextOfTextComponent(stringParagraph);
				result.addAll(famylessLexeme);
			}
			Lexeme symbol = new Lexeme(LexemeView.SYMBOL);
			symbol.setTextOfTextComponent(mat.group());
			result.addAll(symbol);

			sb.delete(0, mat.end());
			mat = pattern.matcher(sb.toString());
		}
		TextComposite paragraph = new TextComposite();
		String stringParagraph = sb.toString();
		paragraph.addAll(getNextThisParser().parse(stringParagraph));
		result.addAll(paragraph);
		return result;
	}
}