package com.usa.ri.gov.ies.admin.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usa.ri.gov.ies.admin.entity.AppAccountEntity;

@Repository
public interface AppAccountRepository extends JpaRepository<AppAccountEntity,Seralizable > {

}
