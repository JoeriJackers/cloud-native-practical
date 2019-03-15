package com.ezgroceries.shoppinglist.domains;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "cocktailDBCLient", url = "https://www.thecocktaildb.com/api/json/v1/1/")
@Profile("!Test")
public interface CocktailDBClient {
    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);
}
