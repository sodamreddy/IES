package com.usa.ri.gov.ies.ar.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usa.ri.gov.ies.ar.entity.ApplicationEntity;

public interface ApplicationRegRepository extends JpaRepository<ApplicationEntity, Serializable> {
	@Query(name="form ApplicationEntity where ssn:ssn")
	public ApplicationEntity findBySsn(long ssn);
}
