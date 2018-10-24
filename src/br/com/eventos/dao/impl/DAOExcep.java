package br.com.eventos.dao.impl;

public class DAOExcep extends Exception{
	private static final long serialVersionUID = 7034312260985707790L;

	public DAOExcep() {
	}

	public DAOExcep(String arg0) {
		super(arg0);
	}

	public DAOExcep(Throwable arg0) {
		super(arg0);
	}

	public DAOExcep(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOExcep(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
