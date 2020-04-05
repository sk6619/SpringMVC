package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 在配置式的springMVC中处理器需要实现接口Controller
 * 这就相当于最初版的对servlet的封装，一个类处理一个请求
 * @author Administrator
 *
 */

public class UserController implements Controller{
    List<String> slist;
   
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "第一个SpringMVC,"
				+ "通过实现Controller接口配置<bean>来实现一个Controller");
		slist = new ArrayList<String>();
		slist.add("hello");
		slist.add("word");
		mView.addObject("list", slist);
		mView.setViewName("first");
		return mView;
	}
	
}
