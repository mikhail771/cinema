package com.dev.cinema.service.impl;

import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;
import javax.naming.AuthenticationException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && isPasswordValid(password, user.get())) {
            return user.get();
        }
        throw new AuthenticationException("Incorrect user email or password");
    }

    @Override
    public User register(String email, String password) {
        return userService.add(new User(email, password));
    }

    private boolean isPasswordValid(String password, User user) {
        return HashUtil.hashPassword(password, user.getSalt()).equals(user.getPassword());
    }
}
