package com.ezgroceries.shoppinglist.cocktail;

import org.springframework.data.repository.Repository;

public interface CocktailRepository extends Repository<Cocktail, Long> {
    public Cocktail findByName(String name);
}
