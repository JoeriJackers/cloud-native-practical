package com.ezgroceries.shoppinglist.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class CocktailDBResponse {

    @Getter @Setter private List<DrinkResource> drinks;

    public List<CocktailResource> getCocktails() {
        if (drinks.isEmpty()) { return Collections.emptyList(); }

        return drinks.stream()
                .map(DrinkResource::mapToCocktail)
                .collect(Collectors.toList());
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
        private String strIngredient5;
        private String strIngredient6;
        private String strIngredient7;
        private String strIngredient8;
        private String strIngredient9;
        private String strIngredient10;
        private String strIngredient11;
        private String strIngredient12;
        private String strIngredient13;
        private String strIngredient14;
        private String strIngredient15;

        public CocktailResource mapToCocktail() {
            List<String> ingredients = new ArrayList<>();
            ingredients.add(strIngredient1);
            ingredients.add(strIngredient2);
            ingredients.add(strIngredient3);
            ingredients.add(strIngredient4);
            ingredients.add(strIngredient5);
            ingredients.add(strIngredient6);
            ingredients.add(strIngredient7);
            ingredients.add(strIngredient8);
            ingredients.add(strIngredient9);
            ingredients.add(strIngredient10);
            ingredients.add(strIngredient11);
            ingredients.add(strIngredient12);
            ingredients.add(strIngredient13);
            ingredients.add(strIngredient14);
            ingredients.add(strIngredient15);
            ingredients = ingredients.stream()
                    .filter(ingr -> !ingr.isEmpty())
                    .collect(Collectors.toList());
            return new CocktailResource(null, idDrink, strDrink, strGlass, strInstructions, strDrinkThumb, ingredients);
        }
    }
}
