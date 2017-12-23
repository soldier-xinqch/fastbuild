package com.fastbuild.mapper;

import com.fastbuild.entity.TestTab;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 测试表 Mapper 接口
 * </p>
 *
 * @author xinqch
 * @since 2017-12-22
 */
public interface TestTabMapper extends BaseMapper<TestTab> {

    public TestTab getById(@Param("id") String id);
}
