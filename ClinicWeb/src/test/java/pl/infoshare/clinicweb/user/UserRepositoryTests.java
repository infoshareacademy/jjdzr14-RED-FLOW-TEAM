package pl.infoshare.clinicweb.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.infoshare.clinicweb.ClinicWebApplication;
import pl.infoshare.clinicweb.user.entity.AppUser;
import pl.infoshare.clinicweb.user.entity.Role;
import pl.infoshare.clinicweb.user.repository.AppUserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ClinicWebApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class UserRepositoryTests {
    @Autowired
    AppUserRepository userRepository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testUserCreation() {

        AppUser user = new AppUser();

        user.setEmail("halina.kopiec@wp.pl");
        user.setPassword("Puszek1234");
        user.setRole(Role.PATIENT);

        AppUser savedUser = userRepository.save(user);

        AppUser existUser = entityManager.find(AppUser.class, savedUser.getId());

        assertThat(existUser.getEmail().equals(savedUser.getEmail()));
    }

    @Test
    public void testFindUserByEmail() {

        String email = "malwina.kozak@gmail.com";

        Optional<AppUser> user = userRepository.findUserByEmail(email);

        assertThat(user).isNotEmpty();
    }

    @Test
    public void testUserRemovalById() {

        AppUser user = new AppUser();
        user.setId(1L);
        userRepository.deleteById(1L);

        Assert.assertTrue(userRepository.findById(1L).isEmpty());
    }
}
