package pl.infoshare.clinicweb.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class AppUserService implements UserDetailsService {

    @Autowired
    private final AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AppUser> user = userRepository.findUserByEmail(email);

        if (user.isPresent()) {

            var userObject = user.get();

            log.info("User was found with email: {}", email);

            return User.builder()
                    .username(userObject.getEmail())
                    .password(userObject.getPassword())
                    .roles(userObject.getRole().getRoleDescription())
                    .build();

        } else {
            throw new UsernameNotFoundException(String.format("User not found with email %s", email));
        }

    }

    public void saveUser(AppUser user) {

        var appUser = userRepository.save(user);
        log.info("User saved with ID: {}", appUser.getId());

    }


}
