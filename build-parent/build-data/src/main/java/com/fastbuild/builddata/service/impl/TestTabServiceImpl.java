package com.fastbuild.builddata.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fastbuild.builddata.entity.TestTab;
import com.fastbuild.builddata.mapper.TestTabMapper;
import com.fastbuild.builddata.service.TestTabService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 测试表 服务实现类
 * </p>
 *
 * @author xinqch
 * @since 2017-12-22
 */
@Service
public class TestTabServiceImpl extends ServiceImpl<TestTabMapper, TestTab> implements TestTabService {

    @Override
    public TestTab getById(String id) {
        return baseMapper.getById(id);
    }

    @Override
    public TestTab getByIdSlave(String id) {
        return baseMapper.getById(id);
    }

}
