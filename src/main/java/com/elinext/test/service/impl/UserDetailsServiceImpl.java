package com.elinext.test.service.impl;

import com.elinext.test.domain.Role;
import com.elinext.test.domain.User;
import com.elinext.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service(value = "userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            User user = userService.findByName(userName);
            if (user.getId() == null) {
                throw new UsernameNotFoundException(String.format("User '%s' not found", userName));
            } else {
                return new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()
                                .stream().map(Role::getRoleName).collect(Collectors.joining(", ")))
                );
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this name not found");
        }
    }
}
