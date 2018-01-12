package com.eumji.zblog.controller.user;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eumji.zblog.controller.user.TreeBuilder.Node;
import com.eumji.zblog.util.JsonDateValueProcessor;
import com.eumji.zblog.util.RandomValueGenerator;
import com.eumji.zblog.vo.Config;
import com.eumji.zblog.vo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@Api("用户相关控制器测试Swagger ")
@RequestMapping("/user")
public class UserControllers{

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public  String index(ModelAndView modelAndView, Model model){
        System.out.println("----------------哈哈8-250---------------");
        modelAndView.addObject("heoolw","哈哈哈哈哈");
        return "user/index";
    }
    
    @RequestMapping(value = "/configs",method = RequestMethod.GET)
    public  String configs(ModelAndView modelAndView, Model model){
        return "user/config";
    }

    @RequestMapping(value = "/knockout",method = RequestMethod.GET)
    public  String knockout(ModelAndView modelAndView, Model model){
        return "user/knockout";
    }
    /**
     * 项目实际模块演练
     * @param modelAndView
     * @param model
     * @return
     */
    @RequestMapping(value = "/table",method = RequestMethod.GET)
    public  String table(ModelAndView modelAndView, Model model){
        return "user/table";
    }
    
    @RequestMapping(value = "/mock",method = RequestMethod.GET)
    public  String mock(ModelAndView modelAndView, Model model){
        return "user/mock";
    }
    /**
     * select  控件 加载数据
     * @param q
     * @return
     */
    @RequestMapping(value = "/selectAjax",method = RequestMethod.GET)
    @ResponseBody
    public String selectAjax(@RequestParam("q") String q){
        System.out.println("前台传递的是"+q);
        List<String> list = new ArrayList<String>();
        list.add("中国打扫打扫打扫");
        list.add("柬埔寨");
        list.add("韩国");
        list.add("中国拉萨");
        list.add("中国河南");
        list.add("日本东京");
        list.add("中国湖北");
        list.add("日本富士山");
        list.add("中国十堰");
        list.add("中国武汉");
        JSONArray jsonArray = JSONArray.fromObject(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("items",jsonArray);
        jsonObject.put("total_count",jsonArray.size());
        System.out.println(jsonObject);
        return String.valueOf(String.valueOf(jsonObject));
    }

    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/treeZhonglin",method = RequestMethod.GET)
    @ResponseBody
    public String treeZhonglin(){
    	JSONArray array = JSONArray.fromObject("[{'id':'1','pid':'-1','text':'商品目录'},{'id':'11','pid':'1','text':'日用品'},{'id':'12','pid':'1','text':'食品'},{'id':'13','pid':'12','text':'香馍馍'},{'id':'111','pid':'11','text':'洗发水'},{'id':'1111','pid':'111','text':'霸王'},{'id':'2','pid':'-1','text':'课程表'},{'id':'3','pid':'2','text':'语文课'},{'id':'33','pid':'2','text':'数学课'},{'id':'4','pid':'3','text':'王二小'},{'id':'5','pid':'4','text':'她有一头牛'},{'id':'22','pid':'-1','text':'四大神兽'}]");
    	List<Node> list = JSONArray.toList(array, Node.class);
    	String buildTree = new TreeBuilder().buildTree(list);
    	System.out.println(buildTree);
    	return buildTree;
    }
    
    /**
     * tree控件加载数据！
     * @return
     */
    @RequestMapping(value = "/treeAjax",method = RequestMethod.GET)
    @ResponseBody
    public String treeAjax(){
        String s = " [\n" +
                "                {\n" +
                "                    \"id\": \"1\",\n" +
                "                    \"text\": \"民事案由(2008版)\",\n" +
                "                    \"state\": {\n" +
                "                        \"opened\": false,\n" +
                "                        \"disabled\": false         \n" +
                "                    },\n" +
                "                    \"children\":\n" +
                "                        [\n" +
                "                            {\n" +
                "                                \"id\": \"2\",\n" +
                "                                \"text\": \"人格权纠纷\",\n" +
                "                                \"children\":\n" +
                "                                    [\n" +
                "                                        {\n" +
                "                                            \"id\": \"3\",\n" +
                "                                            \"text\": \"人格权纠纷\",\n" +
                "                                            \"children\": [\n" +
                "                                                {\n" +
                "                                                    \"id\": \"4\",\n" +
                "                                                    \"text\": \"生命权、健康权、身体权纠纷\",\n" +
                "                                                    \"children\":\n" +
                "                                                        [\n" +
                "                                                            {\n" +
                "                                                                \"id\": \"5\",\n" +
                "                                                                \"text\": \"道路交通事故人身损害赔偿纠纷\",\n" +
                "                                                                \"children\":\n" +
                "                                                                    [\n" +
                "                                                                        {\n" +
                "                                                                            \"id\": \"6\",\n" +
                "                                                                            \"text\": \"湖北人民在吗？\"\n" +
                "                                                                        }\n" +
                "                                                                    ]\n" +
                "                                                            }\n" +
                "                                                        ]\n" +
                "                                                }\n" +
                "                                            ]\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                            }\n" +
                "                        ]\n" +
                "                }\n" +
                "            ]";
        return s;
    }

    /**
     * jquery table 提供数据！
     * @return
     */
    @RequestMapping(value = "/tableAjax",method = RequestMethod.GET)
    @ResponseBody
    public String tableAjax(){
        List<User> list  = new ArrayList();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setId(Long.valueOf(i));
            user.setUsername("钟林"+ UUID.randomUUID().toString());
            user.setPassword(String.valueOf(i)+UUID.randomUUID());
            user.setExpired(true);
            user.setLocked(false);
            user.setCredential(true);
            user.setEnabled(true);
            user.setCreateTime(new Date());
            list.add(user);
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.setIgnoreDefaultExcludes(false);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.setExcludes(new String[]{"authorities"});
        Map map = new HashMap();
        map.put("data",list);
        JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
        return jsonObject.toString();
    }
    
    
    /**
     * jquery table 提供数据！  2017年9月16日23:39:25
     * @return
     */
    @RequestMapping(value = "/tableData",method = RequestMethod.GET)
    @ResponseBody
    public String tableData(){
    	  List<Config> list  = new ArrayList();
          for (int i = 1; i < 22; i++) {
             Config config = new Config();
             config.setId(i);
             config.setName("钟林"+i);
             config.setVersion(String.valueOf(i));
             config.setContent("内容"+i);
             config.setList("集合"+i);
             config.setStarTime(new Date());
             config.setUpdateTime(new Date());
              list.add(config);
          }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.setIgnoreDefaultExcludes(false);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.setExcludes(new String[]{"authorities"});
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
        return jsonObject.toString();
    }
    /**
     * config 模块
     * jquery table 提供数据！
     * @return
     */
    @RequestMapping(value = "/config",method = RequestMethod.GET)
    @ResponseBody
    public String config(@RequestParam("aopData") String aopData){
    	int curPage = getCurPage(aopData)[0];
        List<Config> list  = new ArrayList();
        for (int i = 1; i < 11; i++) {
           Config config = new Config();
           config.setId(i);
           config.setName("钟林"+curPage*i);
           config.setVersion(String.valueOf(curPage*i));
           config.setContent("内容"+curPage*i);
           config.setList("集合"+curPage*i);
           config.setStarTime(new Date());
           config.setUpdateTime(new Date());
            list.add(config);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", list);
        map.put("iTotalDisplayRecords", 100);
        map.put("iTotalRecords", 100);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(map);
    }

    /**
     * 获取当前的页码！
     * @param aopData
     * @return
     */
    public int [] getCurPage(String aopData){
    	JSONArray jsArr = JSONArray.fromObject(aopData);
    	int iDisplayStart = -1;
		int iDisplayLength = -1;
    	for (Object object : jsArr) {
			if(JSONObject.fromObject(object).get("name").equals("iDisplayStart")){
				iDisplayStart = Integer.parseInt((JSONObject.fromObject(object).get("value").toString()));
			}
			if(JSONObject.fromObject(object).get("name").equals("iDisplayLength")){
				iDisplayLength = Integer.parseInt(JSONObject.fromObject(object).get("value").toString());
			}
			if(iDisplayStart!=-1 && iDisplayLength!=-1){
				break;
			}
		}
    	JSONObject jsonParameter = JSONObject.fromObject(jsArr.get(jsArr.size()-1));
    	System.out.println("参数："+jsonParameter.get("parameter"));
    	//System.out.println("========================当前页码："+curPage+"-----每页显示条数"+pageSize);
    	System.out.println("=========================limit"+iDisplayStart+","+(iDisplayStart+iDisplayLength));
    	int page [] = {iDisplayStart,(iDisplayStart+iDisplayLength)};
    	return page;
    }
    /**
     * lay ui table 数据
     * @return
     */
    @RequestMapping(value = "/layui",method = RequestMethod.GET)
    @ResponseBody
    public String layuiTable(){
        List<User> list  = new ArrayList();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setId(Long.valueOf(i));
            user.setUsername(RandomValueGenerator.generateRandomChineseName("女",3));
            user.setPassword(RandomValueGenerator.generateRandomEnglishName("M"));
            user.setExpired(RandomValueGenerator.generateRandomChineseBoolean());
            user.setLocked(RandomValueGenerator.generateRandomChineseBoolean());
            user.setCredential(RandomValueGenerator.generateRandomChineseBoolean());
            user.setEnabled(RandomValueGenerator.generateRandomChineseBoolean());
            user.setCreateTime(RandomValueGenerator.generateRandomBirthday(27));
            list.add(user);
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.setIgnoreDefaultExcludes(false);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.setExcludes(new String[]{"authorities"});
        Map map = new HashMap();
        map.put("data",list);
        map.put("count",list.size());
        map.put("code","0");
        map.put("msg","layui table");
        JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
        return jsonObject.toString();
    }
  

}
