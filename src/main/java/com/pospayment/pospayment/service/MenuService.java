package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Menu;
import com.pospayment.pospayment.repository.MenuRepo;
import com.pospayment.pospayment.util.JsonConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private JsonConverter jsonConverter;

    public void saveMenu(Menu menu) {
        menuRepo.save(menu);
    }

    @Transactional
    public void deleteMenu(String uuid) {
        menuRepo.deleteByUUID(uuid);
    }

    public void deactiveMenu(String id) {
        Menu menu = menuRepo.findById(id).get();
        menu.setIsActive(false);
        menuRepo.save(menu);
    }


    public Menu getMenu(String id) {
        return menuRepo.findById(id).get();
    }

    public String getAllMenus(Integer companyId) {
        return jsonConverter.convertToJson(menuRepo.findByCompanyId(companyId));
    }



}
