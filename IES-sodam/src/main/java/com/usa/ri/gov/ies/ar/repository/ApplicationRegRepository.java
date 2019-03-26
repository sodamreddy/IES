package com.usa.ri.gov.ies.ar.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usa.ri.gov.ies.ar.entity.ApplicationEntity;

public interface ApplicationRegRepository extends JpaRepository<ApplicationEntity, Serializable> {

}
