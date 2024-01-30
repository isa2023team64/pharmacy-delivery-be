package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.User;
import com.isa2023team64.pharmacydeliverybe.repository.UserRepository;
import com.isa2023team64.pharmacydeliverybe.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).orElse(null);
	}

	public User findById(Long id) throws AccessDeniedException {
		return userRepository.findById(id).orElseGet(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}

    public User update(User user) {
		// user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
