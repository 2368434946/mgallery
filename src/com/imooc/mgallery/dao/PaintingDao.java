package com.imooc.mgallery.dao;

import java.util.ArrayList;
import java.util.List;

import com.imooc.mgallery.entity.Painting;
import com.imooc.mgallery.utils.PageModel1;
import com.imooc.mgallery.utils.XmlDataSource;

//油画数据访问对象
public class PaintingDao {
	
	//分页查询油画数据
	public PageModel1 pagination(int page,int rows) {
		//Painting油画对象集合
		List<Painting> list =XmlDataSource.getRawData();
		PageModel1 pageModel = new PageModel1(list,page,rows);
		return pageModel;
	}
	//按类别分页查询
	public PageModel1 pagination(int category, int page,int rows) {
		List<Painting> list = XmlDataSource.getRawData();
		List<Painting> categoryList = new ArrayList();
		for(Painting p :list) {
			if(p.getCategory() == category) {
				categoryList.add(p);
			}
		}
		PageModel1 pageModel = new PageModel1(categoryList,page,rows);
		return pageModel;
	}
	
	//数据新增
	public void create(Painting painting) {
		XmlDataSource.append(painting );
	}
	//数据修改
	public void update(Painting painting) {
		XmlDataSource.update(painting);
	}
	//数据删除
	public void delete(Integer id) {
		XmlDataSource.delete(id);
	}
	
	
	
	public Painting findById(Integer id) {
		List<Painting> data = XmlDataSource.getRawData();
		Painting painting = null;
		for(Painting p : data) {
			if(p.getId() == id) {
				painting = p;
				break;
			}
		}
		return painting;
	}

}
