package pl.infoshare.clinicweb.doctor;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class DoctorService {
    private final DoctorRepository repository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.repository = doctorRepository;
    }


    public void addDoctor(Doctor user) {
        addDoctor(user);
    }


    public List<Doctor> findAllDoctors() {
        return repository.findAll();
    }


    public void deleteDoctor(Long idDoctor) {
        repository.deleteById(idDoctor);
    }



    public void updateDoctor(Doctor doctor) {
        repository.save(doctor);
    }

    public void findDoctorByKey(String name, String surname) {

    }


    public void flush() {

    }


    public <S extends Doctor> S saveAndFlush(S entity) {
        return null;
    }


    public <S extends Doctor> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    public void deleteAllInBatch(Iterable<Doctor> entities) {

    }


    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }


    public void deleteAllInBatch() {

    }


    public Doctor getOne(Integer integer) {
        return null;
    }





    public Doctor getReferenceById(Integer integer) {
        return null;
    }


    public <S extends Doctor> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }


    public <S extends Doctor> List<S> findAll(Example<S> example) {
        return List.of();
    }


    public <S extends Doctor> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }


    public <S extends Doctor> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }


    public <S extends Doctor> long count(Example<S> example) {
        return 0;
    }


    public <S extends Doctor> boolean exists(Example<S> example) {
        return false;
    }


    public <S extends Doctor, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


    public <S extends Doctor> S save(S entity) {
        return null;
    }


    public <S extends Doctor> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }


    public Optional<Doctor> findById(Integer integer) {
        return Optional.empty();
    }


    public boolean existsById(Integer integer) {
        return false;
    }


    public List<Doctor> findAll() {
        return List.of();
    }


    public List<Doctor> findAllById(Iterable<Integer> integers) {
        return List.of();
    }


    public long count() {
        return 0;
    }


    public void deleteById(Integer integer) {

    }


    public void delete(Doctor entity) {

    }


    public void deleteAllById(Iterable<? extends Integer> integers) {

    }


    public void deleteAll(Iterable<? extends Doctor> entities) {

    }


    public void deleteAll() {

    }


    public List<Doctor> findAll(Sort sort) {
        return List.of();
    }


    public Page<Doctor> findAll(Pageable pageable) {
        return null;
    }
}
