package com.hemeijia.apigate.service;

import com.hemeijia.apigate.message.error.RestApiException;
import com.hemeijia.apigate.message.error.RestEntityExistsException;
import com.hemeijia.apigate.persistence.dao.UserRepository;
import com.hemeijia.apigate.persistence.entity.User;
import com.hemeijia.apigate.web.dto.UserDto;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

  @Setter(onMethod_ = @Autowired)
  private PasswordEncoder passwordEncoder;

  @Setter(onMethod_ = @Autowired)
  private UserRepository userRepository;

  public User register(UserDto dto) throws RestApiException {
    String username = dto.getUsername();
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RestEntityExistsException("There is an account with same username");
    }
    final User user = new User();
    BeanUtils.copyProperties(dto, user, "password");
    user.setPassword(passwordEncoder.encode(dto.getPhoneNumber()));
    user.setName(dto.getName());
    user.setEnabled(true);
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("No user found with username: " + username)
    );
  }

}
