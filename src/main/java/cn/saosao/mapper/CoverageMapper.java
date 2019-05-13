package cn.saosao.mapper;

import cn.saosao.pojo.Coverage;

public interface CoverageMapper {
	/**通过id获取险种
	 * 
	 * @param id
	 * @return
	 */
	public Coverage getCoverageById(String coverageid);
}
