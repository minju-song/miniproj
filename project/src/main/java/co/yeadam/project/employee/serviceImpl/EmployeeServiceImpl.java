package co.yeadam.project.employee.serviceImpl;

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

}
