package org.client.springClient.service;

import org.client.springClient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${central.server.url}")
    private String mainURL;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User findById(Long id) {
        return restTemplate.getForObject(mainURL + "/admin/findById/" + id, User.class);
    }

    @Override
    public List<User> findAll() {
        ResponseEntity<List<User>> rateResponse =
                restTemplate.exchange(mainURL + "/admin/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>(){});
        List<User> result = rateResponse.getBody();
        System.out.println("User service method:findAll size the return ArrayList: " + result.size());
        return result;
    }

    @Override
    public User saveUser(User user) {
        return restTemplate.postForObject(mainURL + "/admin/saveUser", new HttpEntity<>(user), User.class);
    }

    @Override
    public void updateUser(User user) {
        restTemplate.put(mainURL + "/admin/updateUser", user);
    }

    @Override
    public void deleteById(Long id) {
        restTemplate.delete(mainURL + "/admin/delete/" + id);
    }
}
