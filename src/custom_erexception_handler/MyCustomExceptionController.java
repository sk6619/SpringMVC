package custom_erexception_handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import custom_exception.AgeException;
import custom_exception.NameException;

/**
 * 自定义异常处理器
 * @author Administrator
 *
 */
public class MyCustomExceptionController implements HandlerExceptionResolver{

	/**
	 * obj :发生异常的Controller对象
	 * exception :发生的异常
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object obj,Exception exception) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("exception", exception);
		//写日志，发送短信等等等操作,把异常告诉开发人员
		//给用户一个处理过的异常结果
		if(exception instanceof NameException) {
			mView.addObject("nameEx", exception.getMessage());
		}
		if(exception instanceof AgeException) {
			mView.addObject("ageEx", exception.getMessage());
		}
		mView.setViewName("error");
		return mView;
	}

}
