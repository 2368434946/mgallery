package com.imooc.mgallery.service;

import java.util.List;

import com.imooc.mgallery.dao.PaintingDao;
import com.imooc.mgallery.entity.Painting;
import com.imooc.mgallery.utils.PageModel1;

public class PaintingService {
        private PaintingDao paintingDao = new PaintingDao();
        
        public PageModel1 pagination(int page,int rows,String...category) {
        	
        	if(rows == 0) {
        		throw new RuntimeException("无效的rows参数");
        	}
        	if(category.length==0 || category[0]==null) {
        		return paintingDao.pagination(page, rows);
        	}else {
        		return paintingDao.pagination(Integer.parseInt(category[0]), page, rows);
        	}
        	
        }
        
       
        
       /*
        * 新增油画 
        */
      public void create(Painting painting) {
    	  paintingDao.create(painting);
      }
      
      /**
       * 按编号查询油画
       * @param id  油画编号
       * @return  油画对象
       */
      public Painting findById(Integer id) {
    	  Painting p = paintingDao.findById(id);
    	  if(p==null) {
    		  throw  new RuntimeException("[id=" +id +"]油画不存在");
    	  }
    	  return p;
      }
      /**
       * 更新油画
       * @param newPainting 新的油画数据
       * @param isPreviewModified 是否修改Preview属性
       */
      public void update(Painting newPainting,Integer isPreviewModified) {
    	  Painting oldPainting = this.findById(newPainting.getId());
    	  oldPainting.setPname(newPainting.getPname());
    	  oldPainting.setCategory(newPainting.getCategory() );
    	  oldPainting.setPrice(newPainting.getPrice());
    	  oldPainting.setDescription(newPainting.getDescription());
    	  if(isPreviewModified == 1) {
    		  oldPainting.setPreview(newPainting.getPreview());
    	  }
    	  paintingDao.update(oldPainting);
    	
      }
      /**
       * 删除油画
       * @param id
       */
      public void delete(Integer id) {
    	  paintingDao.delete(id);
      }
      
      
      public static void main(String[] args) {
		PaintingService paintingService = new PaintingService();
		PageModel1 pageModel = paintingService.pagination(2, 6);
		List<Painting> paintingList = pageModel.getPageData();
		for(Painting painting :paintingList) {
			System.out.println(painting.getPname());
		}
		System.out.println(pageModel.getPageStartRow()+ ":"+pageModel.getPageEndRow());
	}
}
