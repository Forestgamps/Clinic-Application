package ru.curs.Clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.curs.Clinic.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer>, JpaSpecificationExecutor<Doctor> {
}
