package com.ezgroceries.shoppinglist.clients;

import com.ezgroceries.shoppinglist.models.CocktailDBResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "cocktailDBCLient", url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=russian")
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);
}
