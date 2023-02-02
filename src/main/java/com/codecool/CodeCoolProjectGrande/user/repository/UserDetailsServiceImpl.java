package com.codecool.CodeCoolProjectGrande.user.repository;


import com.codecool.CodeCoolProjectGrande.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void processOauthUser(OAuth2User user){
        if (!userRepository.findUserByEmail(user.getAttributes().get("email").toString()).isPresent()){
            userRepository.save(new User(user.getAttributes().get("given_name").toString(), "not_provided_yet", user.getAttributes().get("email").toString()));
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }
}