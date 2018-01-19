package com.fastbuild.service.impl;

import com.fastbuild.entity.AuthClient;
import com.fastbuild.mapper.AuthClientMapper;
import com.fastbuild.service.AuthClientService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 授权客户端表 服务实现类
 * </p>
 *
 * @author xinqch
 * @since 2018-01-18
 */
@Service("authClientService")
public class AuthClientServiceImpl extends ServiceImpl<AuthClientMapper, AuthClient> implements AuthClientService {

}
