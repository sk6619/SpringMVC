package custom_exception;

/**
 * 自定义异常
 * @author Administrator
 *
 */
public class MyException  extends Exception{
    //声明一个序列化ID
	private static final long serialVersionUID = 1L;
	
	public MyException() {
		super();
	}

	public MyException(String message) {
		super(message);
	}
	

}
