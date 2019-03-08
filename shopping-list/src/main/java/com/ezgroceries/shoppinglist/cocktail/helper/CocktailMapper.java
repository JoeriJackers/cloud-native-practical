package com.ezgroceries.shoppinglist.cocktail.helper;

import com.ezgroceries.shoppinglist.cocktail.Cocktail;
import com.ezgroceries.shoppinglist.cocktail.CocktailResource;

import java.util.ArrayList;
import java.util.UUID;

public class CocktailMapper {
    public static CocktailResource mapCocktailToCocktailResource(Cocktail cocktail) {
        return new CocktailResource(
                cocktail.getIdDrink(),
                cocktail.getName(),
                "",
                "",
                "",
                new ArrayList<String>()
        );
    }

}
