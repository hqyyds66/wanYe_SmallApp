package com.mindor.serivce.serviceimpl;

import java.util.List;
import java.util.Map;

import com.mindor.dao.Productdao;
import com.mindor.entity.Equipment;
import com.mindor.serivce.ProductService;

public class ProductServiceimpl implements ProductService {

	private Productdao productdao;

	public Productdao getProductdao() {
		return productdao;
	}

	public void setProductdao(Productdao productdao) {
		this.productdao = productdao;
	}

	public List<Object> selectProductById(String sql) {
		// TODO Auto-generated method stub
		List<Object> productList = null;
		productList = productdao.selectProductById(sql);
		return productList;
	}

	public List<Object> selectProduct(int start, int pageSize) {
		List<Object> productList = null;
		productList = productdao.selectProduct(start, pageSize);
		return productList;
	}

	@Override
	public int selectProductList() {
		// TODO Auto-generated method stub
		int size = 0;
		size = productdao.selectProductList();
		return size;
	}

	@Override
	public List<Object> selectProductAll() {
		// TODO Auto-generated method stub
		List<Object> productList = null;
		productList = productdao.selectProductAll();
		return productList;
	}

	@Override
	public List<Object> selectProduct() {
		// TODO Auto-generated method stub
		List<Object> productList = null;
		productList = productdao.selectProduct();
		return productList;
	}

	@Override
	public List<Object> selectProducts(String sql, int start, int pageSize) {
		// TODO Auto-generated method stub
		List<Object> productList = null;
		productList = productdao.selectProducts(sql, start, pageSize);
		return productList;
	}

	@Override
	public List<String> equipmentIdList(String hql) {
		// TODO Auto-generated method stub
		List<String> equipmentIdList = null;
		equipmentIdList = productdao.equipmentIdList(hql);
		return equipmentIdList;
	}

	@Override
	public Equipment selecteEquipmentById(String equipmentId) {
		// TODO Auto-generated method stub
		Equipment equipment = new Equipment();
		equipment = productdao.selecteEquipmentById(equipmentId);
		return equipment;
	}

	@Override
	public List<Object> selectProductBysql(String sql) {
		// TODO Auto-generated method stub
		return productdao.selectProductBysql(sql);
	}

	@Override
	public String equVersion(String equipmentId) {
		// TODO Auto-generated method stub
		return productdao.equVersion(equipmentId);
	}

	@Override
	public void updProductById(String productId, String softVersion,
			String versionCont) {
		// TODO Auto-generated method stub
		productdao.updProductById(productId, softVersion, versionCont);
	}

}
