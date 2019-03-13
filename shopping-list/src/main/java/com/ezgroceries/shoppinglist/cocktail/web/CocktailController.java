package com.ezgroceries.shoppinglist.cocktail.web;

import com.ezgroceries.shoppinglist.cocktail.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {
    static final Logger logger = LoggerFactory.getLogger(CocktailController.class);
    CocktailDBClient cocktailDBClient;
    CocktailService cocktailService;

    public CocktailController(CocktailDBClient cocktailDBClient, CocktailService cocktailService) {
        this.cocktailDBClient = cocktailDBClient;
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public @ResponseBody
    List<CocktailResource> get(@RequestParam(value = "search", required = false) String search) {
        if (search == null || search.isEmpty()) {
            logger.debug("No search term specified, retrieving from DB");
            return cocktailService.findAll();
        }
        CocktailDBResponse resources = cocktailDBClient.searchCocktails(search);
        cocktailService.mergeCocktails(resources.getCocktails());
        return resources.getCocktails();
    }
}
