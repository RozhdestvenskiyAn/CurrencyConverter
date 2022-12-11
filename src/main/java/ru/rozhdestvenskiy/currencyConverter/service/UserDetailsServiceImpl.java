package ru.rozhdestvenskiy.currencyConverter.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rozhdestvenskiy.currencyConverter.exception.UserNotFoundException;
import ru.rozhdestvenskiy.currencyConverter.model.User;
import ru.rozhdestvenskiy.currencyConverter.repository.UserRepository;
import ru.rozhdestvenskiy.currencyConverter.security.UserDetailsImp;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
        return new UserDetailsImp(user);
    }
}
