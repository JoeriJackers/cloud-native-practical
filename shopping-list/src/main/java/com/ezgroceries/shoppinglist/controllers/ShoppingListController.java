package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.domains.CocktailResource;
import com.ezgroceries.shoppinglist.domains.ShoppingListResource;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/shopping-lists")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShoppingList(@RequestBody ShoppingListResource shoppingListResource) {
        return shoppingListService.create(shoppingListResource);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingListResource> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }

    @GetMapping(value = "/{listId}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingListResource getShoppingListById(@PathVariable String listId) {
        return shoppingListService.findById(UUID.fromString(listId));
    }

    @PostMapping(value = "/{listId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createSimpleCocktail(@PathVariable String listId, @RequestBody List<CocktailResource> cocktails) {
        shoppingListService.addCocktailsToShoppingList(listId, cocktails);
        return shoppingListService.findById(UUID.fromString(listId));
    }
}
