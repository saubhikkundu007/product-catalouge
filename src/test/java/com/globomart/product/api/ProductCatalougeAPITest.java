package com.globomart.product.api;

import org.junit.Test;

import com.globomart.product.model.ProductDetails;

import junit.framework.Assert;

public class ProductCatalougeAPITest {
		
	@Test
	public void TestaddProduct(){
		ProductDetails prodDetails1 = new ProductDetails();
		prodDetails1.setProductId("test1");
		prodDetails1.setProductName("test Product 1");		
		prodDetails1.setProductType("test Product Type");
		prodDetails1.setProductPrice("5");
		
		ProductDetails prodDetails2 = new ProductDetails();
		prodDetails2.setProductId("test2");
		prodDetails2.setProductName("test Product 2");		
		prodDetails2.setProductType("test Product Type");
		prodDetails2.setProductPrice("10");
		
		Assert.assertEquals(new ProductCatalougeAPI().addProduct(prodDetails1.toString()).getStatus(), 200);
		Assert.assertEquals(new ProductCatalougeAPI().addProduct(prodDetails2.toString()).getStatus(), 200);
	}
	
	@Test
	public void TestgetProdDetailsBasedOnId(){
		Assert.assertEquals(new ProductCatalougeAPI().getProdDetailsBasedOnId("test1").getStatus(), 200);
	}

	
	@Test
	public void TestgetProdDetailsBasedOnType(){
		Assert.assertNotNull(new ProductCatalougeAPI().getProdDetailsBasedOnType("test Product Type").getStatus());
	}
	
	@Test
	public void TestdeleteProdDetailsBasedOnId(){
		Assert.assertEquals(new ProductCatalougeAPI().deleteProduct("test1").getStatus(), 200);
		Assert.assertEquals(new ProductCatalougeAPI().deleteProduct("test2").getStatus(), 200);
	}

}
