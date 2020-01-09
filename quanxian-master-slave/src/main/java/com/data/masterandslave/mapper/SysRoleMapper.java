package com.data.masterandslave.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.masterandslave.datasource.Datasource;
import com.data.masterandslave.datasource.DatasourceName;
import com.data.masterandslave.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author alin
 * @since 2019-12-23
 */
@Service
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Datasource(DatasourceName.SLAVE1)
    SysRole getRoleById(@Param("id")Integer id);


}
