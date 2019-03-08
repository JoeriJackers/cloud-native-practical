package com.ezgroceries.shoppinglist.shoppingList;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, UUID> {
    public ShoppingList findByName(String name);
}
