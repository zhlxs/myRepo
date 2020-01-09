package com.data.masterandslave.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.masterandslave.entity.SysResource;
import com.data.masterandslave.mapper.SysResourceMapper;
import com.data.masterandslave.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author alin
 * @since 2019-12-23
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public Integer saveMy(SysResource sysResource) {
        sysResourceMapper.insertMy(sysResource);
        return null;
    }
}
