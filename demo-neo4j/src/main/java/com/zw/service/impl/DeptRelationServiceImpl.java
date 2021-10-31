package com.zw.service.impl;

import com.google.common.collect.Lists;
import com.zw.model.Dept;
import com.zw.model.DeptRelationShip;
import com.zw.repository.DeptRepository;
import com.zw.repository.RelationShipRepository;
import com.zw.service.DeptRelationService;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.metadata.schema.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service.impl
 * @descripation TODO
 * @date 2021-06-30
 */
@Service
@Slf4j
public class DeptRelationServiceImpl implements DeptRelationService {
    @Autowired
    DeptRepository deptRepository;

    @Autowired
    RelationShipRepository relationShipRepository;

    /**
     * CEO
     *    -设计部
     *        - 设计1组
     *        - 设计2组
     *    -技术部
     *        - 前端技术部
     *        - 后端技术部
     *        - 测试技术部
     */
    @Override
    public void createShips() {
        List<Dept> deptList = Lists.newArrayList();
        List<DeptRelationShip> shipList = Lists.newArrayList();
        //createCEO
        Dept deptCeo = Dept.builder().deptName("CEO").build();
        deptList.add(deptCeo);

        Dept deptDesign = Dept.builder().deptName("设计部").build();
        deptList.add(deptDesign);
        //ceo同设计部
        DeptRelationShip shipCeo_design = DeptRelationShip.builder().parent(deptCeo).child(deptDesign).build();
        shipList.add(shipCeo_design);

        Dept deptDesign_1 = Dept.builder().deptName("设计1组").build();
        deptList.add(deptDesign_1);
        //设计1组同设计部关系
        DeptRelationShip shipDesign_1 = DeptRelationShip.builder().parent(deptDesign).child(deptDesign_1).build();
        shipList.add(shipDesign_1);

        Dept deptDesign_2 = Dept.builder().deptName("设计2组").build();
        deptList.add(deptDesign_2);
        //设计2组同设计部关系
        DeptRelationShip shipDesign_2 = DeptRelationShip.builder().parent(deptDesign).child(deptDesign_2).build();
        shipList.add(shipDesign_2);

        Dept deptRD = Dept.builder().deptName("技术部").build();
        deptList.add(deptRD);
        //ceo同技术部
        DeptRelationShip shipCeo_RD = DeptRelationShip.builder().parent(deptCeo).child(deptRD).build();
        shipList.add(shipCeo_RD);

        Dept deptRD_1 = Dept.builder().deptName("前端技术部").build();
        deptList.add(deptRD_1);
        //前端同技术
        DeptRelationShip shipRD_1 = DeptRelationShip.builder().parent(deptRD).child(deptRD_1).build();
        shipList.add(shipRD_1);

        Dept deptRD_2 = Dept.builder().deptName("后端技术部").build();
        deptList.add(deptRD_2);
        //后端同技术
        DeptRelationShip shipRD_2 = DeptRelationShip.builder().parent(deptRD).child(deptRD_2).build();
        shipList.add(shipRD_2);

        Dept deptRD_3 = Dept.builder().deptName("测试技术部").build();
        deptList.add(deptRD_3);
        //测试同技术
        DeptRelationShip shipRD_3 = DeptRelationShip.builder().parent(deptRD).child(deptRD_3).build();
        shipList.add(shipRD_3);

        //节点Repository保存节点
        deptRepository.saveAll(deptList);
        log.info("《《《《《《《《《《《《《《《"+ relationShipRepository);
        relationShipRepository.saveAll(shipList);

    }

    @Override
    public void deleteAll() {
        deptRepository.deleteAll();
        relationShipRepository.deleteAll();
    }


}
