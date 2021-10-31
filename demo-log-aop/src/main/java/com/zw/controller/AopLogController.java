package com.zw.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-05-21
 */

@Slf4j
@RestController
public class AopLogController {

    @GetMapping("/getTest")
    public Dict getAopTest(@RequestParam(required = false) String who) {
        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }

    @PostMapping("/postTest")
    public Dict postAopTest(@RequestBody Map<String,Object> map){
        String jst = JSONUtil.toJsonStr(map);
        log.info(jst);
        return Dict.create().set("json",map);
    }
}
