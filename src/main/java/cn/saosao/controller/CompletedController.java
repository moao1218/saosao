package cn.saosao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.saosao.pojo.Account;
import cn.saosao.pojo.DuoTiaoJIan;
import cn.saosao.service.Finance.ExpenditureServer;
import cn.saosao.util.FenYe;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 理赔模块 处理查看已完成的理赔单 类 和没有处理的理赔单的信息，
 * 
 * @author wh
 *
 */
@Controller
public class CompletedController {
	@Autowired
	ExpenditureServer exs;

	
	
	// 查看支出信息
	@SuppressWarnings("unused")
	@GetMapping("/Completed")
	public String zhichu(HttpServletRequest request, HttpServletResponse response, DuoTiaoJIan d,
			@RequestParam(value = "adree", defaultValue = "1") int adree) {
		DuoTiaoJIan d1=null;
		if(adree!=-1)
			d1 = (DuoTiaoJIan) request.getSession().getAttribute("fenyexinxi");
		if (d1 != null)
			d = d1;
		System.out.println(d.toString());
		
		
		Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		if (adree<=0) adree=1;
		int findcount = exs.findcount();//获取总数据
		if (findcount % 5 != 0) {// 不等于0就是总数据不是五的整数
			findcount = findcount / 5 + 1;
		} else {
			findcount /= 5;
		}
		if(adree>=findcount)adree=findcount;
		m.put("cp", adree);
		m.put("ps", 5);
		m.put("acc", d.acc);
		m.put("claimid", d.claimid);
		m.put("policyid", d.policyid);
		m.put("cname", d.cname);
		m.put("moneymin", d.moneymin);
		m.put("moneymax", d.moneymax);
		m.put("accountant", d.accountant);
		m.put("accounts", l);
		m.put("zys", 0);// 记录总数据

		// 获得每页五个的数据，就是中间显示的物体
		List<Map<String, Object>> finAll = exs.finAll(m);
		Object object = m.get("zys");
		Integer zys = Integer.valueOf(object.toString());

		

		request.setAttribute("accountlist", finAll);
		System.out.println(finAll.size());
		/*
		 * for (int i = 0; i < finAll.size(); i++) { for (Entry<String, Object> m1 :
		 * finAll.get(i).entrySet()) { System.out.println(m1.getKey() + "::" +
		 * m1.getValue()); } }
		 */

		// 获取右下角的页码
		FenYe huoqu = huoqu(zys,adree);
		request.setAttribute("fenye", huoqu);
		
		//条件查询回显
		request.setAttribute("huixian", d);
		
		
		return "backPage/Finance/Expenditure";
	}

	// 查看支出信息
	@SuppressWarnings("unused")
	@PostMapping("/Completed2")
	public String zhichu2(HttpServletRequest request, HttpServletResponse response, DuoTiaoJIan d) {

		request.getSession().setAttribute("fenyexinxi", d);

		System.out.println(d.toString());
		Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		
		m.put("cp", 1);
		m.put("ps", 5);
		m.put("acc", d.acc);
		m.put("claimid", d.claimid);
		m.put("policyid", d.policyid);
		m.put("cname", d.cname);
		m.put("moneymin", d.moneymin);
		m.put("moneymax", d.moneymax);
		m.put("accountant", d.accountant);
		m.put("accounts", l);
		m.put("zys", 0);// 记录总数据

		// 获得每页五个的数据，就是中间显示的物体
		List<Map<String, Object>> finAll = exs.finAll(m);
		Object object = m.get("zys");
		Integer zys = Integer.valueOf(object.toString());

		request.setAttribute("accountlist", finAll);
		
		// 获取右下角的页码
		FenYe huoqu2 = huoqu2(zys);
		request.setAttribute("fenye", huoqu2);
		
		//回显
		//条件查询回显
		request.setAttribute("huixian", d);
				
		return "backPage/Finance/Expenditure";
	}

	

	/**
	 * 
	 * @param zsj 总数据 很明显调用这个方法的都是第一页
	 * @return
	 */
	public FenYe huoqu2(int zsj) {
		int zys = 0;
		if (zsj % 5 != 0) {// 不等于0就是总数据不是五的整数
			zys = zsj / 5 + 1;
		} else {
			zys /= 5;
		}
		FenYe f = new FenYe();
		f.setXsh(5);// 行数据
		f.setZsj(zsj);// 总数据
		f.setIsshang(false);// 是否有上一行
		f.setIsxia(true);
		f.setDangqian(1);
		f.setZong(zys);// 总页数
		f.setZsj(zsj);// 总数据
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= zys; i++) {
			list.add(i);
		}
		f.setLie(list);

		return f;
	}

	/**
	 * 
	 * @param zsj    总数据
	 * @param adress 当前页
	 * @return
	 */
	public FenYe huoqu(int zsj, int adress) {
		int zys = 0;
		if (zsj % 5 != 0) {// 不等于0就是总数据不是五的整数
			zys = zsj / 5 + 1;
		} else {
			zys /= 5;
		}
		FenYe f = new FenYe();
		f.setXsh(5);// 行数据
		f.setZsj(zsj);// 总数据
		if (adress == 1)
			f.setIsshang(false);// 是否有上一行
		else
			f.setIsshang(true);// 是否有上一行
		if (adress == zys)
			f.setIsxia(false);// 是否有下一行
		else
			f.setIsxia(true);// 是否有下一行
		f.setDangqian(adress);
		f.setZong(zys);// 总页数
		f.setZsj(zsj);// 总数据
		List<Integer> list = new ArrayList<Integer>();
		if(adress-2>0) {	//1,2,3,4,5
			int a=adress-2;
			for(int i = 1,j=a;i<=5&&j<=zys;i++,j++) {
				list.add(i);
			}
		}else {//123
			for(int i = 1,j=1;i<=5&&j<=zys;i++,j++) {
				list.add(i);
			}
		}
		f.setLie(list);
		return f;
	}
	
	
	
	// 处理待完成的理赔单
		@GetMapping("/ToBeCompleted")
		public String ToBeCompleted() {
			
			
			
			return "";
		}
}
