package com.data.masterandslave.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.masterandslave.entity.SysResource;
import com.data.masterandslave.entity.SysRole;
import com.data.masterandslave.entity.SysUser;
import com.data.masterandslave.mapper.SysRoleMapper;
import com.data.masterandslave.mapper.SysUserMapper;
import com.data.masterandslave.service.ISysResourceService;
import com.data.masterandslave.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author alin
 * @since 2019-12-23
 */
@Component
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ISysResourceService sysResourceService;

    public void addUser(SysUser sysUser) {

        sysUserMapper.insert(sysUser);
    }

    @Override
    public String getByIdInService(Integer id) {
//        SysUser userFromMaster = sysUserService.getUserFromMaster(1);
//        SysUser userFromSlave = sysUserService.getUserFromSlave(1);
//        return userFromMaster.toString()+userFromSlave.toString();
        return "";
    }

    @Override
    public SysUser getUserFromMaster(Integer id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUser getUserFromSlave(Integer id) {
        return sysUserMapper.selectById(id);
    }

//    @Transactional
    @Override
    public String getAll() {
        SysRole roleById = sysRoleMapper.getRoleById(1);
        SysUser userFromMaster = sysUserMapper.getUserFromMaster(1);
        SysUser sysUser = sysUserMapper.selectById(1);
        return roleById.toString()+"-----"+userFromMaster.toString()+sysUser.toString();
    }

    @Override
    @Transactional
    public String getUserFromMasterAndSlave() {
        SysUser userFromSlave = sysUserMapper.getUserFromSlave1(1);
        SysUser userFromMaster = sysUserMapper.getUserFromMaster(1);
        SysUser userFromSlave2 = sysUserMapper.getUserFromSlave2(1);
        return userFromSlave.toString()+userFromMaster.toString()+userFromSlave2.toString();
    }
    @Override
//    @Transactional
    public void testaDD() {
        SysResource sysResource = new SysResource();
        sysResource.setUri("testalin");
        sysResource.setCreateTime(LocalDateTime.now());
        sysResourceService.saveMy(sysResource);

//        SysUser sysUser = new SysUser();
//        sysUser.setUserName("addMaster");
//        sysUserMapper.insert(sysUser);

//        SysUser userFromSlave2 = sysUserMapper.getUserFromSlave2(1);
//        System.out.println("userFromSlave2------->"+userFromSlave2);

//        int i =1/0;


    }


    @Override
    @Transactional
    public String inserttestaDD() {
        SysUser userFromSlave = sysUserMapper.getUserFromSlave1(1);
        SysUser userFromMaster = sysUserMapper.getUserFromMaster(1);
        SysUser userFromSlave2 = sysUserMapper.getUserFromSlave2(1);
        return userFromSlave.toString()+userFromMaster.toString()+userFromSlave2.toString();


    }
}
