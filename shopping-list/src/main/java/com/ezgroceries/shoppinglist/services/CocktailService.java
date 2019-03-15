package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.domains.Cocktail;
import com.ezgroceries.shoppinglist.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.domains.CocktailResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CocktailService {
    private static final Logger logger = LoggerFactory.getLogger(CocktailService.class);
    private CocktailRepository cocktailRepository;

    @Autowired
    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public List<CocktailResource> findAll() {
        Iterable<Cocktail> cocktails = cocktailRepository.findAll();
        return StreamSupport.stream(cocktails.spliterator(), true)
                .map(cocktail -> cocktail.mapToCocktailResource())
                .collect(Collectors.toList());
    }

    public CocktailResource findById(UUID idDrink) {
        Optional<Cocktail> cocktail = cocktailRepository.findById(idDrink);
        return cocktail.orElse(new Cocktail()).mapToCocktailResource();
    }

    public List<CocktailResource> mergeCocktails(List<CocktailResource> clientCocktails) {
        List<String> knownIds = findAll().stream()
                .map(cocktail -> cocktail.getCocktailId())
                .collect(Collectors.toList());

        List<Cocktail> newCocktails = clientCocktails.stream()
                .filter(cocktail -> knownIds.indexOf(cocktail.getCocktailId()) < 0)
                .map(cocktail -> {
                    Set<String> ingr = new HashSet();
                    if (!cocktail.getIngredients().isEmpty()) {
                        ingr = new HashSet(cocktail.getIngredients());
                    }
                    Cocktail newCocktail = new Cocktail(null, cocktail.getCocktailId(), cocktail.getName(), ingr);
                    return newCocktail;
                })
                .collect(Collectors.toList());

        return StreamSupport.stream(cocktailRepository.saveAll(newCocktails).spliterator(), true)
                .map(cocktail -> cocktail.mapToCocktailResource())
                .collect(Collectors.toList());
    }
}
