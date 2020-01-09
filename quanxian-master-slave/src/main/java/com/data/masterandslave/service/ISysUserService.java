package com.data.masterandslave.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.data.masterandslave.entity.SysUser;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author alin
 * @since 2019-12-23
 */
public interface ISysUserService extends IService<SysUser> {
    public void addUser(SysUser sysUser);

    String getByIdInService(Integer id);

    SysUser getUserFromMaster(Integer id);

    SysUser getUserFromSlave(Integer id);

    public String getAll();

    String getUserFromMasterAndSlave();

    public void testaDD();

    public String inserttestaDD();
}
