package pl.infoshare.clinicweb.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.infoshare.clinicweb.user.entity.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Query("select u from AppUser u where u.email=:email")
    Optional<AppUser> findUserByEmail(String email);
}
