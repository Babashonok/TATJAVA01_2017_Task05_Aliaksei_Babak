package com.epam.task5.main;

import com.epam.task5.bean.Item;
import com.epam.task5.parser.Parsable;
import com.epam.task5.parser.exception.ParserException;
import com.epam.task5.parser.factory.ParserFactory;
;

import java.io.File;
import java.util.LinkedList;

public class Main {
    public static void main(String [] args) {
        String filePath = System.getProperty("user.dir") + File.separator + "menu.xml";
        try {
            ParserFactory parserFactory = ParserFactory.getInstance();
            //Parsable parser = parserFactory.getStaxParser();
            //Parsable parser = parserFactory.getSaxParser();
            Parsable parser = parserFactory.getDomParser();
            LinkedList<Item> menu = parser.getMenu(filePath);
            InfoPrinter.printInfo(menu);
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }
    }


}
