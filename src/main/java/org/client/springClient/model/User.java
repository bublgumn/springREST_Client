package org.client.springClient.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private Long id;

    private String firstname;

    private String lastname;

    private Long age;

    private String email;

    private String password;

    private List<Role> role = new ArrayList<>();

    public User() {
    }

    public User(String firstname, String lastname, Long age, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
    }

    public User(String firstname, String lastname, Long age, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public User(String firstname, String lastname, Long age, String email, String password, List<Role> role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String printRoles() {
        String result = "";
        for (int i = 0; i < getRole().size(); i++) {
            if (i != getRole().size() - 1) {
                result = result + getRole().get(i).getName().substring(5) + " ";
            } else {
                result = result + getRole().get(i).getName().substring(5);
            }
        }
        return result;
    }

    public boolean isAdmin() {
        boolean result = false;
        for (Role role : getRole()
        ) {
            if (role.getName().trim().equals("ROLE_admin")) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void generateRole(int role) {
        if (role == 1) {
            this.role.add(new Role("ROLE_admin"));
        }
        this.role.add(new Role("ROLE_user"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*@JsonIgnore*/
    public List<Role> getRole() {
        return role;
    }

    /*@JsonIgnore*/
    public void setRole(List<Role> role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}