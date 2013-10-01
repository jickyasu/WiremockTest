package com.hooklogic;

public class HooklogicException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public HooklogicException() {
		super();
	}

	public HooklogicException(String message) {
		super(message);
	}
	
	public HooklogicException(Throwable t) {
		super(t);
	}
}
