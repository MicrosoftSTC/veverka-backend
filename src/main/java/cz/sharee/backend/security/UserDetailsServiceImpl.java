package cz.sharee.backend.security;

import cz.sharee.backend.domain.profile.User;
import cz.sharee.backend.exceptions.domain.UserNotFoundException;
import cz.sharee.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jt on 6/22/20.
 */
@Slf4j
@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userDomainRepository;
//    private LoginAttemptServiceImpl loginAttemptService;

    @SneakyThrows
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        log.debug("Getting User info via JPA");

        User loadedUser = userDomainRepository.findByUsername(username).get();

        if(userDomainRepository.findByUsername(username).isPresent()){
//            validateLoginAttempt(loadedUser);
            return loadedUser;
        }else{
            throw new UserNotFoundException("User does not exist");
        }

//        return userDomainRepository.findUserDomainByUsername(username).orElseThrow(() -> {
//            return new UsernameNotFoundException("User name: " + username + " not found");
//        });
    }

//    private void validateLoginAttempt(UserDomain userDomain) throws ExecutionException {
//        if(userDomain.isAccountNonLocked()){
//            if(loginAttemptService.hasExceededMaxAttempt(userDomain.getUsername())){
//                // exceeded max login attempts
//                userDomain.setIsAccountNonLocked(false);
//            }else{
//                // the cache expired, users login attempts are zero, but account is still locked
//                userDomain.setIsAccountNonLocked(true);
//            }
//        }else{
//            // account is locked, should not be in the cache
//            loginAttemptService.evictUserFromLoginAttemptCache(userDomain.getUsername());
//        }
//    }
}

