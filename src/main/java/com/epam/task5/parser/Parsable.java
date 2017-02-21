package com.epam.task5.parser;

import com.epam.task5.bean.Item;
import com.epam.task5.parser.exception.ParserException;

import java.util.LinkedList;

/**
 * defines all .xml parsers behavior
 */
public interface Parsable {
    /**
     * read .xml file according class-name specification
     * @param filePath absolute path to the reading .xml file
     * @return whole menu of Item-bean entities
     */
    LinkedList<Item> getMenu(String filePath) throws ParserException;
}
