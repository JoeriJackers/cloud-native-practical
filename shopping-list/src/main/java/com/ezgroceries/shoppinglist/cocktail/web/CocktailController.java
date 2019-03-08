package com.ezgroceries.shoppinglist.cocktail.web;

import com.ezgroceries.shoppinglist.cocktail.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {
    CocktailDBClient cocktailDBClient;
    CocktailService cocktailService;

    public CocktailController(CocktailDBClient cocktailDBClient, CocktailService cocktailService) {
        this.cocktailDBClient = cocktailDBClient;
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public @ResponseBody
    List<CocktailResource> get(@RequestParam String search) {
        CocktailDBResponse resources = cocktailDBClient.searchCocktails(search);
        cocktailService.mergeCocktails(resources.getCocktails());
        return cocktailService.findAll();
    }
}
