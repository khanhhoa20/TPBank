package com.vn.tpbank.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "schedule_plan")
public class SchedulePlan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_PLAN_SEQ")
	@SequenceGenerator(name = "SCHEDULE_PLAN_SEQ", sequenceName = "SCHEDULE_PLAN_SEQ", allocationSize = 1)
	private long id;

	@Column(name = "scheduleplandetail_info")
	private String scheduleplandetail_info;

	@Column(name = "scheduleplan_description")
	private String scheduleplan_description;

	@Column(name = "scheduleplan_name")
	private String scheduleplan_name;

	@Column(name = "startdate")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "enddate")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "createdate")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;

	public SchedulePlan() {

	}

	public SchedulePlan(long id, String scheduleplandetail_info, String scheduleplan_description,
			String scheduleplan_name, Date startDate, Date endDate, Date createDate, Department department) {
		super();
		this.id = id;
		this.scheduleplandetail_info = scheduleplandetail_info;
		this.scheduleplan_description = scheduleplan_description;
		this.scheduleplan_name = scheduleplan_name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
		this.department = department;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getScheduleplandetail_info() {
		return scheduleplandetail_info;
	}

	public void setScheduleplandetail_info(String scheduleplandetail_info) {
		this.scheduleplandetail_info = scheduleplandetail_info;
	}

	public String getScheduleplan_description() {
		return scheduleplan_description;
	}

	public void setScheduleplan_description(String scheduleplan_description) {
		this.scheduleplan_description = scheduleplan_description;
	}

	public String getScheduleplan_name() {
		return scheduleplan_name;
	}

	public void setScheduleplan_name(String scheduleplan_name) {
		this.scheduleplan_name = scheduleplan_name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
