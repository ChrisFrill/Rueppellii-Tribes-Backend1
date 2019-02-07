package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserDTO;
import com.greenfox.tribes1.Exception.UsernameTakenException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ApplicationUserServiceTest {
  private String username = "John";
  private String password = "password";
  private ApplicationUserService applicationUserService;

  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Mock
  ApplicationUserRepository applicationUserRepository;

  private ApplicationUserDTO testUserDTO = ApplicationUserDTO.builder()
          .username(username)
          .password(password)
          .build();
  private ApplicationUser testUser = ApplicationUser.builder()
          .username(username)
          .password(password)
          .build();

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    applicationUserService = new ApplicationUserService(applicationUserRepository, encoder);
  }

  @Test
  public void getByUsername_test() {
    when(applicationUserRepository.findByUsername(username)).thenReturn(Optional.of(testUser));
    assertEquals(testUser, applicationUserService.getByUsername(username).get());
  }

  @Test(expected = UsernameTakenException.class)
  public void registerNewUser_ThrowException_IfUserAlreadyExist() throws UsernameTakenException {
    when(applicationUserRepository.existsByUsername(username)).thenReturn(true);
    applicationUserService.registerNewUser(testUserDTO);
  }

  @Test
  public void registerNewUser_ReturnsUser_IfUserNotExist() throws UsernameTakenException {
    when(applicationUserRepository.save(Mockito.any(ApplicationUser.class))).thenReturn(testUser);
    assertEquals(testUser, applicationUserService.registerNewUser(testUserDTO));
  }

  @Test
  public void createUserFromDTO_test(){
    assertEquals(testUser.getUsername(), applicationUserService.createUserFromDTO(testUserDTO).getUsername());
    assertEquals(testUser.getPassword(), applicationUserService.createUserFromDTO(testUserDTO).getPassword());
  }

  @Test(expected = UsernameNotFoundException.class)
  public void login_ThrowsException_IfUserNotRegistered(){
    when(applicationUserRepository.existsByUsername(username)).thenReturn(false);
    applicationUserService.login(testUserDTO);
  }

  @Test
  public void login_ReturnsOK_ifUserRegistered(){
    when(applicationUserRepository.existsByUsername(username)).thenReturn(true);
    when(applicationUserRepository.findByUsername(username)).thenReturn(Optional.of(testUser));
    assertEquals(testUserDTO,applicationUserService.login(testUserDTO));
  }

}
