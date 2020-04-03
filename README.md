# SpringMVC
第一个springMVC项目

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
