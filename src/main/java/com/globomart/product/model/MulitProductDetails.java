package com.globomart.product.model;

import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MulitProductDetails {
	
	@SerializedName("productList")
	@Expose
	private List<ProductDetails> productList;
		
	public List<ProductDetails> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductDetails> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}

}
