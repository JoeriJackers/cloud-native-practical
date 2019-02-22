package com.ezgroceries.shoppinglist.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CocktailDBResponse {
    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

    public List<CocktailResource> getCocktails() {
        List<CocktailResource> cocktails = new ArrayList<CocktailResource>();

        for (DrinkResource drink : drinks) {
            CocktailResource cocktail = drink.mapToCocktail();
            cocktails.add(cocktail);
        }
        return cocktails;
    }

    public static class DrinkResource {
        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;

        public void setIdDrink(String idDrink) {
            this.idDrink = idDrink;
        }

        public String getIdDrink() {
            return idDrink;
        }

        public void setStrDrink(String strDrink) {
            this.strDrink = strDrink;
        }

        public void setStrDrinkThumb(String strDrinkThumb) {
            this.strDrinkThumb = strDrinkThumb;
        }

        public void setStrGlass(String strGlass) {
            this.strGlass = strGlass;
        }

        public void setStrIngredient1(String strIngredient1) {
            this.strIngredient1 = strIngredient1;
        }

        public void setStrIngredient2(String strIngredient2) {
            this.strIngredient2 = strIngredient2;
        }

        public void setStrIngredient3(String strIngredient3) {
            this.strIngredient3 = strIngredient3;
        }

        public void setStrInstructions(String strInstructions) {
            this.strInstructions = strInstructions;
        }

        public String getStrDrink() {
            return strDrink;
        }

        public String getStrDrinkThumb() {
            return strDrinkThumb;
        }

        public String getStrGlass() {
            return strGlass;
        }

        public String getStrIngredient1() {
            return strIngredient1;
        }

        public String getStrIngredient2() {
            return strIngredient2;
        }

        public String getStrIngredient3() {
            return strIngredient3;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public CocktailResource mapToCocktail() {
            return new CocktailResource(idDrink, strDrink, strGlass, strInstructions, strDrinkThumb, Arrays.asList(strIngredient1, strIngredient2, strIngredient3));
        }
    }
}
