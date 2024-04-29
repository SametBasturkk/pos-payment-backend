package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Menu;
import com.pospayment.pospayment.service.MenuService;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createMenu(@RequestHeader String Authorization, @RequestBody Menu menu) {
        menuService.saveMenu(userService.getCompany(jwtToken.getUsername(Authorization)), menu);
        return ResponseEntity.ok("Menu created successfully");
    }

    @PostMapping("/delete")
    public void deleteMenu(@RequestParam String id) {
        menuService.deleteMenu(id);
    }

    @PostMapping("/deactive")
    public void deactiveMenu(String id) {
        menuService.deactiveMenu(id);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<String> getMenu(@PathVariable String uuid) {
        return ResponseEntity.ok(menuService.getMenuItems(uuid));
    }

    @GetMapping("/get-all")
    public ResponseEntity<String> getAllMenus(@RequestHeader String Authorization) {
        Company company = userService.getCompany(jwtToken.getUsername(Authorization));
        return ResponseEntity.ok(menuService.getAllMenus(company));
    }


}
