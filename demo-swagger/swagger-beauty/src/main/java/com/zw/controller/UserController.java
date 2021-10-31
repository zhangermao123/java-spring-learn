package com.zw.controller;

import com.battcn.boot.swagger.model.DataType;
import com.battcn.boot.swagger.model.ParamType;
import com.zw.pojo.User;
import com.zw.pojo.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-07
 */
@RestController
@RequestMapping("/user")
@Api(tags = "1.0.0-SNAPSHOT",value = "用户管理")
@Slf4j
public class UserController {

    @GetMapping
    @ApiOperation(value = "通过用户名获取用户信息",httpMethod = "GET",notes = "备注")
    @ApiImplicitParams({@ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = DataType.STRING,paramType = ParamType.QUERY)})
    public ApiResponse<User> getByUserName(String username) {
        log.info("多个参数用  @ApiImplicitParams");
        return ApiResponse.<User>builder().code(200).message("操作成功").data(new User(1, username, "JAVA")).build();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "通过id获取用户信息" )
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "用户编号",required = true,dataType = DataType.INT,paramType = ParamType.PATH)})
    public ApiResponse<User> get(@PathVariable Integer id) {
        log.info("单个参数用  @ApiImplicitParam");
        return ApiResponse.<User>builder().code(200).message("操作成功").data(new User(id, "u1", "p1")).build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "通过id删除用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "用户编号",required = true,dataType = DataType.INT,paramType = ParamType.PATH)})
    public void delete(@PathVariable Integer id) {
        log.info("单个参数用 ApiImplicitParam");
    }

    @PostMapping("/one")
    @ApiOperation(value = "添加用户")
    public User post(@RequestBody User user) {
        log.info("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        return user;
    }

    @PostMapping("/list")
    @ApiOperation(value = "添加一组用户")
    public List<User> multipar(@RequestBody List<User> user) {
        log.info("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        return user;
    }

    @PostMapping("/array")
    @ApiOperation(value = "添加一组用户")
    public User[] array(@RequestBody User[] user) {
        log.info("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        return user;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改用户")
    public void put(@PathVariable Long id, @RequestBody User user) {
        log.info("如果你不想写 @ApiImplicitParam 那么 swagger 也会使用默认的参数名作为描述信息 ");
    }

    @PostMapping("/{id}/file")
    @ApiOperation(value = "文件操作")
    public String file(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        log.info(file.getContentType());
        log.info(file.getName());
        log.info(file.getOriginalFilename());
        return file.getOriginalFilename();
    }
}
