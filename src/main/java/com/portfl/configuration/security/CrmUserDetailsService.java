package com.portfl.configuration.security;

import com.portfl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ikatlinsky
 * @since 3/29/17
 */
@Service
public class CrmUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public CrmUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CrmUserDetails(this.userRepository.findByUsername(username));
    }
}