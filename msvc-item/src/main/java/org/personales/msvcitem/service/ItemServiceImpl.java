package org.personales.msvcitem.service;

import lombok.AllArgsConstructor;
import org.personales.msvcitem.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate restTemplate;

    
    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return null;
    }

}
