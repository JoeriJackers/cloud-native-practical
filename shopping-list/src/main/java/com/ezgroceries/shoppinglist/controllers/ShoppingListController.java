package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.domains.CocktailResource;
import com.ezgroceries.shoppinglist.services.CocktailShoppingListService;
import com.ezgroceries.shoppinglist.domains.ShoppingListResource;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/shopping-lists")
public class ShoppingListController {
    private ShoppingListService shoppingListService;
    private CocktailShoppingListService cocktailShoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService, CocktailShoppingListService cocktailShoppingListService) {
        this.shoppingListService = shoppingListService;
        this.cocktailShoppingListService = cocktailShoppingListService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShoppingList(@RequestBody ShoppingListResource shoppingListResource) {
        return shoppingListService.create(shoppingListResource);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingListResource> getAllShoppingLists() {
        return cocktailShoppingListService.getAllShoppingLists();
    }

    @GetMapping(value = "/{listId}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingListResource getShoppingListById(@PathVariable String listId) {
        return cocktailShoppingListService.getShoppingList(listId);
    }

    @PostMapping(value = "/{listId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createSimpleCocktail(@PathVariable String listId, @RequestBody List<CocktailResource> cocktails) {
        cocktailShoppingListService.addCocktailsToShoppingList(listId, cocktails);
        return shoppingListService.findById(UUID.fromString(listId));
    }
}
