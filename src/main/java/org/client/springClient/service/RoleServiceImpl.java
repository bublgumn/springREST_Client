package org.client.springClient.service;

import org.client.springClient.config.RestTemplateImpl;
import org.client.springClient.dto.JwtTokenDto;
import org.client.springClient.model.Role;
import org.client.springClient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RoleServiceImpl implements RoleService {

    @Value("${central.server.url}")
    private String mainURL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtTokenDto jwtTokenDto;

    @Override
    public Role getRoleByName(String roleName) {
        /*return restTemplate.getForObject(mainURL + "/admin/getRoleByName/" + roleName, Role.class)*/;
        ResponseEntity<Role> rateResponse =
                restTemplate.exchange(
                        mainURL + "/admin/getRoleByName/" + roleName,
                        HttpMethod.GET,
                        new HttpEntity<>("", RestTemplateImpl.getHttpHeaders(jwtTokenDto)),
                        new ParameterizedTypeReference<Role>() {
                        });
        return rateResponse.getBody();
    }

}
