package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.dto.MenuDTO;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Menu;
import com.pospayment.pospayment.service.MenuService;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.Converter;
import com.pospayment.pospayment.util.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    private MenuService menuService;

    private JwtToken jwtToken;

    private UserService userService;

    private Converter converter;

    public MenuController(MenuService menuService, JwtToken jwtToken, UserService userService, Converter converter) {
        this.menuService = menuService;
        this.jwtToken = jwtToken;
        this.userService = userService;
        this.converter = converter;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMenu(@RequestHeader String Authorization, @RequestBody Menu menu) {
        menuService.saveMenu(userService.getCompany(jwtToken.getUsername(Authorization)), menu);
        log.info("Menu created successfully : {}", menu);
        return ResponseEntity.ok("Menu created successfully");
    }

    @PostMapping("/delete")
    public void deleteMenu(@RequestParam String id) {
        log.info("Menu delete request : {}", id);
        menuService.deleteMenu(id);
    }

    @PostMapping("/deactive")
    public void deactiveMenu(String id) {
        menuService.deactiveMenu(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMenu(@PathVariable String id) {
        log.info("Menu request : {}", id);
        return ResponseEntity.ok(menuService.getMenuItems(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<MenuDTO>> getAllMenus(@RequestHeader String Authorization) {
        Company company = userService.getCompany(jwtToken.getUsername(Authorization));
        log.info("Menu list request");
        return ResponseEntity.ok(menuService.getAllMenus(company));

    }



}
