package org.client.springClient.service;

import org.client.springClient.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RoleServiceImpl implements RoleService {

    @Value("${central.server.url}")
    private String mainURL;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Role getRoleByName(String roleName) {
        return restTemplate.getForObject(mainURL + "/admin/getRoleByName/" + roleName, Role.class);
    }

}
