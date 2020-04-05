package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * 这通过创建AbstarctController来创建一个Controller
 * 和实现Controller接口创建一个Controller一样一个类只能处理一个请求
 * @author Administrator
 *
 */
public class User1Controller extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "通过继承创建AbstractController"
				+ "来实现一个Controller");
		mView.setViewName("first");
		return mView;
	}

}
