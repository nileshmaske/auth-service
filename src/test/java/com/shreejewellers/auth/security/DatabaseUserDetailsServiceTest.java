package com.shreejewellers.auth.security;
import com.shreejewellers.auth.user.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
class DatabaseUserDetailsServiceTest {
 @Test void unknownUserRejected(){
  var repo=mock(UserRepository.class);
  when(repo.findByUsername("missing")).thenReturn(Optional.empty());
  assertThrows(UsernameNotFoundException.class,()->new DatabaseUserDetailsService(repo).loadUserByUsername("missing"));
 }
}
