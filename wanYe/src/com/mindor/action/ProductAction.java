package com.mindor.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;
import org.apache.commons.io.FileUtils;

import com.mindor.entity.Product;
import com.mindor.entity.Product_equipment02;
import com.mindor.serivce.ProductService;

public class ProductAction {

	private ProductService productService;
	Product product = new Product();

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String updateVersion() {
		return "updateVersion";
	}

	private File uploadfile; // 得到上传的文件
	private File uploadfile2; // 得到上传的文件
	private String uploadfileContentType; // 得到文件的类型
	private String uploadfile2ContentType; // 得到文件的类型
	private String uploadfileFileName; // 得到文件的名称
	private String uploadfile2FileName; // 得到文件的名称

	/*
	 * 获取所有产品信息
	 */

	public String getUploadfile2ContentType() {
		return uploadfile2ContentType;
	}

	public void setUploadfile2ContentType(String uploadfile2ContentType) {
		this.uploadfile2ContentType = uploadfile2ContentType;
	}

	public String getUploadfile2FileName() {
		return uploadfile2FileName;
	}

	public void setUploadfile2FileName(String uploadfile2FileName) {
		this.uploadfile2FileName = uploadfile2FileName;
	}

	public File getUploadfile2() {
		return uploadfile2;
	}

	public void setUploadfile2(File uploadfile2) {
		this.uploadfile2 = uploadfile2;
	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getUploadfileContentType() {
		return uploadfileContentType;
	}

	public void setUploadfileContentType(String uploadfileContentType) {
		this.uploadfileContentType = uploadfileContentType;
	}

	public String getUploadfileFileName() {
		return uploadfileFileName;
	}

	public void setUploadfileFileName(String uploadfileFileName) {
		this.uploadfileFileName = uploadfileFileName;
	}

	@SuppressWarnings("rawtypes")
	public void productList() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		int ListSize;
		int start = 0;
		int pageSize = 10;

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		String startString = ServletActionContext.getRequest().getParameter(
				"start");
		String pageCountString = ServletActionContext.getRequest()
				.getParameter("pageSize");
		if (startString != null) {
			start = Integer.parseInt(startString);
		}
		if (pageCountString != null) {
			pageSize = Integer.parseInt(pageCountString);
		}
		start = start + 1;

		ListSize = productService.selectProductList();
		List<Object> productList = productService
				.selectProduct(start, pageSize);
		Iterator it = productList.iterator();
		JSONArray jsonObject = null;

		Product_equipment02 product_equipment02 = null;// 创建实体类
		List<Product_equipment02> mylist = new LinkedList<Product_equipment02>();
		while (it.hasNext()) {
			System.out.println("12122");
			Product product = (Product) it.next();

			// System.out.println(tuple[0]);
			//		     
			// product=(Product) tuple[0];
			product_equipment02 = new Product_equipment02();

			product_equipment02.setProductId(String.valueOf(product
					.getProductId()));
			product_equipment02.setProductName(String.valueOf(product
					.getProductName()));
			product_equipment02.setIndustry(String.valueOf(product
					.getIndustry()));
			product_equipment02.setCategory(String.valueOf(product
					.getCategory()));
			product_equipment02.setAgreement(String.valueOf(product
					.getAgreement()));
			product_equipment02.setDate(String.valueOf(product.getDate()));
			product_equipment02.setSoftVersion(String.valueOf(product
					.getSoftVersion()));
			product_equipment02.setVersion_updateTime(String.valueOf(product
					.getVersion_updateTime()));
			mylist.add(product_equipment02);
		}
		jsonObject = JSONArray.fromObject(mylist, jsonConfig);// 转json

		if (productList.size() > 0) {
			json.put("productList", jsonObject);
			json.put("ListSize", ListSize);
			out.print(json);
			out.flush();
			out.close();
		} else {
			json.put("productList", 0);
			json.put("ListSize", 0);
			out.print(json);
			out.flush();
			out.close();
		}
	}

	public void productListById() throws IOException {
		JSONObject json = new JSONObject();
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		JSONArray jsonObject = null;

		// 提供了一个过滤作用，如果遇到关联的对象时他会自动过滤掉，不去执行关联关联所关联的对象。
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "equipment", "openUser" });// 在这里添加要过滤的属性名
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		String productId = ServletActionContext.getRequest().getParameter("id");

		String findSql = "SELECT a FROM Product a where a.productId='"
				+ productId + "'";

		List<Object> Product = productService.selectProductById(findSql);

		jsonObject = JSONArray.fromObject(Product, jsonConfig);
		if (Product.size() > 0) {
			json.put("productList", jsonObject);// 给result赋值，传递给页面
			out.print(json);
			out.flush();
			out.close();
		} else {
			json.put("productList", 0);
			out.print(json);
			out.flush();
			out.close();
		}

	}

	public String upload() throws ServletException, IOException {
		System.out.println("fileName:" + this.getUploadfileFileName());
		System.out.println("contentType:" + this.getUploadfileContentType());
		System.out.println("File:" + this.getUploadfile());


		String productId = ServletActionContext.getRequest().getParameter(
				"productIdStr");
		String softVersion = ServletActionContext.getRequest().getParameter(
				"softVersion");
		String version_updateContent = ServletActionContext.getRequest()
				.getParameter("version_updateContent");

		System.out.println(productId);
		System.out.println(softVersion);
		System.out.println(version_updateContent);
		String msg = null;

		// 获取要保存文件夹的物理路径(绝对路径)
		String fileStr = "/file/" + productId;

		String realPath = ServletActionContext.getServletContext().getRealPath(
				fileStr);
		File file = new File(realPath);

		System.out.println("fileName:" + this.getUploadfileFileName());
		if (uploadfile != null
				&& uploadfileFileName.equals("user1.1024.new.2.bin")) {
			// 测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
			if (!file.exists())
				file.mkdirs();
			try {
				// 保存文件
				FileUtils.copyFile(uploadfile, new File(realPath,
						uploadfileFileName));
				productService.updProductById(productId, softVersion,
						version_updateContent);
				msg = "success";
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("文件一上传失败！");
		}

		System.out.println("fileName:" + this.getUploadfile2FileName());
		if (uploadfile2 != null
				&& uploadfile2FileName.equals("user2.1024.new.2.bin")) {
			// 测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
			if (!file.exists())
				file.mkdirs();
			try {
				// 保存文件
				FileUtils.copyFile(uploadfile2, new File(realPath,
						uploadfile2FileName));
				productService.updProductById(productId, softVersion,
						version_updateContent);
				// request.setAttribute("message","nagnaljg");
				// request.getRequestDispatcher("/Product_updateVersion").forward(request,
				// response);
				msg = "success";

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("文件二上传失败！");
		}

		System.out.println("msg=========" + msg);

		return msg;

	}

	public String dojump() {

		return "update";
	}
}
