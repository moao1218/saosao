package cn.saosao.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.saosao.pojo.Clerk;
import cn.saosao.service.Finance.DaiZhiFuServer;
import cn.saosao.service.Finance.InsurancePolicyServer;

/**
 * 审阅投保单，进行退款操作
 * 
 * @author wh
 *
 */
@Controller
public class InsurancePolicyController {
	@Autowired
	InsurancePolicyServer insus;

	@Autowired
	DaiZhiFuServer dzfs;

	@RequestMapping("InsurancePolicy")
	public String InsurancePolicy(HttpServletRequest request,
			@RequestParam(value = "red", defaultValue = "1") int red) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> attribute = (Map<String, Object>) request.getSession().getAttribute("shuju");
		if (attribute != null) {
			/*
			 * for (Entry<String, Object> entrySet : attribute.entrySet()) {
			 * System.out.println(entrySet.getKey()+":"+entrySet.getValue()); }
			 */
			map = attribute;
		} else {
			map.put("policyid", null);
			map.put("holder_name", null);
			map.put("holder_card", null);
		}
		PageHelper.startPage(red, 5);
		List<Map<String, Object>> findAll = insus.findAll(map);
		PageInfo po = new PageInfo(findAll, 5);

		for (int i = 0; i < findAll.size(); i++) {
			if (findAll.get(i).get("SECOND_AUDITOR") == null) {// 保单二审
				findAll.get(i).put("SECOND_AUDITOR", "");
			}
			if (findAll.get(i).get("THIRD_AUDITOR") == null) {// 保单三审
				findAll.get(i).put("THIRD_AUDITOR", "");
			}
		}

		request.setAttribute("list", po);
		request.setAttribute("huixian", map);
		return "backPage/Finance/InsurancePolicy";

	}

	@RequestMapping("InsurancePolicy2")
	public String InsurancePolicy2(HttpServletRequest request,

			@RequestParam(value = "policyid", defaultValue = "") String policyid,
			@RequestParam(value = "holder_name", defaultValue = "") String holder_name,
			@RequestParam(value = "holder_card", defaultValue = "") String holder_card) {
		int red = 1;
		System.out.println("policyid" + policyid + "::" + "holder_name:" + holder_name + "holder_card:" + holder_card);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("policyid", policyid);
		map.put("holder_name", holder_name);
		map.put("holder_card", holder_card);
		request.getSession().setAttribute("shuju", map);
		PageHelper.startPage(red, 5);
		List<Map<String, Object>> findAll = insus.findAll(map);
		PageInfo po = new PageInfo(findAll, 5);

		for (int i = 0; i < findAll.size(); i++) {
			if (findAll.get(i).get("SECOND_AUDITOR") == null) {// 保单二审
				findAll.get(i).put("SECOND_AUDITOR", "");
			}
			if (findAll.get(i).get("THIRD_AUDITOR") == null) {// 保单三审
				findAll.get(i).put("THIRD_AUDITOR", "");
			}
		}

		request.setAttribute("list", po);
		request.setAttribute("huixian", map);
		return "backPage/Finance/InsurancePolicy";

	}

	@ResponseBody
	@RequestMapping("zhuanz")
	public Map<String, String> zhuanz(HttpServletRequest request, HttpServletResponse response) {
		int cg = 0, shibai = 0;

		String parameter = request.getParameter("empNames");
		System.out.println("parameter" + parameter);
		String[] split = parameter.split(",");
		if (split.length == 0 || split[0].equals("")) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("code", "100");
			m.put("yuanying", "对象为空");
			return m;
		}

		for (int i = 0; i < split.length; i++) {
			List<Map<String, Object>> selectByidall = insus.selectByidall(split[i]);
			if (selectByidall.size() != 1) {// 查询出来的对象不止一条或者没有
				shibai++;
				continue;
			}
			Object object = selectByidall.get(0).get("BANK_CARD");
			if (object == null) {// 银行卡为空
				shibai++;
				continue;
			}

			Clerk attribute = (Clerk) request.getSession().getAttribute("clerk");
			if (attribute == null) {
				try {
					// 返回登录
					request.getRequestDispatcher("/").forward(request, response);
					return null;
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			if (zhuan(object.toString())) {
				// 插入资金明细表
				Map<String, Object> map = new HashMap<String, Object>();
				for (Entry<String, Object> entrySet : selectByidall.get(0).entrySet()) {

					map.put("ACCOUNTID", System.currentTimeMillis());// 时间戳

					map.put("CLAIMID", "");// 理赔单号
					map.put("POLICYID", split[i]);// 保单号
					if (entrySet.getKey().equals("PREMIUM"))
						map.put("MONEY", entrySet.getValue());// 金额
					else if (entrySet.getKey().equals("USERID"))
						map.put("USERID", entrySet.getValue());// 用户id
					else if (entrySet.getKey().equals("HOLDER_CARD"))
						map.put("USER_CARD", entrySet.getValue());// 身份证
					else if (entrySet.getKey().equals("BANK_CARD"))
						map.put("BANC_CARD", entrySet.getValue());// 银行卡
					map.put("ACCOUNTANT", attribute.getUsername());// 操作人username
					map.put("TRANSFERTIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));// 操作时间
					map.put("ACCOUNT_STATUS", "1");// 转态
					map.put("REMARKS", "投保单退款转账");// 备注
					map.put("YULIU", "");// 预留字段

				}

				// 模拟成功之后开始改变表状态
				insus.updateByidtozhaungtai(split[i]);
				// 插入资金明细表
				dzfs.insertall(map);
				cg++;
			}

		}

		Map<String, String> m = new HashMap<String, String>();
		m.put("code", "200");
		m.put("yuanying", "");
		m.put("shibai", shibai + "");
		m.put("cg", cg + "");
		return m;

	}

	// 模拟转账 对方银行卡
	public boolean zhuan(String yhk) {

		return true;
	}

}
