# SpringMVC
############################################第一个springMVC项目

第一步：下载SpringMVC的核心jar包：（maven仓库下载）
一个简单的项目至少需要

第二部 ：配置DispatherServlet（xml配置-在web.xml中）
按照配置servlet的方式，官网给的配置方式：
 <web-app>

<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/app-context.xml</param-value>
</context-param>

<servlet>
    <servlet-name>app</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/app-context.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

    <servlet-mapping>
        <servlet-name>app</servlet-name>
        <url-pattern>/app/*</url-pattern>
    </servlet-mapping>

</web-app>

第三部:创建SpringMVC的配置文件:
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    //必须有这个标签
    <mvc:annotation-driven/>

</beans>

第一个SpringMVC程序已经可以运行。可以接受和发送请求


发送请求first.do   经过DispatherServlet一系列处理，访问到配置文件里面的<bean>的id从而访问到我们的具体controller。

Controller代码
package Controller;

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

	
@Override
public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView mView = new ModelAndView();
	mView.addObject("msg", "第一个SpringMVC/采用配置");
	mView.setViewName("/WEB-INF/html/first.jsp");
	return mView;
}

}

视图解析器
为了简化代码书写，springmvc还提供了视图解析器（wen-inf文件夹下的文件是不能访问的，所以必须加上web-inf前缀）

<!-- 配置视图解析器 ,简化对扩展名的书写-->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<!-- 配置前缀和后缀 -->
	<property name="prefix" value="/WEB-INF/jsp/"/>
	<property name="suffix" value=".jsp"/>
	</bean>
配置之后  mView.setViewName("first");sprinfmvc会帮我们处理为/web-INF/jsp/ +first+ .jsp


到此，一个最基本的SpringMVC项目已经结束


#######################################################SpringMVC2
更新SpringMVC1


一：使用注解Controller以及其他注解
1：在SpringMVC配置文件中添加标签<context:component-scan base-package="Controller"/>，在下面代码中添加黄色的部分

<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

	<!-- 配置使用注解，配置扫描哪一个包先在xmlns里面添加声明context -->
	<context:component-scan base-package="Controller"/>
	
	<!-- 无论是注解式和配置式必须加 -->
	<mvc:annotation-driven />
	
	<!-- 声明controller，让容器创建，然后把请求交给这个controller id:请求url，/开头 class:类名 -->
	<bean id="/first.do" class="Controller.UserController" />
	
	<!-- 配置视图解析器 ,简化对扩展名的书写-->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<!-- 配置前缀和后缀 -->
	<property name="prefix" value="/WEB-INF/jsp/"/>
	<property name="suffix" value=".jsp"/>
	</bean>

</beans>



二：使用注解@Controller
创建一个Controller类并且无需实现Controller接口
只需要在类上打上注解@controller，SpringMVC就会自动找到这个类把它解析
/**
 * 使用直接的方式声明Controller而不是采用配置式，
 * 所以无需实现Controller接口
 * @author Administrator
 *
 */
@Controller
public class AnotationController {
	
@RequestMapping("/second.do")
public ModelAndView getAndView() {
	ModelAndView mView = new ModelAndView();
	//在mView里面添加一个字符串shaokui，键为user
	mView.addObject("user", "shaokui");
	mView.setViewName("second");
	return mView;
}

}
三：使用注解
@RequestMapping
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PatchMapping

@RequestMapping

@RequestMapping("/second.do")
public ModelAndView useAnaotation() {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "启动注解成功");
		mView.setViewName("second");
		return mView;
	}
//@requestMapping的value属性，可以映射多个url
@RequestMapping(value= {"/second1.do","/second2.do","/second3.do"})
public ModelAndView useAnaotation1() {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "多个请求映射一个方法");
		mView.setViewName("second");
		return mView;
	}

/**
	 * @requestMappingd的第二个属性method,
	 * method = RequestMethod.GET 查
	 * method = RequestMethod.POST 增
	 * method = RequestMethod.PUT  改
	 * method = RequestMethod.DELETE 删
	 * 一种rest风格，浏览器过来的请求只能get-get，post-post...
	 * @return
	 */
	@RequestMapping(value = "/second4.do",method = RequestMethod.GET)
	public ModelAndView useRequestMapping() {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "rest风格");
		mView.setViewName("second");
		return mView;
	}
//只能支持post请求
	@RequestMapping(value = "/second41.do",method = RequestMethod.POST)
	public ModelAndView useRequestMapping2() {
		ModelAndView mView = new ModelAndView();
		mView.addObject("msg", "rest风格");
		mView.setViewName("second");
		return mView;
	}

//方法返回String
	@GetMapping("/second6.do")
	public String useString(HttpServletRequest request) {
		request.setAttribute("msg", "方法返回string类型");
		//配置了视图解析器，所以返回的字符串会匹配视图
		return "second";
	}


SpringMVC3
SpringMVC中比较重要的几个注解

一：@PathVariable
/**
	 * 通过url:/pathVariable/{paramId}/{paramName}.do
	 * 解析出paramId和paramName
	 * @param paramId--- id
	 * @param paramName ---name
	 * @return mv
	 */
	@RequestMapping("/pathVariable/{paramId}/{paramName}.do")
	public ModelAndView methodPathVariable(@PathVariable(value = "paramId",required = false) Integer id,
			@PathVariable(value = "paramName",required = false) String name) {
		ModelAndView mvieView = new ModelAndView();
		// 将获取到的参数添加到model里面在页面查看 
		mvieView.addObject("paramId", id+"@pathVariable");
		mvieView.addObject("paramName", name+"@pathVariable");
		mvieView.setViewName("third");
		return mvieView;
	}

二：@RequestParam
/**
	 * 参数绑定，传过来的参数绑定到不通的参数名
	 * @param paramId
	 * @param paramName 
	 * @return mv
	 */
	@RequestMapping("/pathRequestParam.do")
	public ModelAndView methodRequest(@RequestParam(value = "paramId",required = false) Integer id,
			@RequestParam(value = "paramName",required = false) String name) {
		ModelAndView mvieView = new ModelAndView();
		// 将获取到的参数添加到model里面在页面查看 
		mvieView.addObject("paramId", id);
		mvieView.addObject("paramName", name);
		mvieView.setViewName("third");
		return mvieView;
	}
三：@pathParam
/**
	 * rest风格，只支持post请求
	 * 
	 * @param 页面表单传过来的paramId绑定到paramId   不能改不通的参数名 亲测
	 * @param 页面表单传过来的paramName绑定到paramName
	 * @return
	 */
	@RequestMapping(value = "/pathParam.do", method = RequestMethod.POST)
	public ModelAndView methodParam(@PathParam(value="paramId") Integer paramId, @PathParam("paramName") String paramName) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("paramId", paramId);
		mView.addObject("paramName", paramName);
		mView.setViewName("third");
		return mView;
	}
	//@pathParam效果和直接匹配对应的参数名是一样的
	@RequestMapping(value = "/pathParam1.do", method = RequestMethod.POST)
	public ModelAndView methodParam1( Integer paramId,  String paramName) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("paramId", paramId);
		mView.addObject("paramName", paramName);
		mView.setViewName("third");
		return mView;
	}
	
一：重定向
原理：

重定向是一个客户端行为，用户请求到达服务器之后，服务器返回响应，HTTP状态码置为302，并将转发的页面保存在响应头中的Location属性中，告诉客户端应该向这个地址发出请求，然后客户端再次发出请求。
在整个过程中客户端发送了最少两次请求，因为请求是不同的，因此request和response对象在重定向前后是不同的，两个对象中的attribute在重定向前后也都是不同的。
 重定向可以访问当前Web应用之外的资源。

// 方法返回String，重定向
	@GetMapping("/second7.do")
	public String useString1(HttpServletRequest request,HttpServletResponse respo) {
		//respo.sendRedirect("test.jsp");
		return "redirect:second6.do";
	}

二：转发
所谓请求转发，是服务器的行为，请求由服务器转发给另外一个页面处理，如何转发，何时转发，转发几次，客户端是不知道的。请求转发时，从发送第一次到最后一次请求的过程中，web容器只创建一次request和response对象，新的页面继续处理同一个请求。也可以理解为服务器将request对象在页面之间传递。
// 转发
	@RequestMapping("/second8.do")
	public void forwardString(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("first").forward(request, response);
	}

请求转发之后地址栏的信息并不会有任何的改变。
// 转发
	@RequestMapping("/second8.do")
	public void forwardString(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("name", "shaokui");
		//转发：所以服务器直接将请求发出去的，所以还是原来的请求
		//整个过程请求只有一个，所以second页面能接受这个属性
		request.getRequestDispatcher("second6.do").forward(request, response);
	}
转发浏览器的地址是不变的，因为这个转发过程是服务器完成的
请求重定向之后地址栏是会改变的，变为跳转之后的页面地址。
@GetMapping("/second7.do")
	public String useString1(HttpServletRequest request,HttpServletResponse respo) {
		//respo.sendRedirect("test.jsp");
		return "redirect:second6.do";
	}
重定向就是让浏览器重新发请求
访问second7.do之后服务器立即将响应给浏览器。浏览器立即重写发一个请求到second6.do
所以浏览器地址立马发生改变



重定向会有一个302的状态码

三：重定向的 / 表示：http://服务器ip:端口/
相当于jsp页面的超链接加/一样
四：请求转发的 / 表示：http://服务器ip:端口/项目名

	
