package com.ezgroceries.shoppinglist.shoppingList;

import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {
    public ShoppingList findByName(String name);
}
