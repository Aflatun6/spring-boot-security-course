package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRoles.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(STUDENT.getGrantedAuthorities(), passwordEncoder.encode("password"), "annasmith", true, true, true, true),
                new ApplicationUser(ADMINTRAINEE.getGrantedAuthorities(), passwordEncoder.encode("password"), "tomharden", true, true, true, true),
                new ApplicationUser(ADMIN.getGrantedAuthorities(), passwordEncoder.encode("password"), "lindawillock", true, true, true, true)
        );
        return applicationUsers;
    }
}
