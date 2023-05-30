package com.restro.app.controller;

import com.restro.app.model.Menu;
import com.restro.app.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;


    @PostMapping
    public String addNewMenu(@RequestBody Menu menu) {
        menuRepository.save(menu);
        return "Employee created in database";
    }

    @GetMapping
    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    @PutMapping("/{menuId}")
    public String updateMenu(@PathVariable Integer menuId, @RequestBody Menu updatedMenu) {
        Optional<Menu> menu = menuRepository.findById(menuId);
        if (menu.isPresent()) {
            Menu oldMenu = menu.get();
            oldMenu.setMenuName(updatedMenu.getMenuName());
            oldMenu.setMenuPrice(updatedMenu.getMenuPrice());
            menuRepository.save(oldMenu);
            return "data with id " + menuId + " updated";
        } else {
            return "No data available with such ID";
        }
    }

    @DeleteMapping("/{menuId}")
    public String deleteMenu(@PathVariable Integer menuId) {
        try {
            menuRepository.deleteById(menuId);
            return "Menu is deleted";
        } catch (EmptyResultDataAccessException erd) {
            return "Failed to delete menu";
        } catch (Exception e) {
            return "Failed to delete menu";
        }

    }
}
