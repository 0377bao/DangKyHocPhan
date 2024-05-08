package iuh.dangkyhocphan.controllers;

import iuh.dangkyhocphan.models.Clazz;
import iuh.dangkyhocphan.models.ResponseObject;
import iuh.dangkyhocphan.services.ClazzService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/admin/clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping(path = "/")
    public ResponseEntity<ResponseObject> getAllClazz(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Query class successfully", clazzService.findAll())
        );
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateClazz(@RequestBody Clazz clazz, @PathVariable Long id){
        Clazz foundedClass = clazzService.findById(id);
        if(foundedClass != null){
            foundedClass.setAdministrator(clazz.getAdministrator());
            foundedClass.setCourse(clazz.getCourse());
            foundedClass.setGiangVien(clazz.getGiangVien());
            foundedClass.setTenLop(clazz.getTenLop());
            foundedClass.setSiSoHienTai(clazz.getSiSoHienTai());
            foundedClass.setSiSoToiDa(clazz.getSiSoToiDa());
            clazzService.save(foundedClass);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't find class id = " + id, "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Update class successfully", foundedClass)
        );

    }
}
