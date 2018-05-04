package com.example.demo.dummy;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

class Invoice {

    @Data
    static public class Item
    {
        String name;

        public Item name(String name){
            this.name = name;
            return this;
        }
    }

    protected List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public void addItem(String name) {
        Item item = new Item();
        item.name = name;

        items.add(item);
    }

    public List<Item> items(){
        return items;
    }
}


class InvoiceChild extends Invoice{

    public void addItem(String name){
        Item item = new Item();
        item.name = name.toUpperCase();

        items.add(item);
    }
}


public class InvoiceTest {

    @Test
    public void create(){

        Invoice invoice = new InvoiceChild();
        invoice.addItem("kevin");
        invoice.addItem("jack");
        invoice.addItem("may");

        invoice.addItem(new Invoice.Item().name("jang"));
        invoice.addItem(new Invoice.Item().name("park"));
        invoice.addItem("me");

        System.out.println(invoice.items());
    }
}
