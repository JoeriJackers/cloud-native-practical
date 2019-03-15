package com.ezgroceries.shoppinglist.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_list")
public class ShoppingList {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    public ShoppingListResource mapToShoppingListResource() {
        return new ShoppingListResource(id, name, Collections.emptyList());
    }
}
