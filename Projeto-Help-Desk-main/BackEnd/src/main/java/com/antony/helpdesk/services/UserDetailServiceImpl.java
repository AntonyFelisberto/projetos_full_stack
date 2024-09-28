package com.antony.helpdesk.services;

import com.antony.helpdesk.abstractions.Person;
import com.antony.helpdesk.repositories.PersonRepository;
import com.antony.helpdesk.security.UserSpringSecurity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> user = personRepository.findByEmail(email);
        if(user.isPresent()) {
            return new UserSpringSecurity(user.get().getPersonId(), user.get().getEmail(),user.get().getPassword(),user.get().getProfile());
        }else {
            throw new UsernameNotFoundException(email);
        }

    }

}
