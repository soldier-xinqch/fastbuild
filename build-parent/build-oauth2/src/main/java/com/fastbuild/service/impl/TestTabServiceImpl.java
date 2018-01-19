package com.fastbuild.service.impl;

import com.fastbuild.database.DataSourceEnum;
import com.fastbuild.database.DataSourceTarget;
import com.fastbuild.entity.TestTab;
import com.fastbuild.mapper.TestTabMapper;
import com.fastbuild.service.TestTabService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import static com.fastbuild.database.DataSourceEnum.*;

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
    @DataSourceTarget("master")
    public TestTab getById(String id) {
        return baseMapper.getById(id);
    }

    @Override
    @DataSourceTarget("slave")
    public TestTab getByIdSlave(String id) {
        return baseMapper.getById(id);
    }

}
