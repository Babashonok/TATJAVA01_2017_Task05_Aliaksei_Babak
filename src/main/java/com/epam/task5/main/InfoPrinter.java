package com.epam.task5.main;


import com.epam.task5.bean.Item;

import java.util.LinkedList;

class InfoPrinter {
    static void printInfo(LinkedList <Item> menu) {
        for (Item item : menu) {
            StringBuilder builder = new StringBuilder();
            builder.append("id: ").append(item.getId());
            if (item.getPhoto() != null) {
                builder.append("\nphoto: ").append(item.getPhoto());
            }
            if (item.getName() != null) {
                builder.append("\nname: ").append(item.getName());
            }
            if (item.getDescription() != null) {
                builder.append("\ndescription: ").append(item.getDescription());
            }
            if(item.getPortion() != null) {
                builder.append("\nportion: ").append(item.getPortion());
            }
            if (item.getPrice() != null) {
                builder.append("\nprice: ").append(item.getPrice());
            }
            System.out.println(builder.toString() + "\n");
        }
    }
}
