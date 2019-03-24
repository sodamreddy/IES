package com.usa.ri.gov.ies.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * this class represents plan Details to DB table
 * @author sodam
 *
 */

@Entity
@Table(name="Plans_Master")
public class PlanEntity {
	@Id
	@GeneratedValue
	@Column(name="Plan_Id")
	private int planId;
	
	@Column(name="Plan_Name",unique=true)
	private String planName;
	
	@Column(name="Plan_Desc")
	private String planDesc;
	
	@Column(name="Plan_Start")
	private String planStart;
	
	@Column(name="Plan_End")
	private String planEnd;
	
	@Column(name="Active_Sw")
	private String activeSw;
	
	@Column(name="Created_By")
	private String createdBy;
	
	@Column(name="Updated_By")
	private String UpdatedBy;
	
	
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int
			planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanDesc() {
		return planDesc;
	}
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	public String getPlanStart() {
		return planStart;
	}
	public void setPlanStart(String planStart) {
		this.planStart = planStart;
	}
	public String getPlanEnd() {
		return planEnd;
	}
	public void setPlanEnd(String planEnd) {
		this.planEnd = planEnd;
	}
	public String getActiveSw() {
		return activeSw;
	}
	public void setActiveSw(String activeSw) {
		this.activeSw = activeSw;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return UpdatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}
}
