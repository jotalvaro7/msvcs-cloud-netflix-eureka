package org.personales.msvcitem.web.controller;


import org.personales.msvcitem.domain.dto.ItemDTO;
import org.personales.msvcitem.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @GetMapping("/listar")
    public List<ItemDTO> getAllItems(){
        return itemService.getAll();
    }

    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public ItemDTO getItemById(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.getItemById(id, cantidad);
    }

}
