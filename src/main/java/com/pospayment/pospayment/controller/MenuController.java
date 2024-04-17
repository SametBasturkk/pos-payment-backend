package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.model.Menu;
import com.pospayment.pospayment.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/create")
    public void createMenu(Menu menu) {
        menuService.saveMenu(menu);
    }

    @PostMapping("/delete")
    public void deleteMenu(String id) {
        menuService.deleteMenu(id);
    }

    @PostMapping("/deactive")
    public void deactiveMenu(String id) {
        menuService.deactiveMenu(id);
    }


}
