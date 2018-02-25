package com.globomart.product.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.globomart.product.model.MulitProductDetails;
import com.globomart.product.model.ProductDetails;
import com.globomart.product.util.GlobomartException;
import com.globomart.product.util.RedisClient;
import com.google.gson.Gson;

public class ProductImpl {
	
	/**
	 * Adding any product to in memory db or product catalouge
	 * @param prodDetails
	 * @throws GlobomartException 
	 */
	public static void addProduct(ProductDetails prodDetails) throws GlobomartException{
		try{
		RedisClient.getInstance().set(prodDetails.getProductId(), prodDetails.toString());
		}catch(Exception ex){
			throw new GlobomartException("error occured adding product details", ex);	
		}
	}
	
	/**
	 * Get any product details based on product id
	 * @param prodId
	 * @throws GlobomartException 
	 */
	public static String getProductById(String prodId) throws GlobomartException{
		try{
		return RedisClient.getInstance().get(prodId);
		}catch(Exception ex){
			throw new GlobomartException("error occured retrieving product details", ex);
		}
	}
	
	
	/**
	 * Get any product details based on productType
	 * @param prodType
	 * @throws GlobomartException 
	 */
	public static String getProductByType(String prodType) throws GlobomartException{
		try{
		MulitProductDetails multiProd =  new MulitProductDetails(); 
		List<ProductDetails> prodList = new ArrayList<>();
		Set<String> list = RedisClient.getInstance().keys("*"); 
		Iterator<String> itr =	list.iterator();
		if(itr.hasNext()){
			try{
				ProductDetails prodDetails = new Gson().fromJson(itr.next(), ProductDetails.class);
				if(prodDetails.getProductType().equals(prodType)){
					prodList.add(prodDetails);
				}
			}catch(Exception ex){}
		}
		multiProd.setProductList(prodList);
		return multiProd.toString();
		}catch(Exception ex){
			throw new GlobomartException("error occured retrieving product details", ex);
		}
	}
	
	/**
	 * Get any product details based on product id
	 * @param prodId
	 * @throws GlobomartException 
	 */
	public static long deleteProductById(String prodId) throws GlobomartException{
		try{
			return RedisClient.getInstance().del(prodId);
		}catch(Exception ex){
			throw new GlobomartException("error occured deleting product details", ex);
		}
	}

}
