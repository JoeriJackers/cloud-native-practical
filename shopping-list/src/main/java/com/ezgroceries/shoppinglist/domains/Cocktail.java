package com.ezgroceries.shoppinglist.domains;


import com.ezgroceries.shoppinglist.helpers.StringSetConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cocktail")
public class Cocktail {

    @Id
    @GeneratedValue
    private UUID id;

    private String idDrink;

    private String name;

    private String glass;

    private String instructions;

    private String image;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public CocktailResource mapToCocktailResource() {
        CocktailResource cocktailResource = new CocktailResource(id, idDrink, name, glass, instructions, image, Collections.emptyList());
        if (ingredients.isEmpty()) { return cocktailResource; }

        cocktailResource.setIngredients(new ArrayList<>(ingredients));
        return cocktailResource;
    }
}
