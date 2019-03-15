package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.domains.CocktailResource;
import com.ezgroceries.shoppinglist.domains.CocktailShoppingList;
import com.ezgroceries.shoppinglist.repositories.CocktailShoppingListRepository;
import com.ezgroceries.shoppinglist.domains.ShoppingListResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CocktailShoppingListService {
    private final CocktailShoppingListRepository cocktailShoppingListRepository;
    private final CocktailService cocktailService;
    private final ShoppingListService shoppingListService;

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
