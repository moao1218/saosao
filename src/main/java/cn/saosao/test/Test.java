package cn.saosao.test;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import cn.saosao.pojo.Coverage;
import cn.saosao.service.ICoverageService;
import cn.saosao.service.impl.CoverageServiceImpl;
import cn.saosao.util.IDUtil;

public class Test {
	public static void main(String[] args) throws IOException {
		IDUtil id=new IDUtil();
		System.out.println(id.getTest("36252619970127051X", "彭晨"));
	}
	
}
