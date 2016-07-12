package cn.cjp.quartz.exception;

public class QuartzException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4246021732338461551L;

	public QuartzException() {
	}

	public QuartzException(String message) {
		super(message);
	}

	public QuartzException(String message, QuartzException cause) {
		super(message, cause);
	}

}
