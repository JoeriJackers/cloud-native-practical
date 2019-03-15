package com.ezgroceries.shoppinglist.domains;


import com.ezgroceries.shoppinglist.helper.StringSetConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
        List<String> ingr = new ArrayList<>();
        if (!ingredients.isEmpty()) {
            ingr = new ArrayList<>(ingredients);
        }
        return new CocktailResource(
                id,
                idDrink,
                name,
                "",
                "",
                "",
                ingr
        );
    }
}
