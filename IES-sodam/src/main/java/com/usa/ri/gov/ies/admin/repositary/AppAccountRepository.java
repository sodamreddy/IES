package com.usa.ri.gov.ies.admin.repositary;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.usa.ri.gov.ies.admin.entity.AppAccountEntity;

public interface AppAccountRepository extends JpaRepository<AppAccountEntity, Serializable> {

	@Query(name="from AppAccountEntity where emailId=:email")
	public AppAccountEntity checkEmail(String email);
 
	
}
