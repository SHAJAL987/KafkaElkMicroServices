package com.elastic_kafka.api.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.elastic_kafka.api.helper.Indices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = Indices.EMPLOYEES_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Employees {
	@Id
	@Field(type = FieldType.Text)
	private String EMPLOYEE_ID;
	@Field(type = FieldType.Text)
	private String FIRST_NAME;
	@Field(type = FieldType.Text)
	private String LAST_NAME;
	@Field(type = FieldType.Keyword)
	private String EMAIL;
	@Field(type = FieldType.Keyword)
	private String PHONE_NO;
	@Field(type = FieldType.Text)
	private String HIRE_DATE;
	@Field(type = FieldType.Keyword)
	private String JOB_ID;
	@Field(type = FieldType.Text)
	private String SALARY;
	@Field(type = FieldType.Text)
	private String COMMISSION_PCT;
	@Field(type = FieldType.Text)
	private String MANAGER_ID;
	@Field(type = FieldType.Text)
	private String DEPARTMENT_ID;
	public String getEMPLOYEE_ID() {
		return EMPLOYEE_ID;
	}
	public void setEMPLOYEE_ID(String eMPLOYEE_ID) {
		EMPLOYEE_ID = eMPLOYEE_ID;
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}
	public String getLAST_NAME() {
		return LAST_NAME;
	}
	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getPHONE_NO() {
		return PHONE_NO;
	}
	public void setPHONE_NO(String pHONE_NO) {
		PHONE_NO = pHONE_NO;
	}
	public String getHIRE_DATE() {
		return HIRE_DATE;
	}
	public void setHIRE_DATE(String hIRE_DATE) {
		HIRE_DATE = hIRE_DATE;
	}
	public String getJOB_ID() {
		return JOB_ID;
	}
	public void setJOB_ID(String jOB_ID) {
		JOB_ID = jOB_ID;
	}
	public String getSALARY() {
		return SALARY;
	}
	public void setSALARY(String sALARY) {
		SALARY = sALARY;
	}
	public String getCOMMISSION_PCT() {
		return COMMISSION_PCT;
	}
	public void setCOMMISSION_PCT(String cOMMISSION_PCT) {
		COMMISSION_PCT = cOMMISSION_PCT;
	}
	public String getMANAGER_ID() {
		return MANAGER_ID;
	}
	public void setMANAGER_ID(String mANAGER_ID) {
		MANAGER_ID = mANAGER_ID;
	}
	public String getDEPARTMENT_ID() {
		return DEPARTMENT_ID;
	}
	public void setDEPARTMENT_ID(String dEPARTMENT_ID) {
		DEPARTMENT_ID = dEPARTMENT_ID;
	}

	
	
	
	
	
	
}
