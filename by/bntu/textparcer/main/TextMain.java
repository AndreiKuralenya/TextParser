package by.bntu.textparcer.main;

import java.io.File;
import java.util.logging.Logger;

import by.bntu.textparcer.TestParser;
import by.bntu.textparcer.TextFile;
import by.bntu.textparcer.model.TextComposite;
import by.bntu.textparcer.exceptions.LogicException;
import by.bntu.textparcer.exceptions.TechnicalException;
import by.bntu.textparcer.parser.BookParser;
import by.bntu.textparcer.parser.ParagrapParser;
import by.bntu.textparcer.parser.SentenceParser;

public class TextMain {
	private static final Logger LOG = Logger.getAnonymousLogger();

	public static void main(String[] args) {
		try {
			String allText = TextFile.getTextFromFile("texts"
					+ File.separator + "inText.txt");
			BookParser bookParser = new BookParser();
			ParagrapParser paragraphParser = new ParagrapParser();
			SentenceParser sentenceParser = new SentenceParser();
			bookParser.setNextThisParser(paragraphParser);
			paragraphParser.setNextThisParser(sentenceParser);
			TextComposite text = new TextComposite();
			text.addAll(bookParser.parse(allText));
			TextFile.putTextToFile(text.toString(), "texts" + File.separator
					+ "outText.txt");
			TextFile.putTextToFile(
					TestParser.sortWordsByProportionOfVowels(text), "texts"
							+ File.separator + "TestFIle.txt");
		} catch (TechnicalException | LogicException e) {
			LOG.error(e);
		}
	}

}