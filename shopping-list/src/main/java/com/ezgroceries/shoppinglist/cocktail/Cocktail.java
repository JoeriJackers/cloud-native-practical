package com.ezgroceries.shoppinglist.cocktail;


import com.ezgroceries.shoppinglist.entities.StringSetConverter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "cocktail")
public class Cocktail {
    @Id
    @Column(name = "id")
    private UUID entityId;

    private String name;
//    private String glass;
//    private String instructions;
//    private String image;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;
}
