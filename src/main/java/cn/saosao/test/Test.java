package cn.saosao.test;

import org.springframework.beans.factory.annotation.Autowired;

import cn.saosao.pojo.Coverage;
import cn.saosao.service.ICoverageService;
import cn.saosao.service.impl.CoverageServiceImpl;

public class Test {
	public static void main(String[] args) {
		ICoverageService cover=new CoverageServiceImpl();
		Coverage coverageById = cover.getCoverageById("101");
		System.out.println(coverageById);
	}
	
}
