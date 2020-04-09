package Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import custom_exception.AgeException;
import custom_exception.NameException;

/**
 * @ExceptionHandler:修饰方法，表示这个方法是处理异常的
 * 必须放在对应的Controler里面，他就处理这个Controller的异常
 * 除非加上@ControllerAdvice全局异常
 * @author Administrator
 *
 */
@ControllerAdvice
public class AnotationExceptionController {
	
	//该方法处理姓名异常
	@ExceptionHandler(value = NameException.class)
	public ModelAndView handExce(Exception exception) {
		//处理异常，日志，数据库，短信。。
		
		//返回异常视图
		ModelAndView mView = new ModelAndView();
		mView.addObject("nameEx", exception.getMessage());
		mView.addObject("exception", exception);
		mView.setViewName("error");
		return mView;
	}
	
	@ExceptionHandler(AgeException.class)
	public ModelAndView handExce1(Exception exception) {
		//处理异常，日志，数据库，短信。。
		
		//返回异常视图
		ModelAndView mView = new ModelAndView();
		mView.addObject("ageEx", exception.getMessage());
		mView.setViewName("error");
		return mView;
	}
	//默认处理所有异常
	@ExceptionHandler
	public ModelAndView handExce3(Exception exception) {
		//处理异常，日志，数据库，短信。。
		
		//返回异常视图
		ModelAndView mView = new ModelAndView();
		mView.addObject("Error", exception.getMessage());
		mView.setViewName("error");
		return mView;
	}

}
