package com.ezgroceries.shoppinglist.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ShoppingListResource {
    @Getter @Setter private UUID shoppingListId;
    @Getter private String name;
    @Getter @JsonInclude(Include.NON_NULL) private List<String> ingredients;
}
