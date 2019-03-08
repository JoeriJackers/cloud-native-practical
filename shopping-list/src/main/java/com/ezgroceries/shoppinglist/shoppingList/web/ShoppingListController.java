package com.ezgroceries.shoppinglist.shoppingList.web;

import com.ezgroceries.shoppinglist.cocktail.CocktailResource;
import com.ezgroceries.shoppinglist.shoppingList.ShoppingListResource;
import com.ezgroceries.shoppinglist.shoppingList.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/shopping-lists")
public class ShoppingListController {
    ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

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
//        return new ShoppingListResource(
//                 UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f"),
//                "Stephanie's Birthday",
//                 Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Blue Curacao")
//        );
    }

    @PostMapping(value = "/{listId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CocktailResource> createSimpleCocktail(@PathVariable String listId, @RequestBody List<CocktailResource> cocktails) {
        return cocktails;
    }
}
