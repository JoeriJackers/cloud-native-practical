package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.domains.Cocktail;
import com.ezgroceries.shoppinglist.domains.CocktailResource;
import com.ezgroceries.shoppinglist.domains.ShoppingList;
import com.ezgroceries.shoppinglist.repositories.ShoppingListRepository;
import com.ezgroceries.shoppinglist.domains.ShoppingListResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final CocktailService cocktailService;

    public ShoppingListResource create(ShoppingListResource shoppingListResource) {
        ShoppingList shoppingList = shoppingListRepository.save(new ShoppingList(shoppingListResource.getShoppingListId(), shoppingListResource.getName(), null));
        return mapToShoppingListResource(shoppingList);
    }

    public List<ShoppingListResource> getAllShoppingLists() {
        Iterable<ShoppingList> shoppingLists = shoppingListRepository.findAll();
        return StreamSupport.stream(shoppingLists.spliterator(), true)
                .map(ShoppingList::mapToShoppingListResource)
                .collect(Collectors.toList());
    }

    public ShoppingListResource findById(UUID id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);
        if (shoppingList.isPresent()) {
            return mapToShoppingListResource(shoppingList.get());
        }
        return null;
    }

    public void addCocktailsToShoppingList(String shoppingListId, List<CocktailResource> cocktailResources) {
        List<Cocktail> cocktails = cocktailResources.stream()
                .map(cocktail -> cocktailService.findById(cocktail.getId()).orElse(null))
                .collect(Collectors.toList());

        ShoppingList shoppingList = shoppingListRepository.findById(UUID.fromString(shoppingListId))
                .orElseGet(ShoppingList::new);

        shoppingList.setCocktails(cocktails);
        shoppingListRepository.save(shoppingList);
    }

    private static ShoppingListResource mapToShoppingListResource(ShoppingList shoppinglist) {
        List<String> ingredients = shoppinglist.getCocktails()
                .stream()
                .map(Cocktail::getIngredients)
                .map(ArrayList<String>::new)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return new ShoppingListResource(shoppinglist.getId(), shoppinglist.getName(), ingredients);
    }
}
