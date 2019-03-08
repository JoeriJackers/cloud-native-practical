package com.ezgroceries.shoppinglist.cocktail;


import com.ezgroceries.shoppinglist.entities.StringSetConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
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
        return new CocktailResource(
                idDrink,
                name,
                "",
                "",
                "",
                new ArrayList<String>()
        );
    }
}
