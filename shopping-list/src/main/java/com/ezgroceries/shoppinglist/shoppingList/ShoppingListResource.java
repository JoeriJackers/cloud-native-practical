package com.ezgroceries.shoppinglist.shoppingList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
public class ShoppingListResource {
    private UUID shoppingListId;
    private final String name;
    private List<String> ingredients;
}
