package org.personales.msvcitem.service;

import org.personales.msvcitem.models.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item findById(Long id, Integer cantidad);

}
