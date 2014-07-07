package by.bntu.textparcer.model;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

class TextScan {
	private static final Logger LOG = Logger.getAnonymousLogger();
	public static final String FILE_PATH = "d://java/text.txt";

	public static void main(String[] args) {
		try {
			Scanner i = new Scanner(new File(FILE_PATH));
			StringBuffer data = new StringBuffer();
			while (i.hasNext())
				data.append(i.nextLine()).append("\n");
			LOG.info(data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}