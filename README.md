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

