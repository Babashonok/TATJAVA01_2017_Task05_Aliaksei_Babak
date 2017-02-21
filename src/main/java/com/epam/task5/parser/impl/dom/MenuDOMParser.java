package com.epam.task5.parser.impl.dom;

import com.epam.task5.bean.Item;
import com.epam.task5.parser.Parsable;
import com.epam.task5.parser.data.ConstantStorage;
import com.epam.task5.parser.exception.ParserException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.LinkedList;


public class MenuDOMParser implements Parsable {
    /**
     * read .xml file according class-name specification
     *
     * @param filePath absolute path to the reading .xml file
     * @return whole menu of Item-bean entities
     */
    public LinkedList<Item> getMenu(String filePath) throws ParserException {
        LinkedList<Item> menu = new LinkedList<>();
        try {
            DOMParser parser = new DOMParser();
            parser.parse(filePath);
            Document doc = parser.getDocument();
            Element root = doc.getDocumentElement();
            NodeList nodes = root.getElementsByTagName(ConstantStorage.ITEM_TAG);
            for (int i = 0; i < nodes.getLength(); ++i) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    menu.add(newMenuItem(element));
                }

            }
        } catch (IOException | SAXException e) {
            throw new ParserException(ConstantStorage.ERROR_MESSAGE);
        }
        return menu;
    }

    private Item newMenuItem(Element element) {
        Item item = new Item();
        if (element.hasAttribute(ConstantStorage.ID_ATTR)) {
            item.setId(element.getAttribute(ConstantStorage.ID_ATTR));
        }
        if (element.getElementsByTagName(ConstantStorage.PHOTO_TAG).getLength() > 0) {
            item.setPhoto(element.getElementsByTagName(ConstantStorage.PHOTO_TAG).item(0).getTextContent());
        }
        if (element.getElementsByTagName(ConstantStorage.NAME_TAG).getLength() > 0) {
            item.setName(element.getElementsByTagName(ConstantStorage.NAME_TAG).item(0).getTextContent());
        }
        if (element.getElementsByTagName(ConstantStorage.DESCRIPTION_TAG).getLength() > 0) {
            item.setDescription(element.getElementsByTagName(ConstantStorage.DESCRIPTION_TAG).item(0).getTextContent());
        }
        if (element.getElementsByTagName(ConstantStorage.PORTION_TAG).getLength() > 0) {
            item.setPortion(element.getElementsByTagName(ConstantStorage.PORTION_TAG).item(0).getTextContent());
        }
        if (element.getElementsByTagName(ConstantStorage.PRICE_TAG).getLength() > 0) {
            item.setPrice(element.getElementsByTagName(ConstantStorage.PRICE_TAG).item(0).getTextContent());
        }
        return item;
    }


}