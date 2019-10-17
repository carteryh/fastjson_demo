# fastjson_demo
fastjson demo

fastjson自定义反序列化实现类,demo对一个字符串进行日期格式化(可兼容多重格式)、String转Double和String转Integer。

1.自定义fastjson字符串日期格式化

	自定义类DateCompatibilityFormat,继承ObjectDeserializer,如下:
```
	/**
	 * fastjson custom class
	 * chenyouhong
	 * date format
	 */
	@Slf4j
	public class DateCompatibilityFormat implements ObjectDeserializer {


		//格式化样式
	    private static final List<String> formatStyles = Lists.newArrayList("yyyy.MM.dd", "yyyy.MM", "yyyy.MM.dd hh:mm:ss");

	    //关键代码
	    @Override
	    public Date deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
	        DateTime dateTime = null;
	        try {
	            for (String formatStyle: formatStyles) {
	                dateTime = this.getDateTime(formatStyle, parser.getLexer().stringVal());
	                if (dateTime != null) break;
	            }
	        } catch (Exception e) {
	            log.info("error {}", e);
	        }

	        return dateTime != null ? dateTime.toDate() : null;
	    }

	    //日期格式化
	    private DateTime getDateTime(String sFormat, String sDate) {
	        DateTime dateTime = null;
	        try {
	            dateTime = DateTimeFormat.forPattern(sFormat).parseDateTime(sDate);
	        } catch (Exception e) {
	            log.info("date format error, format={}, date={}, error={}", sFormat, sDate, e);
	        }

	        return dateTime;
	    }

	    //暂未知道具体作用
	    @Override
	    public int getFastMatchToken() {
	        return 0;
	    }

	}
```

2.对应Model加上相应的注解即可
```

	@Data
	public class SysUser implements Serializable {

	    private static final long serialVersionUID = 1L;

	    private String username;

	    private String loginCode;

	    private String password;

	    //日期格式化
	    @JSONField(deserializeUsing = DateCompatibilityFormat.class)
	    private Date createDate;

	    private String mobile;

		//String转Integer
	    @JSONField(deserializeUsing = StringToIntegerFormat.class)
	    private Integer age;

		//String转Double
	    @JSONField(deserializeUsing = StringToDoubleFormat.class)
	    private Double weight;

	}
```

3.测试方法,测试类DemoApplicationTests
```
	@Test
	public void testFormat() {
		String json = " {\"createDate\":\"2015.03\", \"age\":\"20,189.89\"}";
		SysUser sysUser = JSON.parseObject(json, SysUser.class);

        try {

            DateTime dateTime = new DateTime(sysUser.getCreateDate());
            String s = dateTime.toString("yyyy-MM-dd");
            System.out.println(s);
            s = dateTime.toString("yyyy-MM");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(sysUser));

	}
```

同理StringToDoubleFormat、StringToIntegerFormat

4.嵌套对象转map,测试类MapTest

   a.嵌套对象转map方法
   该方法把嵌套对象转为单层map。
           
    Map<String, Object> map = ObjectToMapUtils.trfMap(json, ".");

   b.获取嵌套对象值
   
   该方法不需要转为map，直接配置相关的key,获取对应的value。
           
    //获取对象相应值,key中无数组情况
    String condition = "[{\"key\": \"data.page.current\"}]";
    List<SearchCondition> conditions = JSON.parseArray(condition, SearchCondition.class);
    Object value = ObjectToMapUtils.getObjValue(json, conditions, null);
    //输出 1
    System.out.println(value);
    
    //获取对象对应value值的数组
    condition = "[{\"key\": \"data.rows.name\", \"value\":\"流程步骤名称\"}]";
    conditions = JSON.parseArray(condition, SearchCondition.class);
    Object obj = ObjectToMapUtils.getObjValue(json, conditions, null);
    System.out.println(obj);
           
   代码如下:

        //测试json,可以为一个Object对像
        String json = "{\"success\":0,\"errorMsg\":\"错误消息\",\"data\":{\"total\":\"总记录数\",\"page\":{\"size\":10,\"current\":1},\"rows\":[{\"id\":\"任务ID\",\"workName\":\"任务名称\",\"assigneeName\":\"经办人姓名\",\"name\":\"流程步骤名称\",\"processInstanceInitiatorName\":\"发起人\",\"processInstanceStartTime\":\"发起时间\",\"createTime\":\"到达时间\",\"dueDate\":\"截止时间\"},{\"id\":\"ID\",\"workName\":\"名称\",\"assigneeName\":\"经办人\",\"name\":\"流程\",\"processInstanceInitiatorName\":\"发起人\",\"processInstanceStartTime\":\"发起\",\"createTime\":\"到达\",\"dueDate\":\"截止\"}]}}";

        //转map，connector自定义，表示嵌套对象key与key的连接
        Map<String, Object> map = ObjectToMapUtils.trfMap(json, ".");

        //获取map值
        //输出 {data.page.current=1, data.page.size=10, data={"total":"总记录数","page":{"current":1,"size":10},"rows":[{"assigneeName":"经办人姓名","processInstanceStartTime":"发起时间","createTime":"到达时间","processInstanceInitiatorName":"发起人","dueDate":"截止时间","name":"流程步骤名称","id":"任务ID","workName":"任务名称"},{"assigneeName":"经办人","processInstanceStartTime":"发起","createTime":"到达","processInstanceInitiatorName":"发起人","dueDate":"截止","name":"流程","id":"ID","workName":"名称"}]}, success=0, data.total=总记录数, data.rows=[{"assigneeName":"经办人姓名","processInstanceStartTime":"发起时间","createTime":"到达时间","processInstanceInitiatorName":"发起人","dueDate":"截止时间","name":"流程步骤名称","id":"任务ID","workName":"任务名称"},{"assigneeName":"经办人","processInstanceStartTime":"发起","createTime":"到达","processInstanceInitiatorName":"发起人","dueDate":"截止","name":"流程","id":"ID","workName":"名称"}], errorMsg=错误消息}
        System.out.println(map);
         //输出 10
        System.out.println(map.get("data.page.size"));

        //获取对象相应值,key中无数组情况
        String condition = "[{\"key\": \"data.page.current\"}]";
        List<SearchCondition> conditions = JSON.parseArray(condition, SearchCondition.class);
        Object value = ObjectToMapUtils.getObjValue(json, conditions, null);
         //输出 1
        System.out.println(value);

        //获取对象对应value值的数组
        condition = "[{\"key\": \"data.rows.name\", \"value\":\"流程步骤名称\"}]";
        conditions = JSON.parseArray(condition, SearchCondition.class);
        Object obj = ObjectToMapUtils.getObjValue(json, conditions, null);
        System.out.println(obj);

        //获取对象对应value值的数组
        condition = "[{\"key\": \"data.rows.processInstanceInitiatorName\", \"value\":\"发起人\"}]";
        conditions = JSON.parseArray(condition, SearchCondition.class);
        obj = ObjectToMapUtils.getObjValue(json, conditions, null);
        System.out.println(obj);

        map = ObjectToMapUtils.trfMap(json, "-");
        System.out.println(map);
        System.out.println(map.get("data-page-size"));


