package com.ezgroceries.shoppinglist.repositories;

import com.ezgroceries.shoppinglist.domains.Cocktail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;


public interface CocktailRepository extends CrudRepository<Cocktail, UUID> {

    List<Cocktail> findByNameContainingIgnoreCase(String search);
}
