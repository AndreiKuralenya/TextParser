package by.bntu.textparcer;

import java.awt.Component;
import java.awt.Composite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.bntu.textparcer.exceptions.LogicException;
import by.bntu.textparcer.lexeme.Lexeme;
import by.bntu.textparcer.lexeme.LexemeView;
import by.bntu.textparcer.model.TextComposite;

public class TestParser {
	private static final Logger LOG = Logger.getAnonymousLogger();

	private static final String VOWELS = "aeiouyAEIOUY";

	private static String letterPattern = "\\w";

	public static ArrayList<String> sortWordsByProportionOfVowels(TextComposite text) throws LogicException {
    if (text == null) {
        throw new LogicException("Text can not be null.");
    }
    ArrayList<String> words = getWordsFromTextComposite(text);
    Comparator<String> comparator = (String s1, String s2) -> Double.compare(propVowels(s1), propVowels(s2));
    Collections.sort(words, comparator);
    return words;
}

	private static double propVowels(String word) {
		int countOfVowels = 0;
		int countOfLetters = 0;
		for (int i = 0; i < word.length(); i++) {
			if (VOWELS.indexOf(word.charAt(i)) != -1) {
				countOfVowels++;
			}
		}
		Pattern pattern = Pattern.compile(letterPattern);
		Matcher mat = pattern.matcher(word);
		while (mat.find()) {
			countOfLetters++;
		}
		return (double) countOfVowels / countOfLetters;
	}

	private static ArrayList<String> getWordsFromComposite(Composite composite) {
		ArrayList<String> allWords = new ArrayList<>();
		Iterator<Component> itBook = composite.iterator();
		while (itBook.hasNext()) {
			// абзацы + код
			Component compBook = itBook.next();
			if (compBook.getClass() == Composite.class) {
				Iterator<Component> itPar = ((Composite) compBook).iterator();
				while (itPar.hasNext()) {
					// предложения
					Component compSentence = itPar.next();
					if (compSentence.getClass() == Composite.class) {
						Iterator<Component> itSent = ((Composite) compSentence)
								.iterator();
						while (itSent.hasNext()) {
							// лексемы
							Component compLexeme = itSent.next();
							if ((compLexeme.getClass() == Lexeme.class)
									&& (((Lexeme) compLexeme).getType() == LexemeView.WORD)) {
								allWords.add(((Lexeme) compLexeme)
										.getTextOfTextComponent());
							}
						}
					}
				}
			}
		}
		return allWords;
	}

}