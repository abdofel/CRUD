package com.example.demo.dao;

import com.example.demo.entities.Patient;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface PatientRepository extends JpaRepository <Patient, Long>{
    public Page<Patient> findByNameContains(String mc, Pageable pageable);
}
