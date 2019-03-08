package com.ezgroceries.shoppinglist.cocktail;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CocktailDBResponse {
    @Getter @Setter private List<DrinkResource> drinks;

    public List<CocktailResource> getCocktails() {
        List<CocktailResource> cocktails = new ArrayList<>();

        for (DrinkResource drink : drinks) {
            CocktailResource cocktail = drink.mapToCocktail();
            cocktails.add(cocktail);
        }
        return cocktails;
    }

    @Getter
    @Setter
    public static class DrinkResource {
        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        private String strIngredient4;

        public CocktailResource mapToCocktail() {
            return new CocktailResource(idDrink, strDrink, strGlass, strInstructions, strDrinkThumb, Arrays.asList(strIngredient1, strIngredient2, strIngredient3, strIngredient4));
        }
    }
}
