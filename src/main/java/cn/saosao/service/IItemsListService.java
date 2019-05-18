package cn.saosao.service;

import cn.saosao.pojo.Items;
import cn.saosao.pojo.Items_List;

public interface IItemsListService {
	public Items_List getItem(String itemid);
	
	public Items findItemById(Integer id);
}
