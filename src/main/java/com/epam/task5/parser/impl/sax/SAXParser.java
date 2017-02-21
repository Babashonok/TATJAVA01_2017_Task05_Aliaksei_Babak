package com.epam.task5.parser.impl.sax;

import com.epam.task5.bean.Item;
import com.epam.task5.parser.Parsable;
import com.epam.task5.parser.data.ConstantStorage;
import com.epam.task5.parser.exception.ParserException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.LinkedList;



public class SAXParser implements Parsable {
    /**
     * read .xml file according class-name specification
     *
     * @param filePath absolute path to the reading .xml file
     * @return whole menu of Item-bean entities
     */
    public LinkedList<Item> getMenu(String filePath) throws ParserException {
        LinkedList<Item> menu;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SAXParserHandler handler = new SAXParserHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(filePath));
            menu = handler.getMenu();
        } catch (IOException | SAXException e) {
            throw new ParserException(ConstantStorage.ERROR_MESSAGE);
        }
        return menu;
    }
}

