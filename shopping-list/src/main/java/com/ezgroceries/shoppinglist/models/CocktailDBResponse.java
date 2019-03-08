package com.ezgroceries.shoppinglist.models;

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

    public static class DrinkResource {
        @Getter @Setter private String idDrink;
        @Getter @Setter private String strDrink;
        @Getter @Setter private String strGlass;
        @Getter @Setter private String strInstructions;
        @Getter @Setter private String strDrinkThumb;
        @Getter @Setter private String strIngredient1;
        @Getter @Setter private String strIngredient2;
        @Getter @Setter private String strIngredient3;
        @Getter @Setter private String strIngredient4;

        public CocktailResource mapToCocktail() {
            return new CocktailResource(idDrink, strDrink, strGlass, strInstructions, strDrinkThumb, Arrays.asList(strIngredient1, strIngredient2, strIngredient3, strIngredient4));
        }
    }
}
