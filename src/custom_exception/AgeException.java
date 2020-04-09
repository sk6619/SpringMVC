package custom_exception;

/**
 * 自定义的年龄异常
 * @author Administrator
 *
 */
public class AgeException extends MyException{

	private static final long serialVersionUID = 2L;
	
	public AgeException() {
		super();
	}

	public AgeException(String message) {
		super(message);
	}

	
}
