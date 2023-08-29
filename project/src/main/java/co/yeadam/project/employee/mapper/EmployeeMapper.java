package co.yeadam.project.employee.mapper;

import java.util.List;

import co.yeadam.project.employee.service.EmployeeVO;

public interface EmployeeMapper {
	EmployeeVO employeeSelect(EmployeeVO emp);
	int employeeUpdate(EmployeeVO emp);
	int employeeInsert(EmployeeVO emp);
	int employeeInsertKing(EmployeeVO emp);
	int employeDelete(EmployeeVO emp);
	List<EmployeeVO> employeeSelectList();
	int employeeSell(EmployeeVO emp);
}
