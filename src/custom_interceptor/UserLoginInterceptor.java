package custom_interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户登录拦截器
 * @author Administrator
 *
 */
public class UserLoginInterceptor implements HandlerInterceptor{

	/**
	 * 整个请求结束猜执行该方法
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("整个请求完成时间为"+format.format(date));
	}

	/**
	 * 调用处理器之后，解析视图之前执行该方法
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("用户登录请求执行完成");
	}

	/**
	 * 在执行控制器之前执行该方法
	 * false则请求到此为止
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截用户登录信息"+handler.toString());
		return true;
	}
	

}
