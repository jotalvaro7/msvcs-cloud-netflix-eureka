package org.personales.msvcitem.controller;


import org.personales.msvcitem.models.Item;
import org.personales.msvcitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping()
    public List<Item> getAllItems(){
        return itemService.findAll();
    }

    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item getItemById(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.findById(id, cantidad);
    }

}
