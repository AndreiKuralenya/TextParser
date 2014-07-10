package by.bntu.textparcer;
import by.bntu.textparcer.exceptions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public class TextFile {

    private static final Logger LOG = Logger.getAnonymousLogger();

    private TextFile() {
    }

    public static String getTextFromFile(String fileName) throws TechnicalException {
        StringBuilder result = new StringBuilder();
        File file = new File(fileName);
        try (BufferedReader inputReader = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            String line;
            while (null != (line = inputReader.readLine())) {
                result.append(line);
                result.append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new TechnicalException("File " + fileName + " not found", e);
        } catch (IOException e) {
            throw new TechnicalException("IOException file worker", e);
        }
        return result.toString();
    }

    public static void putTextToFile(String text, String fileName) throws TechnicalException {
        PrintWriter out = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new PrintWriter(file.getAbsoluteFile());
            out.print(text);
        } catch (FileNotFoundException e) {
            throw new TechnicalException("File " + fileName + " not found", e);
        } catch (IOException e) {
            throw new TechnicalException("IOException in putTextToFile", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void putTextToFile(List list, String fileName) throws TechnicalException {
        StringBuilder text = new StringBuilder();
        for (Object obj : list) {
            text.append(obj).append("\n");
        }
        putTextToFile(text.toString(), fileName);
    }

}