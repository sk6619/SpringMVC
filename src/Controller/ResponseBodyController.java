package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Entity.User;


/**
 * 简单描述对注解@Responsebody的使用
 * 官网：可以对方法使用@ResponseBody注释，
 * 通过HttpMessageConverter将返回序列化到响应体。
 * 默认能处理字符串，其他对象需要配置消息转换器
 * @author Administrator
 */
@Controller
public class ResponseBodyController {
	
	//返回字符串，可以直接返回到浏览器，浏览器可以解析出数据
	@RequestMapping("/fourth.do")
	@ResponseBody
	public String doString() {
		String xString = "helloword";
		return xString;
	}
	//相当于上个方法
	public void StringToJson1(HttpServletResponse response) throws IOException {
		String xString = "responsebody";
		response.getWriter().write(xString);
	}
	@RequestMapping("/fourth1.do")
	public String toFourthJSP() {
		return "fourth";
	}
	
	/**
	 * 将user对象转换成json格式返回
	 * @param name
	 * @param age
	 * @return
	 */
	@PostMapping("/getuser.do")
	@ResponseBody
	public User getAjaxUser(String name,Integer age) {
		User user = new User();
		user.setAge(age);
		user.setName(name);
		return user;
	}
	@PostMapping("/getuser1.do")
	@ResponseBody
	public String getAjaxUser1(String name,Integer age) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(name);
		User user = new User();
		user.setAge(age);
		user.setName(name);
		String uString = mapper.writeValueAsString(user);
		return uString;
	}
	/**
	 * produces:解决response乱码，还有一种方法在sprinmvc配置文件修改
	 * @return
	 */
	@RequestMapping(value = "/fourth2.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public java.util.List<String> getList() {
		java.util.List<String> list = new ArrayList<String>();
		String xString = "responsebody1";
		String xString1 = "responsebody2";
		String xString2 = "responsebody3";
		list.add(xString);
		list.add(xString2);
		list.add(xString1);
		return list;
	}
}
