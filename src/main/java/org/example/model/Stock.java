package org.example.model;

import java.util.*;

public class Stock {

    private final Set<Item> items;

    public Stock() {
        this.items = new HashSet<>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new NoSuchElementException("There is no such element.");
    }

    public List<Item> getItemsAsList() {
        return new ArrayList<>(items);
    }
}
