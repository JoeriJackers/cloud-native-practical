package com.ezgroceries.shoppinglist.cocktailShoppingList;

import com.ezgroceries.shoppinglist.cocktail.Cocktail;
import com.ezgroceries.shoppinglist.cocktail.CocktailResource;
import com.ezgroceries.shoppinglist.cocktail.CocktailService;
import com.ezgroceries.shoppinglist.shoppingList.ShoppingList;
import com.ezgroceries.shoppinglist.shoppingList.ShoppingListResource;
import com.ezgroceries.shoppinglist.shoppingList.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CocktailShoppingListService {
    private CocktailShoppingListRepository cocktailShoppingListRepository;
    private CocktailService cocktailService;
    private ShoppingListService shoppingListService;

    @Autowired
    public CocktailShoppingListService(CocktailShoppingListRepository cocktailShoppingListRepository, CocktailService cocktailService, ShoppingListService shoppingListService) {
        this.cocktailShoppingListRepository = cocktailShoppingListRepository;
        this.cocktailService = cocktailService;
        this.shoppingListService = shoppingListService;
    }

    public void addCocktailsToShoppingList(String shoppingListId, List<CocktailResource> cocktailResources) {
        UUID shoppingListUUID = UUID.fromString(shoppingListId);
        List<CocktailShoppingList> cocktailShoppingLists = new ArrayList<>();
        List<CocktailResource> cocktails = cocktailResources.stream()
                .map(cocktail -> cocktailService.findByIdDrink(cocktail.getCocktailId()))
                .collect(Collectors.toList());

       cocktails.stream()
                .forEach(cocktail -> cocktailShoppingLists.add(new CocktailShoppingList(shoppingListUUID, UUID.fromString(cocktail.getCocktailId()))));
//
//        Set<String> uniqueIngredients = cocktails.stream()
//                .map(cocktail -> cocktail.getIngredients())
//                .flatMap(Collection::stream)
//                .collect(Collectors.toSet());

//        cocktailResources.forEach(cocktail -> cocktailShoppingLists.add(new CocktailShoppingList(shoppingListUUID, cocktail.getCocktailId())));
        cocktailShoppingListRepository.saveAll(cocktailShoppingLists);
    }
}
