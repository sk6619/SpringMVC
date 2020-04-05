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
其他几个注解也没什么好讲的
