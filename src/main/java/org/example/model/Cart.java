package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
