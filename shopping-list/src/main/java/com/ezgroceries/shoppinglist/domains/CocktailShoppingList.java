package com.ezgroceries.shoppinglist.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cocktail_shopping_list")
public class CocktailShoppingList {
    @Id
    public UUID cocktailId;
    public UUID shoppingListId;
}
