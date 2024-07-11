package com.Inventory.inventory.controller;

import com.Inventory.inventory.dto.InventoryDto;
import com.Inventory.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getitems")
    public List<InventoryDto> getItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/item/{itemId}")
    public InventoryDto getItemById(@PathVariable Integer itemId) {
        return inventoryService.getItemById(itemId);
    }

    @PostMapping("/additem")
    public InventoryDto saveItem(@RequestBody InventoryDto inventoryDTO) {
        return inventoryService.saveItem(inventoryDTO);
    }

    @PutMapping("/updateitem")
    public InventoryDto updateItem(@RequestBody InventoryDto inventoryDTO) {
        return inventoryService.updateItem(inventoryDTO);
    }

    @DeleteMapping("/deleteitem/{itemId}")
    public String deleteItem(@PathVariable Integer itemId) {
        return inventoryService.deleteItem(itemId);
    }
}
