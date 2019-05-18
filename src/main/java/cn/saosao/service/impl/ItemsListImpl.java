package cn.saosao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.ItemListMapper;
import cn.saosao.pojo.Items;
import cn.saosao.pojo.Items_List;
import cn.saosao.service.IItemsListService;
@Service
public class ItemsListImpl implements IItemsListService{
	@Autowired
	ItemListMapper itemsListMapper;
	@Override
	public Items_List getItem(String itemid) {
		
		return itemsListMapper.getlist(itemid);
	}
	@Override
	public Items findItemById(Integer id) {
		return itemsListMapper.findItemById(id);
	}

}
