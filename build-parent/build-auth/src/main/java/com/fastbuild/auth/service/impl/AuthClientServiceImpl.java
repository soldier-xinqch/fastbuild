package com.fastbuild.auth.service.impl;

import com.fastbuild.auth.service.IAuthClientService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

public class AuthClientServiceImpl implements IAuthClientService {


    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {

        return null;
    }
}
