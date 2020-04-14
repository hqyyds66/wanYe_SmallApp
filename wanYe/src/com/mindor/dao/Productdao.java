package com.mindor.dao;

import java.util.List;

import com.mindor.entity.Equipment;

public interface Productdao {
	public List<Object>  query();
	public int deleteProduct(int productId);
	public int updateProduct(String sql);
	public List<Object> selectProductById(String sql);
	public List<Object> selectProduct(int start,int pageSize); 
	public int selectProductList();
	public List<Object> selectProductAll();
	public List<Object> selectProduct(); 
	
	public List<Object> selectProductBysql(String sql);
	public List<Object> selectProducts(String sql,int start,int pageSize);
	public List<String> equipmentIdList(String hql);
	public Equipment selecteEquipmentById(String equipmentId);
	
	public String equVersion(String equipmentId);
	
	public void updProductById(String  productId,String softVersion,String versionCont);
 }
