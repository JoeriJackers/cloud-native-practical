package com.ezgroceries.shoppinglist.cocktailShoppingList;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CocktailShoppingListRepository extends CrudRepository<CocktailShoppingList, UUID> {}
