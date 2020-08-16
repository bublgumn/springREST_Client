package org.client.springClient.service;

import org.client.springClient.config.RestTemplateImpl;
import org.client.springClient.dto.JwtTokenDto;
import org.client.springClient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    /*return restTemplate.getForObject(
                mainURL + "/admin/findById/" + id
                new HttpEntity<>(), User.class);*/

    @Value("${central.server.url}")
    private String mainURL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtTokenDto jwtTokenDto;

    @Override
    public User findById(Long id) {
        ResponseEntity<User> rateResponse =
                restTemplate.exchange(
                        mainURL + "/admin/findById/" + id,
                        HttpMethod.GET,
                        new HttpEntity<>("", RestTemplateImpl.getHttpHeaders(jwtTokenDto)),
                        new ParameterizedTypeReference<User>() {
                        });
        return rateResponse.getBody();
    }

    @Override
    public List<User> findAll() {
        ResponseEntity<List<User>> rateResponse =
                restTemplate.exchange(
                        mainURL + "/admin/findAll",
                        HttpMethod.GET,
                        new HttpEntity<>("", RestTemplateImpl.getHttpHeaders(jwtTokenDto)),
                        new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> result = rateResponse.getBody();
        if (result != null) {
            System.out.println("User service method:findAll size the return ArrayList: " + result.size());
        }
        return result;
    }

    @Override
    public User saveUser(User user) {
        return restTemplate.postForObject(
                mainURL + "/admin/saveUser",
                new HttpEntity<>(user, RestTemplateImpl.getHttpHeaders(jwtTokenDto)),
                User.class);
    }

    @Override
    public void updateUser(User user) {
        restTemplate.put(
                mainURL + "/admin/updateUser",
                new HttpEntity<>(user, RestTemplateImpl.getHttpHeaders(jwtTokenDto)));
    }

    @Override
    public void deleteById(Long id) {
        restTemplate.exchange(
                mainURL + "/admin/delete/" + id,
                HttpMethod.DELETE,
                new HttpEntity<>("", RestTemplateImpl.getHttpHeaders(jwtTokenDto)),
                new ParameterizedTypeReference<String>() {
                });
    }

    @Override
    public User findByName(String username) {
        return restTemplate.postForObject(
                mainURL + "/user/getThisUser",
                new HttpEntity<>(username, RestTemplateImpl.getHttpHeaders(jwtTokenDto)),
                User.class);
    }

}
