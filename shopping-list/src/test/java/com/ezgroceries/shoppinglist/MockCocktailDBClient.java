package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.clients.CocktailDBClient;
import com.ezgroceries.shoppinglist.domains.CocktailDBResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile("Test")
public class MockCocktailDBClient implements CocktailDBClient {
    public CocktailDBResponse searchCocktails(String search) {

        CocktailDBResponse.DrinkResource drink1 =  new CocktailDBResponse.DrinkResource();
        drink1.setIdDrink("23b3d85a-3928-41c0-a533-6538a71e17c4");
        drink1.setStrDrink("Margerita");
        drink1.setStrGlass("Cocktail glass");
        drink1.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg");
        drink1.setStrInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
        drink1.setStrIngredient1("Tequila");
        drink1.setStrIngredient2("Triple sec");
        drink1.setStrIngredient3("Lime juice");
        drink1.setStrIngredient4("Salt");

        CocktailDBResponse.DrinkResource drink2 =  new CocktailDBResponse.DrinkResource();
        drink2.setIdDrink("d615ec78-fe93-467b-8d26-5d26d8eab073");
        drink2.setStrDrink("Blue Margerita");
        drink2.setStrGlass("Cocktail glass");
        drink2.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg");
        drink2.setStrInstructions("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..");
        drink2.setStrIngredient1("Tequila");
        drink2.setStrIngredient2("Blue Curacao");
        drink2.setStrIngredient3("Lime juice");
        drink2.setStrIngredient4("Salt");

        CocktailDBResponse fakeResponse = new CocktailDBResponse();
        fakeResponse.setDrinks(Arrays.asList(drink1, drink2));

        return fakeResponse;
    };
}
