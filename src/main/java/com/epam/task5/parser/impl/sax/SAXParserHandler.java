package com.epam.task5.parser.impl.sax;

import com.epam.task5.bean.Item;
import com.epam.task5.parser.data.ConstantStorage;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;

class SAXParserHandler extends DefaultHandler {

    private LinkedList<Item> menu = new LinkedList<>();
    private Item item;
    private StringBuilder text;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        text = new StringBuilder();
        if (qName.equals(ConstantStorage.ITEM_TAG)) {
            item = new Item();
            item.setId(atts.getValue(ConstantStorage.ID_ATTR));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        text.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case ConstantStorage.PHOTO_TAG:
                item.setPhoto(text.toString());
                break;
            case ConstantStorage.NAME_TAG:
                item.setName(text.toString());
                break;
            case ConstantStorage.DESCRIPTION_TAG:
                item.setDescription(text.toString());
                break;
            case ConstantStorage.PORTION_TAG:
                item.setPortion(text.toString());
                break;
            case ConstantStorage.PRICE_TAG:
                item.setPrice(text.toString());
                break;
            case ConstantStorage.ITEM_TAG:
                menu.add(item);
                break;
        }
    }

    LinkedList<Item> getMenu() {
        return menu;
    }


}

