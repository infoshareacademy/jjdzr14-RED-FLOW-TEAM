package pl.infoshare.clinicweb.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class AppUserService implements UserDetailsService {

    @Autowired
    private final AppUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser user = userRepository.findUserByEmail(email).get();

        if (!user.equals(null)) {

            log.info("User was found with email: {}", email);

            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(String.valueOf(user.getRole()))
                    .build();

        } else {

            throw new UsernameNotFoundException(String.format("User not found with email %s", email));
        }

    }

    public void saveUser(AppUserDto user) {

        var appUser = userMapper.toEntity(user);
        log.info("User saved with ID: {}", appUser.getId());

    }


}
