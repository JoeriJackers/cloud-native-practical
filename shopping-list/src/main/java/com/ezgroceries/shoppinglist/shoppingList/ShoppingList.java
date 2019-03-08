package com.ezgroceries.shoppinglist.shoppingList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_list")
public class ShoppingList {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    public ShoppingListResource mapToShoppingListResource() {
        return new ShoppingListResource(id, name, Arrays.asList());
    }
}
