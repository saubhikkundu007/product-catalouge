package com.globomart.product.util;

/**
 * This class used to throw internal exceptions.
 * 
 * @author Saubhik Kundu
 *
 */
public class GlobomartException extends Exception {

	private static final long serialVersionUID = -7264872607959031952L;

	/**
	 * Constructor which accepts messge as string.
	 * 
	 * @param msg
	 *            exception message.
	 */
	public GlobomartException(String msg) {
		super(msg);
	}

	/**
	 * Constructor which accepts throwable.
	 * 
	 * @param e
	 *            throwable object.
	 */
	public GlobomartException(Throwable e) {
		super(e);
	}

	/**
	 * Constructor which accepts throwable and message as arguements.
	 * 
	 * @param msg
	 *            is error message.
	 * @param e
	 *            is throwable object.
	 */
	public GlobomartException(String msg, Throwable e) {
		super(msg, e);
	}
}
