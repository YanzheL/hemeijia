package com.hemeijia.apigate.persistence.dao;

import com.hemeijia.apigate.persistence.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

  Optional<User> findByUsername(String username);

  Optional<User> findByPhoneNumber(String phoneNumber);
}
