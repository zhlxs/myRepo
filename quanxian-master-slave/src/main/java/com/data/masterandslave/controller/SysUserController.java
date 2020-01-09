package com.data.masterandslave.controller;

import com.data.masterandslave.entity.SysUser;
import com.data.masterandslave.service.ISysUserService;
import com.data.masterandslave.util.MD5Util;
import com.data.masterandslave.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author alin
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/user")
    public ResponseResult getById(Integer id) {
        String userFromMaster = sysUserService.getByIdInService(id);


        return new ResponseResult(userFromMaster, "成功", 200);
    }

    @PostMapping("/addUser")
    public ResponseResult addUser(@RequestBody SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getUserName()) || StringUtils.isEmpty(sysUser.getPassword())) {
            return new ResponseResult("用户名或者密码不能为空", 400);
        }
        sysUser.setPassword(MD5Util.md5Encrypt32Lower(sysUser.getPassword()));
        sysUserService.addUser(sysUser);
        return new ResponseResult("成功", 200);
    }

    @GetMapping("/getAll")
    public ResponseResult getAll() {

        String all = sysUserService.getAll();
        return new ResponseResult(all, "成功", 200);
    }

    @GetMapping("/getUserFromMasterAndSlave")
    public ResponseResult getUserFromMasterAndSlave() {

        System.out.println("what is this?");
        String getUserFromMasterAndSlave = sysUserService.getUserFromMasterAndSlave();
        return new ResponseResult(getUserFromMasterAndSlave, "成功", 200);
    }


    @GetMapping("/testaDD")
    public ResponseResult testaDD() {

        sysUserService.testaDD();
        return new ResponseResult("成功", 200);
    }


    @GetMapping("/inserttest")
    public ResponseResult inserttest() {

        String s = sysUserService.inserttestaDD();
        return new ResponseResult(s, "成功", 200);
    }


}
