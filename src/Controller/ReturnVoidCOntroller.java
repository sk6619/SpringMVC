package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Entity.User;

/**
 * 返回值是void，一般是用来响应ajax请求
 * @author Administrator
 *
 */
@Controller
public class ReturnVoidCOntroller {
	
	/**
	 * @param name
	 * @param age
	 * @throws IOException 
	 */
	@RequestMapping(value = "/void.do",method = RequestMethod.GET)
	public void getAjax(String name,Integer age,HttpServletResponse response) throws IOException {
		PrintWriter outWriter = response.getWriter();
		//输出给页面，ajax接收
		outWriter.print(name+age);
		outWriter.flush();
		outWriter.close();
	}
	/**
	 * 封装一个user对象返回给ajax
	 * @param name
	 * @param age
	 * @param response
	 * @throws IOException 
	 */
	@PostMapping("void1.do")
	public void getAjax2(String name,Integer age,HttpServletResponse response) throws IOException {
		User user = new User(name, age);
		response.setCharacterEncoding("utf-8");
		PrintWriter outWriter = response.getWriter();
		outWriter.print(user);
		outWriter.flush();
		outWriter.close();
	}
	/**
	 * 将user对象转化为json字符串
	 * @param name
	 * @param age
	 * @param response
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("void2.do")
	public void getAjaxJSON(String name,Integer age,HttpServletResponse response) throws IOException, JsonMappingException {
		User user = new User(name, age);
		//利用kackson提供的类库，将user对象转换为json格式的字符串
		ObjectMapper mapper = new ObjectMapper();
		String userString=mapper.writeValueAsString(user);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(userString);
		writer.flush();
		writer.close();
	}
	/**
	 * 返回list
	 * @param name
	 * @param age
	 * @param response
	 * @throws IOException
	 * @throws JsonMappingException
	 */
	@RequestMapping("void3.do")
	public void getAjaxJSON1(HttpServletResponse response) throws IOException, JsonMappingException {
		User user = new User("张三", 10);
		User user1 = new User("lisi", 30);
		User user2 = new User("李四", 40);
		List<User> list = new ArrayList<>();
		list.add(user2);
		list.add(user1);
		list.add(user);
		//利用kackson提供的类库，将user对象转换为json格式的字符串
		ObjectMapper mapper = new ObjectMapper();
		String userString=mapper.writeValueAsString(list);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(userString);
		writer.flush();
		writer.close();
	}

}
