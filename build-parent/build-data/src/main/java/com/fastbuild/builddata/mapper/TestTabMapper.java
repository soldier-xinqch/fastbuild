package com.fastbuild.builddata.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fastbuild.builddata.entity.TestTab;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 测试表 Mapper 接口
 * </p>
 *
 * @author xinqch
 * @since 2017-12-22
 */
@Repository
public interface TestTabMapper extends BaseMapper<TestTab> {

    public TestTab getById(@Param("id") String id);
}
