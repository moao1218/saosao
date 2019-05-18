package cn.saosao.mapper;

import cn.saosao.pojo.Items;
import cn.saosao.pojo.Items_List;

public interface ItemListMapper {
	
	public Items_List getlist(String itemsid);
	
	
	public Items findItemById(Integer id);
}
