package co.yeadam.project.employee.service;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeVO {
 public EmployeeVO(String id, String pw, String name, String phone) {
		this.empId = id;
		this.empPw = pw;
		this.empName = name;
		this.empPhone = phone;
	}
 
 public EmployeeVO() {
	 
 }
private String empId;
 private String empPw;
 private String empName;
 private String empPhone;
 private String empLevel;
 private Date hireDate;
}
