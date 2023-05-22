package com.vn.tpbank.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTMENT_SEQ")
	@SequenceGenerator(name = "DEPARTMENT_SEQ", sequenceName = "DEPARTMENT_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Long departmentId;

	@Column(name = "department_name")
	private String departmentName;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Operator> operatorList;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<SchedulePlan> schedulePlanList;

	public Department() {

	}

	public Department(Long departmentId, String departmentName, List<Operator> operatorList,
			List<SchedulePlan> schedulePlanList) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.operatorList = operatorList;
		this.schedulePlanList = schedulePlanList;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Operator> getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(List<Operator> operatorList) {
		this.operatorList = operatorList;
	}

	public List<SchedulePlan> getSchedulePlanList() {
		return schedulePlanList;
	}

	public void setSchedulePlanList(List<SchedulePlan> schedulePlanList) {
		this.schedulePlanList = schedulePlanList;
	}

}
