package com.fastbuild.service.impl;

import com.fastbuild.entity.AuthUserRole;
import com.fastbuild.mapper.AuthUserRoleMapper;
import com.fastbuild.service.AuthUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 授权用户角色关联表 服务实现类
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@Service
public class AuthUserRoleServiceImpl extends ServiceImpl<AuthUserRoleMapper, AuthUserRole> implements AuthUserRoleService {

}
