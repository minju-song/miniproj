package co.yeadam.project.employee.service;

import java.util.List;

public interface EmployeeService {
	List<EmployeeVO> employeeSelectList();
	EmployeeVO employeeSelect(EmployeeVO emp);
	int employeeUpdate(EmployeeVO emp);
	int employeeInsert(EmployeeVO emp);
	int employeeInsertKing(EmployeeVO emp);
	int employeDelete(EmployeeVO emp);
	int employeeSell(EmployeeVO emp);
}
