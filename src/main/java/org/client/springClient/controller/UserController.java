package org.client.springClient.controller;

import org.client.springClient.model.User;
import org.client.springClient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/admin/returnAllUsers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/admin/addNewUser/{role}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@RequestBody User user, @PathVariable(name = "role") int role) {
        user.generateRole(role);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") long id){
        userService.deleteById(id);
    }

    @PutMapping("/admin/editUser/{role}")
    public void editUser (@RequestBody User user, @PathVariable(name = "role") int role){
        user.generateRole(role);
        userService.updateUser(user);
    }
}