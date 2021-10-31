package com.zw.controller;

import com.zw.model.Dept;
import com.zw.model.DeptRelationShip;
import com.zw.service.DeptRelationService;
import com.zw.service.impl.DeptRelationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-30
 */
@RestController
public class NeoController {


    @Autowired
    DeptRelationService deptRelationService;

    @GetMapping("/create")
    public String create(){
        deptRelationService.createShips();
        return "create success";
    }

//    @GetMapping("get")
//    public DeptRelationShip get(Long id){
//        Optional<DeptRelationShip> byId = deptRelationService.findById(id);
//        return byId.orElse(null);
//    }
//
//    @GetMapping("deleteRelationShip")
//    public void deleteRelationShip(Long id){
//        relationShipRepository.deleteById(id);
//    }
//
//    @GetMapping("deleteDept")
//    public void deleteDept(Long id){
//        deptRepository.deleteById(id);
//    }
//
    @GetMapping("deleteAll")
    public void deleteAll(){
        deptRelationService.deleteAll();
    }
}
