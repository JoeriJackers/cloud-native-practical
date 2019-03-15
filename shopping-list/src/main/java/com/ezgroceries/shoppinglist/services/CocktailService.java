package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.domains.Cocktail;
import com.ezgroceries.shoppinglist.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.domains.CocktailResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CocktailService {
    private final CocktailRepository cocktailRepository;

    @Autowired
    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public List<CocktailResource> findAll() {
        Iterable<Cocktail> cocktails = cocktailRepository.findAll();
        return StreamSupport.stream(cocktails.spliterator(), true)
                .map(Cocktail::mapToCocktailResource)
                .collect(Collectors.toList());
    }

    public Optional<Cocktail> findById(UUID idDrink) {
        return cocktailRepository.findById(idDrink);
    }

    public void mergeCocktails(List<CocktailResource> clientCocktails) {
        List<String> knownIds = findAll().stream()
                .map(CocktailResource::getCocktailId)
                .collect(Collectors.toList());

        List<Cocktail> newCocktails = clientCocktails.stream()
                .filter(cocktail -> knownIds.indexOf(cocktail.getCocktailId()) < 0)
                .map(cocktail -> {
                    Set<String> ingr = new HashSet<>();
                    if (!cocktail.getIngredients().isEmpty()) {
                        ingr = new HashSet(cocktail.getIngredients());
                    }
                    return new Cocktail(null, cocktail.getCocktailId(), cocktail.getName(), ingr);
                })
                .collect(Collectors.toList());

        cocktailRepository.saveAll(newCocktails);
    }
}
