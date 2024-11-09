package pl.infoshare.clinicweb.doctor;

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

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ClinicWebApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testDoctorRemovalById() {

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctorRepository.deleteById(1L);

        Assert.assertTrue(doctorRepository.findById(1L).isEmpty());
    }

}
