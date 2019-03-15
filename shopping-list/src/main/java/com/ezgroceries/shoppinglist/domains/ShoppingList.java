package com.ezgroceries.shoppinglist.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_list")
public class ShoppingList {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @ManyToMany
    @JoinTable(name = "cocktail_shopping_list",
            joinColumns = {@JoinColumn(name = "shopping_list_id")},
            inverseJoinColumns = {@JoinColumn(name = "cocktail_id")})
    private List<Cocktail> cocktails;

    public ShoppingListResource mapToShoppingListResource() {
        return new ShoppingListResource(id, name, Collections.emptyList());
    }
}
