package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.Administrator;
import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admin/Info")
public class AdminController {
    @Autowired
    private AdministratorService adminService;
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getAdminById(@PathVariable Long id) {

        Administrator admin = adminService.findById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find any admin", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Admin found", admin)
        );

    }
}
