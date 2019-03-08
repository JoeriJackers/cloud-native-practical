package com.ezgroceries.shoppinglist.shoppingList;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListResource create(ShoppingListResource shoppingListResource) {
        ShoppingList shoppingList = shoppingListRepository.save(new ShoppingList(shoppingListResource.getShoppingListId(), shoppingListResource.getName()));
        return shoppingList.mapToShoppingListResource();
    }

    public List<ShoppingListResource> getAllShoppingLists() {
        Iterable<ShoppingList> shoppingLists = shoppingListRepository.findAll();
        return StreamSupport.stream(shoppingLists.spliterator(), true)
                .map(item -> item.mapToShoppingListResource())
                .collect(Collectors.toList());
    }

    public ShoppingListResource findById(UUID id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);
        if (shoppingList != null) {
            return shoppingList.get().mapToShoppingListResource();
        }
        return null;
    }
}
