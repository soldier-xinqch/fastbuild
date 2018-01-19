package com.fastbuild.builddata.service;

import com.baomidou.mybatisplus.service.IService;
import com.fastbuild.builddata.entity.TestTab;

/**
 * <p>
 * 测试表 服务类
 * </p>
 *
 * @author xinqch
 * @since 2017-12-22
 */
public interface TestTabService extends IService<TestTab> {

    public TestTab getById(String id);

    public TestTab getByIdSlave(String id);

}
