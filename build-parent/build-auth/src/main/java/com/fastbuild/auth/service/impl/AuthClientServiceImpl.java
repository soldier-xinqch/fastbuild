package com.fastbuild.auth.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.fastbuild.auth.model.AuthClientModel;
import com.fastbuild.auth.service.IAuthClientService;
import com.fastbuild.entity.OauthClientDetails;
import com.fastbuild.service.OauthClientDetailsService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("authClientService")
public class AuthClientServiceImpl implements IAuthClientService {


    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Wrapper<OauthClientDetails> wrapper = new EntityWrapper<>();
        wrapper.eq("client_id",clientId);
        OauthClientDetails client =  oauthClientDetailsService.selectOne(wrapper);
        AuthClientModel model = new AuthClientModel();

        model.setClientId(client.getClientId());
        model.setClientSecret(client.getClientSecret());

        String authorities  = client.getAuthorities();
        String[] authoritiesArray = authorities.split(",");
        model.setAuthorities(getAuthorities(authoritiesArray));

        String resourceId  = client.getResourceIds();
        String[] ids = resourceId.split(",");
        List<String> idLists = Collections.arrayToList(ids);
        model.setResourceIds(idLists);

        model.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        model.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());

        String types  = client.getAuthorizedGrantTypes();
        String[] typesArray = types.split(",");
        List<String> typeLists = Collections.arrayToList(typesArray);
        model.setAuthorizedGrantTypes(typeLists);

        String scope  = client.getScope();
        String[] scopes = scope.split(",");
        List<String> scopeLists = Collections.arrayToList(scopes);
        model.setScope(scopeLists);

        return model;
    }


    public Collection<? extends GrantedAuthority> getAuthorities(String[] roles) {
        if(null == roles) return null;
        // 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        for(String str :roles){
//            GrantedAuthority au = new SimpleGrantedAuthority("ROLE_USER");
            GrantedAuthority au = new SimpleGrantedAuthority(str);
            list.add(au);
        }
        return list;
    }
}
