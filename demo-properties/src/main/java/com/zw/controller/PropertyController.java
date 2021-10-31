package com.zw.controller;

import cn.hutool.core.lang.Dict;
import com.zw.pojo.ApplicationProperty;
import com.zw.pojo.DeveloperProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-05-20
 */
@RestController
public class PropertyController {
    private final ApplicationProperty applicationProperty;
    private final DeveloperProperty developerProperty;

    @Autowired
    public PropertyController(ApplicationProperty applicationProperty, DeveloperProperty developerProperty) {
        this.applicationProperty = applicationProperty;
        this.developerProperty = developerProperty;
    }

    @GetMapping("/property")
    public Dict getProperty(){
        return Dict.create().set("applicationProperty",applicationProperty).set("developerProperty",developerProperty);
    }
}
