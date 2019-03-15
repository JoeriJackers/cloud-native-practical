package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.domains.CocktailDBClient;
import com.ezgroceries.shoppinglist.domains.CocktailDBResponse;
import com.ezgroceries.shoppinglist.domains.CocktailResource;
import com.ezgroceries.shoppinglist.services.CocktailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {
    private static final Logger logger = LoggerFactory.getLogger(CocktailController.class);
    private final CocktailDBClient cocktailDBClient;
    private final CocktailService cocktailService;

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
