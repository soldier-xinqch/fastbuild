package com.fastbuild.auth.service.impl;

import com.fastbuild.auth.service.IAuthCustomTokenService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * 自定义授权token
 *
 * @auther xinch
 * @create 2018/1/16 17:26
 */
@Service("authCustomTokenService")
public class AuthCustomTokenServiceImpl implements IAuthCustomTokenService,TokenEnhancer {

    private RandomValueStringGenerator generator = new RandomValueStringGenerator();

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        // 自定义生成 token
        if (oAuth2AccessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) oAuth2AccessToken);
            token.setValue(generator.generate());// 这里设置token  默认为uuid 这里自定义为默认的6位大小写数字穿插的字符

            OAuth2RefreshToken refreshToken = token.getRefreshToken();
            if (refreshToken instanceof DefaultOAuth2RefreshToken) {
                token.setRefreshToken(new DefaultOAuth2RefreshToken(generator.generate()));// 这里设置token
            }
        }

        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("organization", oAuth2Authentication.getName() + randomAlphabetic(4));
        // 添加附带信息
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        System.out.println("oAuth2AccessToken = [" + oAuth2AccessToken.getValue() + "], oAuth2Authentication = [" + oAuth2Authentication.getName() + "]");
        return oAuth2AccessToken;
    }
}
