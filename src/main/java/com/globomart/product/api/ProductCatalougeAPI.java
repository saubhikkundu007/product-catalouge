package com.globomart.product.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.globomart.product.impl.ProductImpl;
import com.globomart.product.model.ErrorStatus;
import com.globomart.product.model.MulitProductDetails;
import com.globomart.product.model.ProductDetails;
import com.globomart.product.util.GlobomartException;
import com.google.gson.Gson;

/**
 * Rest service for product catalouge
 * 
 * @author Saubhik Kundu
 *
 */
@Path("/product")
public class ProductCatalougeAPI {

	/**
	 * GET product details based on product type Takes product type as input and
	 * returns the array of json as output
	 * 
	 * @param productType
	 * @return
	 */
	@GET
	@Path("/product-type/{product-type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdDetailsBasedOnType(@PathParam("product-type") String productType) {
		try {
			String details = ProductImpl.getProductByType(productType);
			if (new Gson().fromJson(details, MulitProductDetails.class).getProductList().size() > 0) {
				return Response.status(200).type("application/json").entity(details).build();
			} else {
				return Response.status(404).type("application/json")
						.entity("{\"status\" : \"error\" , \"details\": \"type : " + productType + " not found\"}")
						.build();
			}
		} catch (GlobomartException e) {
			e.printStackTrace();
			ErrorStatus error = new ErrorStatus();
			error.setStatus("error");
			error.setDetails(e.getLocalizedMessage());
			return Response.status(500).type("application/json").entity(error.toString()).build();
		}
	}

	/**
	 * GET product details based on product id Takes product id as input and
	 * returns the single detail for that product in a json
	 * 
	 * @param productId
	 * @return
	 */
	@GET
	@Path("/product-id/{product-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdDetailsBasedOnId(@PathParam("product-id") String productId) {
		try {
			String details = ProductImpl.getProductById(productId);
			if (details != null) {
				return Response.status(200).type("application/json").entity(details).build();
			} else {
				return Response.status(404).type("application/json")
						.entity("{\"status\" : \"error\" , \"details\": \"id : " + productId + " not found\"}").build();
			}
		} catch (GlobomartException e) {
			e.printStackTrace();
			ErrorStatus error = new ErrorStatus();
			error.setStatus("error");
			error.setDetails(e.getLocalizedMessage());
			return Response.status(500).type("application/json").entity(error.toString()).build();
		}
	}

	/**
	 * Adds the details about a product to the catalouge
	 * 
	 * @param prodDetails
	 * @return
	 * @throws GlobomartException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(String details) {
		ProductDetails prodDetails = new Gson().fromJson(details, ProductDetails.class);
		try {
			ProductImpl.addProduct(prodDetails);
			return Response.status(200).type("application/json")
					.entity("{\"status\" : \"success\" , \"details\": \"added: " + prodDetails.getProductId() + "\"}")
					.build();
		} catch (GlobomartException e) {
			e.printStackTrace();
			ErrorStatus error = new ErrorStatus();
			error.setStatus("error");
			error.setDetails(e.getLocalizedMessage());
			return Response.status(500).type("application/json").entity(error.toString()).build();
		}
	}

	/**
	 * Deletes a product from catalouge based on product id
	 * 
	 * @param prodDetails
	 * @return
	 * @throws GlobomartException
	 */
	@DELETE
	@Path("/product-id/{product-id}")
	public Response deleteProduct(@PathParam("product-id") String prodId) {
		try {
			if (ProductImpl.deleteProductById(prodId) != 0) {
				return Response.status(200).type("application/json")
						.entity("{\"status\" : \"success\" , \"details\": \"deleted: " + prodId + "\"}").build();
			} else {
				return Response.status(404).type("application/json")
						.entity("{\"status\" : \"error\" , \"details\": \"id : " + prodId + " not found\"}").build();
			}
		} catch (GlobomartException e) {
			e.printStackTrace();
			ErrorStatus error = new ErrorStatus();
			error.setStatus("error");
			error.setDetails(e.getLocalizedMessage());
			return Response.status(500).type("application/json").entity(error.toString()).build();
		}
	}

}