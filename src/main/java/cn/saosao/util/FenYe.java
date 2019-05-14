package cn.saosao.util;

import java.io.Serializable;
import java.util.List;

/**
 * 该类用于 财务模块分页使用， 保存了一些分页所需要的参数
 * 
 * @author lenovo
 *
 */
public class FenYe implements Serializable{

	boolean isshang;// 是否有上一页
	boolean isxia; // 是否有下一页

	List<Integer> lie;// 列表里面保存中间现实的数字12345，最多五个

	int dangqian;// 标记当前页码
	int zong;// 一共有多少页码
	int zsj;// 一共有多少数据
	int xsh;// 每页显示多少行
	
	
	@Override
	public String toString() {
		return "FenYe [isshang=" + isshang + ", isxia=" + isxia + ", lie=" + lie + ", dangqian=" + dangqian + ", zong="
				+ zong + ", zsj=" + zsj + ", xsh=" + xsh + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public boolean isIsshang() {
		return isshang;
	}
	public void setIsshang(boolean isshang) {
		this.isshang = isshang;
	}
	public boolean isIsxia() {
		return isxia;
	}
	public void setIsxia(boolean isxia) {
		this.isxia = isxia;
	}
	public List<Integer> getLie() {
		return lie;
	}
	public void setLie(List<Integer> lie) {
		this.lie = lie;
	}
	public int getDangqian() {
		return dangqian;
	}
	public void setDangqian(int dangqian) {
		this.dangqian = dangqian;
	}
	public int getZong() {
		return zong;
	}
	public void setZong(int zong) {
		this.zong = zong;
	}
	public int getZsj() {
		return zsj;
	}
	public void setZsj(int zsj) {
		this.zsj = zsj;
	}
	public int getXsh() {
		return xsh;
	}
	public void setXsh(int xsh) {
		this.xsh = xsh;
	}

	
	
	
	
}
