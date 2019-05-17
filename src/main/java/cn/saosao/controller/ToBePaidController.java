package cn.saosao.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.saosao.pojo.Clerk;
import cn.saosao.service.Finance.DaiZhiFuServer;

/**
 * 待转账的列表
 * 
 * @author wh
 *
 */
@Controller

public class ToBePaidController {

	@Autowired
	DaiZhiFuServer dzfs;

	int a = 0;

	// 查询不带条件的 待支付的列表
	@RequestMapping("/ToBeCompleted")
	public String Tobepaid(HttpServletRequest request, @RequestParam(value = "adr", defaultValue = "1") Integer adr) {
		if (adr <= 0)
			adr = 1;
		// 三个条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("claimid", null);
		map.put("name", null);
		map.put("sfz", null);
		PageHelper.startPage(adr, 5);
		List<Map<String, Object>> findall = dzfs.findall(map);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		PageInfo po = new PageInfo(findall, 5);
		for (int i = 0; i < findall.size(); i++) {
			Object object = findall.get(i).get("TRANSFER_FAILED");
			if (object == null)
				findall.get(i).put("TRANSFER_FAILED", "");

		}

		// 主体信息
		request.setAttribute("list", po); // 查询的结果
		request.setAttribute("huixian", map); // 查询条件回显
		return "backPage/Finance/ToBePaid";

	}

	// 查询带条件的未支付列表
	@PostMapping("/ToBeCompleted")
	public String Tobepaid2(HttpServletRequest request, @RequestParam(value = "adr", defaultValue = "1") Integer adr,
			@RequestParam(value = "claimid") String claimid, @RequestParam(value = "name") String name,
			@RequestParam(value = "sfz") String sfz) {
		// 如果为空则赋为“”
		if (adr <= 0)// 页码小于0则赋为1
			adr = 1;
		if (claimid == null)
			claimid = "";
		if (name == null)
			name = "";
		if (sfz == null)
			sfz = "";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("claimid", claimid);
		map.put("name", name);
		map.put("sfz", sfz);
		PageHelper.startPage(adr, 5);
		List<Map<String, Object>> findall = dzfs.findall(map);
		for (int i = 0; i < findall.size(); i++) {
			if (findall.get(i).get("THIRD_PIC") == null)
				findall.get(i).put("THIRD_PIC", "");// 第三方照片
			if (findall.get(i).get("SITE_PHOTO") == null)
				findall.get(i).put("SITE_PHOTO", "");// 现场照片
			if (findall.get(i).get("TRANSFER_FAILED") == null)
				findall.get(i).put("TRANSFER_FAILED", "");// 转账失败原因
			if (findall.get(i).get("FINAL_DATE") == null)
				findall.get(i).put("FINAL_DATE", "");// 转账结束时间
			if (findall.get(i).get("UPPER_OPINION") == null)
				findall.get(i).put("UPPER_OPINION", "");// 上一级意见
			if (findall.get(i).get("INITIAL_AMOUNT") == null)
				findall.get(i).put("INITIAL_AMOUNT", "");// 初始金额
			if (findall.get(i).get("ACCOUNTANT") == null)
				findall.get(i).put("ACCOUNTANT", "");// 会计操作人
			if (findall.get(i).get("FINAL_DATE") == null)
				findall.get(i).put("FINAL_DATE", "");// 转账结束时间
			if (findall.get(i).get("FINAL_DATE") == null)
				findall.get(i).put("FINAL_DATE", "");// 转账结束时间
			if (findall.get(i).get("TRANSFER_FAILED") == null)
				findall.get(i).put("TRANSFER_FAILED", "");// 转账结束时间

			/*
			 * for(Entry<String, Object> entrySet : findall.get(i).entrySet()) {
			 * System.out.print(entrySet.getKey()+":"+entrySet.getValue()+"  "); }
			 * System.out.println();
			 */
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		PageInfo po = new PageInfo(findall, 5);

		for (int i = 0; i < po.getList().size(); i++) {
			System.out.println(po.getList().get(i));
		}

		// 主体信息
		request.setAttribute("list", po);
		// 回显
		request.setAttribute("huixian", map);
		return "backPage/Finance/ToBePaid";

	}

	// 开始转账操作
	@ResponseBody
	@RequestMapping("zhuangzhuan")
	public Map<String, String> zhuanzhuang(HttpServletRequest request) {
		int cg = 0; // 转账成功统计
		int shibai = 0; // 转账失败统计

		String parameter = request.getParameter("empNames");
		if (parameter == null)
			parameter = "";
		Map<String, String> t = t(parameter, request);
		return t;
		// 开始的分割
		/*
		 * String[] split = parameter.split(","); //传入为空
		 * if(split[0]==null||split[0].length()==0||split[0].equals("")) { Map<String,
		 * String> m = new HashMap<String, String>(); m.put("code", "100");
		 * m.put("yuanying", "对象为空"); m.put("shibai", shibai+""); m.put("cg", cg+"");
		 * return m; } for (int i = 0; i < split.length; i++) { // 分割完之后开始获取每个信息
		 * List<Map<String, Object>> selectByid = dzfs.selectByid(split[i]); // 一般只会查出一条
		 * if (selectByid.size() != 1) { // 转账失败 shibai++;
		 * dzfs.updateByidtoyuanying(split[i], "理赔号重复"); continue; } //
		 * 但是每笔转账都不能影响其他的转账，所以不能批量操作 Map<String, Object> map = new HashMap<String,
		 * Object>(); // 获取当前操作人 Clerk clerk = (Clerk)
		 * request.getSession().getAttribute("clerk"); if (clerk == null) {// 登录用户为空
		 * shibai++; dzfs.updateByidtoyuanying(split[i], "操作人登录失效"); continue; } // 开始转账
		 * boolean iszhaunz = iszhaunz(); if (!iszhaunz) {// 转账失败 终止本次操作，进入下一次操作
		 * shibai++; dzfs.updateByidtoyuanying(split[i], "银行转账失败"); continue; }
		 * 
		 * // 添加 转账成功数据 for (Entry<String, Object> entrySet :
		 * selectByid.get(0).entrySet()) {
		 * 
		 * map.put("ACCOUNTID", System.currentTimeMillis());// 时间戳 if
		 * (entrySet.getKey().equals("CLAIMID")) map.put("CLAIMID", split[i]);// 理赔单号
		 * map.put("POLICYID", "");// 保单号 if (entrySet.getKey().equals("FINAL_AMOUNT"))
		 * map.put("MONEY", entrySet.getValue());// 金额 else if
		 * (entrySet.getKey().equals("USERID")) map.put("USERID",
		 * entrySet.getValue());// 用户id else if (entrySet.getKey().equals("CLAIM_CARD"))
		 * map.put("USER_CARD", entrySet.getValue());// 身份证 else if
		 * (entrySet.getKey().equals("BANK_CARD")) map.put("BANC_CARD",
		 * entrySet.getValue());// 银行卡 map.put("ACCOUNTANT", clerk.getUsername());//
		 * 操作人username map.put("TRANSFERTIME", new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));// 操作时间
		 * map.put("ACCOUNT_STATUS", "1");// 转态 map.put("REMARKS", "理赔转账支出");// 备注
		 * map.put("YULIU", "");// 预留字段
		 * 
		 * }
		 * 
		 * // 转账成功插入数据 int insertall = dzfs.insertall(map); if (insertall != 0) { cg++;
		 * //改理赔单状态 dzfs.updateByidtozhuangtai(split[i], 28);//28是转账完成状态 } else
		 * shibai++; } Map<String, String> m = new HashMap<String, String>();
		 * m.put("code", "200"); m.put("yuanying", ""); m.put("shibai", shibai+"");
		 * m.put("cg", cg+""); return m;
		 */

	}

	@RequestMapping("xiangxi")
	public String xiangxi(@RequestParam(value = "id", defaultValue = "") String id, HttpServletRequest request) {
		// 非法判断
		if (id == null || id.equals("") || id.equals("undefined")) {
			return "forward:ToBeCompleted";
		}

		List<Map<String, Object>> findByid = dzfs.findByid(id);
		for (int i = 0; i < findByid.size(); i++) {
			if (findByid.get(i).get("THIRD_PIC") == null)
				findByid.get(i).put("THIRD_PIC", "");// 第三方照片
			if (findByid.get(i).get("SITE_PHOTO") == null)
				findByid.get(i).put("SITE_PHOTO", "");// 现场照片
			if (findByid.get(i).get("TRANSFER_FAILED") == null)
				findByid.get(i).put("TRANSFER_FAILED", "");// 转账失败原因
			if (findByid.get(i).get("FINAL_DATE") == null)
				findByid.get(i).put("FINAL_DATE", "");// 转账结束时间
			if (findByid.get(i).get("UPPER_OPINION") == null)
				findByid.get(i).put("UPPER_OPINION", "");// 上一级意见
			if (findByid.get(i).get("INITIAL_AMOUNT") == null)
				findByid.get(i).put("INITIAL_AMOUNT", "");// 初始金额
			if (findByid.get(i).get("ACCOUNTANT") == null)
				findByid.get(i).put("ACCOUNTANT", "");// 会计操作人
			if (findByid.get(i).get("FINAL_DATE") == null)
				findByid.get(i).put("FINAL_DATE", "");// 转账结束时间
			if (findByid.get(i).get("FINAL_DATE") == null)
				findByid.get(i).put("FINAL_DATE", "");// 转账结束时间
			if (findByid.get(i).get("TRANSFER_FAILED") == null)
				findByid.get(i).put("TRANSFER_FAILED", "");// 转账结束时间

			for (Entry<String, Object> entrySet : findByid.get(i).entrySet()) {
				System.out.print(entrySet.getKey() + ":" + entrySet.getValue() + "  ");
			}
			System.out.println();
		}
		// System.out.println(findByid.toString());
		request.setAttribute("xinxi", findByid.get(0));

		return "backPage/Finance/xiangxi";

	}

	// 模拟银行转账 返回boolean 模拟成功
	public boolean iszhaunz() {
		return true;

	}

	public Map<String, String> t(String n, HttpServletRequest request) {
		Map<String, String> zhaun = null;
		synchronized (this) {
			zhaun = zhaun(n, request);
		}
		return zhaun;
	}

	public Map<String, String> zhaun(String n, HttpServletRequest request) {
		int cg = 0; // 转账成功统计
		int shibai = 0; // 转账失败统计

		// 开始的分割
		String[] split = n.split(",");
		// 传入为空
		if (split[0] == null || split[0].length() == 0 || split[0].equals("")) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("code", "100");
			m.put("yuanying", "对象为空");
			m.put("shibai", shibai + "");
			m.put("cg", cg + "");
			return m;
		}
		for (int i = 0; i < split.length; i++) {
			// 分割完之后开始获取每个信息
			List<Map<String, Object>> selectByid = dzfs.selectByid(split[i]);
			/*
			 * 一般只会查出一条，没有查出来可能是因为前段的 id穿错误，或者这个id的转态不是25，就是这个业务被别人操作了
			 */
			if (selectByid.size() != 1) {
				// 转账失败
				shibai++;
				dzfs.updateByidtoyuanying(split[i], "理赔号重复,或者已经被处理");
				continue;
			}
			// 但是每笔转账都不能影响其他的转账，所以不能批量操作
			Map<String, Object> map = new HashMap<String, Object>();
			// 获取当前操作人
			Clerk clerk = (Clerk) request.getSession().getAttribute("clerk");
			if (clerk == null) {// 登录用户为空
				shibai++;
				dzfs.updateByidtoyuanying(split[i], "操作人登录失效");
				continue;
			}
			// 开始转账
			boolean iszhaunz = iszhaunz();
			if (!iszhaunz) {// 转账失败 终止本次操作，进入下一次操作
				shibai++;
				dzfs.updateByidtoyuanying(split[i], "银行转账失败");
				continue;
			}

			// 添加 转账成功数据
			for (Entry<String, Object> entrySet : selectByid.get(0).entrySet()) {

				map.put("ACCOUNTID", System.currentTimeMillis());// 时间戳
				if (entrySet.getKey().equals("CLAIMID"))
					map.put("CLAIMID", split[i]);// 理赔单号
				map.put("POLICYID", "");// 保单号
				if (entrySet.getKey().equals("FINAL_AMOUNT"))
					map.put("MONEY", entrySet.getValue());// 金额
				else if (entrySet.getKey().equals("USERID"))
					map.put("USERID", entrySet.getValue());// 用户id
				else if (entrySet.getKey().equals("CLAIM_CARD"))
					map.put("USER_CARD", entrySet.getValue());// 身份证
				else if (entrySet.getKey().equals("BANK_CARD"))
					map.put("BANC_CARD", entrySet.getValue());// 银行卡
				map.put("ACCOUNTANT", clerk.getUsername());// 操作人username
				map.put("TRANSFERTIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));// 操作时间
				map.put("ACCOUNT_STATUS", "1");// 转态
				map.put("REMARKS", "理赔转账支出");// 备注
				map.put("YULIU", "");// 预留字段

			}

			// 转账成功插入数据
			int insertall = dzfs.insertall(map);
			if (insertall != 0) {
				cg++;
				// 改理赔单状态
				dzfs.updateByidtozhuangtai(split[i], 28);// 28是转账完成状态
			} else
				shibai++;
		}

		Map<String, String> m = new HashMap<String, String>();
		m.put("code", "200");
		m.put("yuanying", "");
		m.put("shibai", shibai + "");
		m.put("cg", cg + "");
		return m;

	}

}
