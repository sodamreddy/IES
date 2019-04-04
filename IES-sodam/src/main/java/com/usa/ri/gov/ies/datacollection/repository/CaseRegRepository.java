package com.usa.ri.gov.ies.datacollection.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usa.ri.gov.ies.datacollection.entity.ApplicantDetailsEntity;
@Repository
public interface CaseRegRepository extends JpaRepository<ApplicantDetailsEntity, Serializable> {

}
