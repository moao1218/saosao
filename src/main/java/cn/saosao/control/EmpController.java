package cn.saosao.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.saosao.mapper.EmpMapper;
import cn.saosao.pojo.Emp;


@RestController
public class EmpController {
	
	@Autowired
	private EmpMapper empMapper;

	@GetMapping(value="/emp/{id}")
	public Emp	findEmpById(@PathVariable("id")Integer id) {
		return empMapper.getEmpById(id);
		
	}
}
