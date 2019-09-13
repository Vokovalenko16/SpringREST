package com.vokovalenko16.framework.config.custom;

import com.vokovalenko16.framework.enums.ValidFlag;
import com.vokovalenko16.framework.po.User;
import com.vokovalenko16.framework.repo.UserRepository;
import com.vokovalenko16.framework.tools.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom user details service.
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 4/22/16
 * @since JDK1.8
 */
@Service public class CustomUserDetailsService implements UserDetailsService {

  @Override public UserDetails loadUserByUsername(String usr) throws UsernameNotFoundException {
    User user = userRepository.findByUsrAndValidFlag(usr, ValidFlag.VALID).orElseThrow(
        // Throw cannot find any user by this usr param.
        () -> new UsernameNotFoundException(String.format("User %s does not exist!", usr)));
    return new CustomUserRepositoryUserDetails(user);
  }

  private final UserRepository userRepository;

  @Autowired public CustomUserDetailsService(UserRepository userRepository) {
    Assert.defaultNotNull(userRepository);
    this.userRepository = userRepository;
  }
}
