package custom_exception;

/**
 * 自定义一个姓名异常
 * @author Administrator
 *
 */
public class NameException extends MyException{

	private static final long serialVersionUID = 3L;
	
	public NameException() {
		super();
	}

	public NameException(String message) {
		super(message);
	}

	
}
