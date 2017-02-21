package com.epam.task5.parser.factory;

import com.epam.task5.parser.Parsable;
import com.epam.task5.parser.impl.dom.MenuDOMParser;
import com.epam.task5.parser.impl.sax.SAXParser;
import com.epam.task5.parser.impl.stax.STAXParser;

public class ParserFactory {
    private static ParserFactory instance;

    private final Parsable domParser = new MenuDOMParser();
    private final Parsable saxParser = new SAXParser();
    private final Parsable staxParser = new STAXParser();

    private ParserFactory() {}

    public static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }
        return instance;
    }

    public Parsable getDomParser() {
        return domParser;
    }

    public Parsable getSaxParser() {
        return saxParser;
    }

    public Parsable getStaxParser() {
        return staxParser;
    }


}
