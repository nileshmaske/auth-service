package com.shreejewellers.auth.security;
import com.shreejewellers.auth.user.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
public class DatabaseUserDetailsService implements UserDetailsService {
 private final UserRepository users;
 public DatabaseUserDetailsService(UserRepository users){this.users=users;}
 @Override public UserDetails loadUserByUsername(String username){
  var user=users.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Invalid credentials"));
  return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
   .password(user.getPasswordHash()).disabled(!user.isEnabled())
   .authorities(user.getRoles().stream().map(role->"ROLE_"+role.getName()).toArray(String[]::new)).build();
 }
}
