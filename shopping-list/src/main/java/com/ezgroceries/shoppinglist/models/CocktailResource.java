package com.ezgroceries.shoppinglist.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class CocktailResource {
    @Getter private String cocktailId;
    @Getter @JsonInclude(Include.NON_NULL) private String name;
    @Getter @JsonInclude(Include.NON_NULL) private String glass;
    @Getter @JsonInclude(Include.NON_NULL) private String instructions;
    @Getter @JsonInclude(Include.NON_NULL) private String image;
    @Getter @JsonInclude(Include.NON_NULL) private List<String> ingredients;
}
