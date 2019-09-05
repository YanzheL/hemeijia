package com.hemeijia.apigate.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class User extends BaseEntity implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id
  protected String username;

  @Column(nullable = false)
  @NonNull
  @JsonIgnore
  protected String password;

  @Column(nullable = false, length = 20)
  @NonNull
  protected String phoneNumber;

  @Column(length = 10)
  @NonNull
  protected String name;

  protected boolean enabled;

  protected boolean member;

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
  }

}
