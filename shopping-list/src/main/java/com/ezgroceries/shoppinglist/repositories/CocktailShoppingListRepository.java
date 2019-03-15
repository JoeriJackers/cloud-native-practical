package com.ezgroceries.shoppinglist.repositories;

import com.ezgroceries.shoppinglist.domains.CocktailShoppingList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CocktailShoppingListRepository extends CrudRepository<CocktailShoppingList, UUID> {
    List<CocktailShoppingList> findCocktailShoppingListsByShoppingListId(UUID shoppingListId);
}
