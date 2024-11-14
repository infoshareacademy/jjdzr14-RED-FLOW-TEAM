package pl.infoshare.clinicweb.user.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.user.entity.AppUser;
import pl.infoshare.clinicweb.user.entity.Role;
import pl.infoshare.clinicweb.user.mapper.UserMapper;
import pl.infoshare.clinicweb.user.registration.AppUserDto;
import pl.infoshare.clinicweb.user.repository.AppUserRepository;

import java.util.Optional;


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

        if (isUserAlreadyRegistered(user.getEmail())) {

            throw new IllegalArgumentException("Istnieje już konto z podanym adresem email!");

        }

        user.setRole(Role.PATIENT);
        var appUser = userMapper.toEntity(user);
        userRepository.save(appUser);
        log.info("User patient saved with ID: {}", appUser.getId());

    }

    public boolean isUserAlreadyRegistered(String email) {

        Optional<AppUser> user = userRepository.findUserByEmail(email);

        return user.isPresent();

    }


}
