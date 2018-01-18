package com.fastbuild.service.impl;

import com.fastbuild.entity.AuthUser;
import com.fastbuild.mapper.AuthUserMapper;
import com.fastbuild.service.AuthUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 授权用户表 服务实现类
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements AuthUserService {

}
