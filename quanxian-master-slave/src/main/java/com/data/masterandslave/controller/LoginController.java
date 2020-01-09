package com.data.masterandslave.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.data.masterandslave.entity.QuanXianBean;
import com.data.masterandslave.entity.SysResource;
import com.data.masterandslave.entity.SysRoleResource;
import com.data.masterandslave.entity.SysUser;
import com.data.masterandslave.entity.SysUserRole;
import com.data.masterandslave.mapper.SysResourceMapper;
import com.data.masterandslave.mapper.SysRoleResourceMapper;
import com.data.masterandslave.mapper.SysUserMapper;
import com.data.masterandslave.mapper.SysUserRoleMapper;
import com.data.masterandslave.util.MD5Util;
import com.data.masterandslave.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by liyonglin on 2019-12-23 12:38
 */
@RestController
public class LoginController {

    public static Map<String, QuanXianBean> tokenMap = new HashMap<>();
    public static Map<String, String> usernameToToken = new HashMap<>();
    public static Long expireTime  = 10*1000l;

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;

    @GetMapping("/login")
    public ResponseResult login(String userName, String password) {
        ResponseResult result = new ResponseResult("请检查用户名和密码后再试", 403);
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return result;
        }
        String md5Password = MD5Util.md5Encrypt32Lower(password);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name", userName);
        wrapper.eq("password", md5Password);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if (null == sysUser) {
            return result;
        }

        // 删除旧的token
        String oldToken = usernameToToken.get(sysUser.getUserName());
        if (!StringUtils.isEmpty(oldToken)) {
            tokenMap.remove(sysUser.getUserName());
        }
        // 将用户信息放到系统缓存中
        QuanXianBean quanXianBean = new QuanXianBean();
        String token = MD5Util.md5Encrypt32Lower(userName + password + System.currentTimeMillis());
        quanXianBean.setToken(token);
        quanXianBean.setUserId(sysUser.getId());
        quanXianBean.setUserName(sysUser.getUserName());
        quanXianBean.setTimevalue(System.currentTimeMillis());
        quanXianBean.setResources(getResourceByUserId(sysUser.getId()));
        tokenMap.put(token, quanXianBean);
        usernameToToken.put(sysUser.getUserName(), token);
        return new ResponseResult(token, "登录成功", 200);
    }

    @GetMapping("/logout")
    public ResponseResult logout(HttpServletRequest request) {
        ResponseResult result = new ResponseResult("退出成功", 200);
        tokenMap.remove(request.getHeader("token"));
        return result;

    }

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    private Set<String> getResourceByUserId(Integer userId) {
        Set<String> set = new HashSet<>(100);
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(sysUserRoleList)) {
            return set;
        }
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.in("role_id", sysUserRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toSet()));
        List<SysRoleResource> list = sysRoleResourceMapper.selectList(wrapper1);
        if (CollectionUtils.isEmpty(list)) {
            return set;
        }

        QueryWrapper wrapper2 = new QueryWrapper();
        wrapper2.in("id", list.stream().map(item -> item.getId()).collect(Collectors.toSet()));
        List<SysResource> list1 = sysResourceMapper.selectList(wrapper2);
        Set<String> collect = list1.stream().map(item -> {
            return item.getUri() + "_" + item.getMethod();
        }).collect(Collectors.toSet());
        return collect;


    }
}
