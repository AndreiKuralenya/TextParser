package by.bntu.textparcer.parser;

import java.util.List;

import by.bntu.textparcer.model.TextComponent;

public abstract class ThisParser {

    public ThisParser nextThisParser;

    public abstract List<? extends TextComponent> parse(String text);

    public ThisParser getNextThisParser() {
        return nextThisParser;
    }

    public void setNextThisParser(ThisParser nextThisParser) {
        this.nextThisParser = nextThisParser;
    }

}