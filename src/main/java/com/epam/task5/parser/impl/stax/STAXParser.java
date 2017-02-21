package com.epam.task5.parser.impl.stax;

import com.epam.task5.bean.Item;
import com.epam.task5.parser.Parsable;
import com.epam.task5.parser.data.ConstantStorage;
import com.epam.task5.parser.exception.ParserException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;


public class STAXParser implements Parsable {
    /**
     * read .xml file according class-name specification
     *
     * @param filePath absolute path to the reading .xml file
     * @return whole menu of Item-bean entities
     */
    public LinkedList<Item> getMenu(String filePath) throws ParserException {
        LinkedList<Item> menu = new LinkedList<>();
        try {
            InputStream input = new FileInputStream(filePath);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(input);
            String tagName = null;
            Item item = null;
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        tagName = reader.getLocalName().trim();
                        if (ConstantStorage.ITEM_TAG.equals(tagName)) {
                            item = new Item();
                            String id = reader.getAttributeValue(null, ConstantStorage.ID_ATTR);
                            item.setId(id);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (text.isEmpty()) {
                            break;
                        }
                        switch (tagName) {
                            case ConstantStorage.PHOTO_TAG:
                                item.setPhoto(text);
                                break;
                            case ConstantStorage.NAME_TAG:
                                item.setName(text);
                                break;
                            case ConstantStorage.DESCRIPTION_TAG:
                                item.setDescription(text);
                                break;
                            case ConstantStorage.PORTION_TAG:
                                item.setPortion(text);
                                break;
                            case ConstantStorage.PRICE_TAG:
                                item.setPrice(text);
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        tagName = reader.getLocalName();
                        if (ConstantStorage.ITEM_TAG.equals(tagName)) {
                            menu.add(item);
                        }
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ParserException(ConstantStorage.ERROR_MESSAGE);
        }
        return menu;
    }
}
