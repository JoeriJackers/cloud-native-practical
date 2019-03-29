package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.clients.CocktailDBClient;
import com.ezgroceries.shoppinglist.domains.CocktailDBResponse;
import com.ezgroceries.shoppinglist.domains.CocktailResource;
import com.ezgroceries.shoppinglist.services.CocktailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    private final CocktailDBClient cocktailDBClient;
    private final CocktailService cocktailService;

    @GetMapping
    public @ResponseBody
    List<CocktailResource> get(@RequestParam(value = "search", required = false) String search) {
        if (search == null || search.isEmpty()) {
            log.debug("No search term specified, retrieving from DB");
            return cocktailService.findAll();
        }
        CocktailDBResponse resources = cocktailDBClient.searchCocktails(search);
        cocktailService.mergeCocktails(resources.getCocktails());
        return resources.getCocktails();
    }
}
