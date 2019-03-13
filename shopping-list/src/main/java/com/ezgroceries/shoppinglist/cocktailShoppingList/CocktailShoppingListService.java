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

    public List<ShoppingListResource> getAllShoppingLists() {
        List<ShoppingListResource> shoppingListResources = shoppingListService.getAllShoppingLists();
        return shoppingListResources.stream()
                .map(list -> getShoppingList(list.getShoppingListId().toString()))
                .collect(Collectors.toList());
    }

    public ShoppingListResource getShoppingList(String shoppingListId) {
        UUID shoppingListUUID = UUID.fromString(shoppingListId);
        ShoppingListResource shoppingListResource = shoppingListService.findById(shoppingListUUID);

        List<CocktailShoppingList> cocktailShoppingLists = cocktailShoppingListRepository.findCocktailShoppingListsByShoppingListId(shoppingListUUID);
        List<List<String>> allIngredients = new ArrayList<>();

        cocktailShoppingLists.stream()
                .forEach(cocktailShoppingList -> allIngredients.add(cocktailService.findById(cocktailShoppingList.getCocktailId()).getIngredients()));

        Set<String> allIngredientsFlat = allIngredients.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        shoppingListResource.setIngredients(new ArrayList<>(allIngredientsFlat));
        return shoppingListResource;
    }

    public void addCocktailsToShoppingList(String shoppingListId, List<CocktailResource> cocktailResources) {
        UUID shoppingListUUID = UUID.fromString(shoppingListId);
        List<CocktailShoppingList> cocktailShoppingLists = new ArrayList<>();
        List<CocktailResource> cocktails = cocktailResources.stream()
                .map(cocktail -> cocktailService.findById(cocktail.getId()))
                .collect(Collectors.toList());

        cocktails.stream()
                .forEach(cocktail -> cocktailShoppingLists.add(new CocktailShoppingList(cocktail.getId(), shoppingListUUID)));

        cocktailShoppingListRepository.saveAll(cocktailShoppingLists);
    }
}
