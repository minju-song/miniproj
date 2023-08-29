package co.yeadam.project.employee.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yeadam.project.common.DataSource;
import co.yeadam.project.employee.mapper.EmployeeMapper;
import co.yeadam.project.employee.service.EmployeeService;
import co.yeadam.project.employee.service.EmployeeVO;

public class EmployeeServiceImpl implements EmployeeService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private EmployeeMapper map = sqlSession.getMapper(EmployeeMapper.class);

	@Override
	public EmployeeVO employeeSelect(EmployeeVO emp) {
		// TODO Auto-generated method stub
		return map.employeeSelect(emp);
	}

	@Override
	public int employeeUpdate(EmployeeVO emp) {
		return map.employeeUpdate(emp);
	}

	@Override
	public int employeeInsert(EmployeeVO emp) {
		return map.employeeInsert(emp);
	}

	@Override
	public int employeDelete(EmployeeVO emp) {
		return map.employeDelete(emp);
	}

	@Override
	public List<EmployeeVO> employeeSelectList() {
		return map.employeeSelectList();
	}

	@Override
	public int employeeSell(EmployeeVO emp) {
		return map.employeeSell(emp);
	}

	@Override
	public int employeeInsertKing(EmployeeVO emp) {
		return map.employeeInsertKing(emp);
	}

}
