package com.ezgroceries.shoppinglist.cocktail;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface CocktailRepository extends CrudRepository<Cocktail, UUID> {
    Cocktail findByIdDrink(String idDrink);
}
