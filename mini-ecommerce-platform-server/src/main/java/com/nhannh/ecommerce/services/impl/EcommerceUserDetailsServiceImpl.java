package com.nhannh.ecommerce.services.impl;

import com.nhannh.ecommerce.domain.EcommerceUserDetails;
import com.nhannh.ecommerce.domain.entities.User;
import com.nhannh.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EcommerceUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User not found with email: %s", email)
                ));
        return new EcommerceUserDetails(user);
    }
}
