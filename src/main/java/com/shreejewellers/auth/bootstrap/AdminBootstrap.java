package com.shreejewellers.auth.bootstrap;
import com.shreejewellers.auth.user.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class AdminBootstrap implements ApplicationRunner {
 private final UserRepository users;private final RoleRepository roles;private final PasswordEncoder encoder;
 private final String username;private final String password;
 public AdminBootstrap(UserRepository users,RoleRepository roles,PasswordEncoder encoder,
  @Value("${BOOTSTRAP_ADMIN_USERNAME:}") String username,
  @Value("${BOOTSTRAP_ADMIN_PASSWORD:}") String password){
  this.users=users;this.roles=roles;this.encoder=encoder;this.username=username;this.password=password;
 }
 @Override @Transactional public void run(ApplicationArguments args){
  if(username.isBlank() && password.isBlank()) return;
  if(username.isBlank() || password.length()<12) throw new IllegalArgumentException("Bootstrap admin requires a username and password of at least 12 characters");
  if(users.existsByUsername(username)) return;
  var adminRole=roles.findByName("ADMIN").orElseThrow(()->new IllegalStateException("ADMIN role missing"));
  var admin=new User();admin.setUsername(username);admin.setPasswordHash(encoder.encode(password));
  admin.getRoles().add(adminRole);users.save(admin);
 }
}
