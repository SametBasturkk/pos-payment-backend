package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Menu;
import com.pospayment.pospayment.repository.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepo menuRepo;

    public void saveMenu(Menu menu) {
        menuRepo.save(menu);
    }

    public void deleteMenu(String id) {
        menuRepo.deleteById(id);
    }

    public void deactiveMenu(String id) {
        Menu menu = menuRepo.findById(id).get();
        menu.setIsActive(false);
        menuRepo.save(menu);
    }


    public Menu getMenu(String id) {
        return menuRepo.findById(id).get();
    }

    public List<Menu> getAllMenus() {
        return menuRepo.findAll();
    }



}
