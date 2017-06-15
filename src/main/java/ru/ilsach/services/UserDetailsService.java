package ru.ilsach.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ilsach.models.Person;
import ru.ilsach.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ilsac on 25.04.2017.
 */

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    @Autowired
    private PersonRepository personRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDetails loadedUser;

        try {
            Person client = personRepository.findByLogin(login);
            loadedUser = new org.springframework.security.core.userdetails.User(
                    client.getLogin(), client.getPassword(),
                    DummyAuthority.getAuth());
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        return loadedUser;
    }

    static class DummyAuthority implements GrantedAuthority
    {
        static Collection<GrantedAuthority> getAuth()
        {
            List<GrantedAuthority> res = new ArrayList<>(1);
            res.add(new DummyAuthority());
            return res;
        }
        @Override
        public String getAuthority() {
            return "USER";
        }
    }
}
