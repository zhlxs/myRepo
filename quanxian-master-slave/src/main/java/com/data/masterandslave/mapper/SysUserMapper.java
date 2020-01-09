package com.data.masterandslave.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.masterandslave.datasource.Datasource;
import com.data.masterandslave.datasource.DatasourceName;
import com.data.masterandslave.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author alin
 * @since 2019-12-23
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser getUserFromMaster(@Param("id") Integer id);


    @Datasource(DatasourceName.SLAVE1)
    SysUser getUserFromSlave1(@Param("id")Integer id);

    @Datasource(DatasourceName.SLAVE2)
    SysUser getUserFromSlave2(@Param("id")Integer id);
}
