package com.ezgroceries.shoppinglist.cocktail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    public List<CocktailResource> mergeCocktails(List<CocktailResource> clientCocktails) {
        List<String> knownIds = findAll().stream().map(cocktail -> cocktail.getCocktailId()).collect(Collectors.toList());

        List<Cocktail> newCocktails = clientCocktails.stream()
                .filter(cocktail -> knownIds.indexOf(cocktail.getCocktailId()) < 0)
                .map(cocktail -> new Cocktail(null, cocktail.getCocktailId(), cocktail.getName(), null))
                .collect(Collectors.toList());

        return StreamSupport.stream(cocktailRepository.saveAll(newCocktails).spliterator(), true)
                .map(cocktail -> cocktail.mapToCocktailResource())
                .collect(Collectors.toList());
    }
}
