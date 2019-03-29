package com.ezgroceries.shoppinglist.clients;

import com.ezgroceries.shoppinglist.domains.Cocktail;
import com.ezgroceries.shoppinglist.domains.CocktailDBResponse;
import com.ezgroceries.shoppinglist.repositories.CocktailRepository;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
@FeignClient(name = "cocktailDBCLient", url = "https://www.thecocktaildb.com/api/json/v1/1/", fallback = CocktailDBClient.CocktailDBClientFallback.class)
@Profile("!Test")
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

    @AllArgsConstructor
    @Component
    class CocktailDBClientFallback implements CocktailDBClient {
        private final CocktailRepository cocktailRepository;

        @Override
        public CocktailDBResponse searchCocktails(String search) {
            List<Cocktail> cocktailEntities = cocktailRepository.findByNameContainingIgnoreCase(search);

            CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
            cocktailDBResponse.setDrinks(cocktailEntities.stream().map(cocktail -> {
                CocktailDBResponse.DrinkResource drinkResource = new CocktailDBResponse.DrinkResource();
                drinkResource.setIdDrink(cocktail.getIdDrink());
                drinkResource.setStrDrink(cocktail.getName());
                drinkResource.setStrGlass(cocktail.getGlass());
                drinkResource.setStrDrinkThumb(cocktail.getImage());
                drinkResource.setStrInstructions(cocktail.getInstructions());
                return drinkResource;
            }).collect(Collectors.toList()));

            return cocktailDBResponse;
        }
    }
}
