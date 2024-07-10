package ru.curs.Clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.curs.Clinic.entity.Talon;

@Repository
public interface TalonRepo extends JpaRepository<Talon, Integer>, JpaSpecificationExecutor<Talon> {
}