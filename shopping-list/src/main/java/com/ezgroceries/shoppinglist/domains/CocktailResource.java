package com.ezgroceries.shoppinglist.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
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
