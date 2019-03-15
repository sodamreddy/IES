package com.usa.ri.gov.ies.admin.repositary;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.usa.ri.gov.ies.admin.entity.PlanEntity;

@Repository
public interface PlanAccountRepository extends JpaRepository<PlanEntity, Serializable> {
	
	
	@Query(name="from PlanEntity where planName=:planName")
	public PlanEntity findByPlanName(String planName);
}
