package com.elasticService.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "employees")
public class Employees {
	@Id
	@Field(type = FieldType.Text, name = "EMPLOYEE_ID")
    private String employeeId;

    @Field(type = FieldType.Text, name = "FIRST_NAME")
    private String firstName;

    @Field(type = FieldType.Text, name = "LAST_NAME")
    private String lastName;
    
    @Field(type = FieldType.Text, name = "EMAIL")
    private String email;
    
    @Field(type = FieldType.Text, name = "PHONE_NUMBER")
    private String phoneNumber;
    
    @Field(type = FieldType.Text, name = "HIRE_DATE")
    private String hireDate;
    
    @Field(type = FieldType.Text, name = "JOB_ID")
    private String jobId;
    
    @Field(type = FieldType.Text, name = "SALARY")
    private String salary;
    
    @Field(type = FieldType.Text, name = "COMMISSION_PCT")
    private String commissionPct;
    
    @Field(type = FieldType.Text, name = "MANAGER_ID")
    private String managerId;
    
    @Field(type = FieldType.Text, name = "DEPARTMENT_ID")
    private String departmentId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(String commissionPct) {
		this.commissionPct = commissionPct;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
    
}
