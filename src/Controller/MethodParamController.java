package Controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @PathVariable/@Pathparam 1.声明URI变量并使用@PathVariable访问它们的值 2.
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/param")
public class MethodParamController {

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
	
	/**
	 * 通过url:/pathVariable/{paramId}/{paramName}.do
	 * 解析出paramId和paramName
	 * @param paramId
	 * @param paramName 
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

}
