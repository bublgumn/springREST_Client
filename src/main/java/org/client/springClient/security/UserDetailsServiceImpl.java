package org.client.springClient.security;

import org.client.springClient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Value("${central.server.url}")
    private String mainURL;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = restTemplate.getForObject(mainURL + "/admin/userByName/ForSecurity/" + username, User.class);
        return user;
    }
}
