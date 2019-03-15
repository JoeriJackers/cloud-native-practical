package com.ezgroceries.shoppinglist.domains;


import com.ezgroceries.shoppinglist.helper.StringSetConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cocktail")
public class Cocktail {
    @Id
    @GeneratedValue
    private UUID id;
    private String idDrink;
    private String name;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public CocktailResource mapToCocktailResource() {
        CocktailResource cocktailResource = new CocktailResource(id, idDrink, name, "", "", "", Collections.emptyList());
        if (ingredients.isEmpty()) { return cocktailResource; }

        cocktailResource.setIngredients(new ArrayList<>(ingredients));
        return cocktailResource;
    }
}
