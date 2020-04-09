package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Entity.User;
import custom_exception.AgeException;
import custom_exception.MyException;
import custom_exception.NameException;

/**
 * 自定义异常例子
 * @author Administrator
 *
 */
@Controller
public class CustomExceptionController {
	/**
	 * 模拟一个异常处理
	 * @param username   客户端参数name绑定到服务器username
	 * @param userage    客户端参数age绑定到服务器userage
	 * @return
	 * @throws MyException 跑出我的自定义异常
	 */
	@RequestMapping("/some.do")
	public ModelAndView handException(@RequestParam("name") String username,
			@RequestParam("age") Integer userage) throws MyException {
		//抛出一个自定义的NameException
		if(("习近平").equals(username)) {
			throw new NameException("名字不符合规定");
		}
		//抛出一个自定义的AgeException
		if(userage < 18) {
			throw new AgeException("年龄不符合规定");
		}
		ModelAndView mView = new ModelAndView();
		User user = new User(username, userage);
		mView.addObject(user);
		mView.setViewName("second");
		return mView;
	}
		//该方法处理这个类的姓名异常
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

}
