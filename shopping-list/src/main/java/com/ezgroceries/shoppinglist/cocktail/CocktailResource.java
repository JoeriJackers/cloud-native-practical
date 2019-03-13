package com.ezgroceries.shoppinglist.cocktail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
public class CocktailResource {
    private UUID id;
    private String cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private String image;
    private List<String> ingredients;
}
