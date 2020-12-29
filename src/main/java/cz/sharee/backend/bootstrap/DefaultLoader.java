package cz.sharee.backend.bootstrap;

import cz.sharee.backend.repositories.AuthorityRepository;
import cz.sharee.backend.repositories.RoleRepository;
import cz.sharee.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultLoader implements CommandLineRunner{

    private final UserRepository userDomainRepository;
    private final RoleRepository roleDomainRepository;
    private final AuthorityRepository authorityDomainRepository;
    // this has to be @Autowired because in constructor in causes cycle
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception{

    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
