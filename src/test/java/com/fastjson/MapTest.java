package com.fastjson;

import com.alibaba.fastjson.JSON;
import com.fastjson.dto.SearchCondition;
import com.fastjson.utils.ObjectToMapUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {

    }

//    {
//        "success": 0,
//            "errorMsg": "错误消息",
//            "data": {
//        "total": "总记录数",
//                "page": {
//            "size": 10,
//                    "current":1
//        },
//        "rows": [
//        {
//            "id": "任务ID",
//                "workName": "任务名称",
//                "assigneeName": "经办人姓名",
//                "name": "流程步骤名称",
//                "processInstanceInitiatorName": "发起人",
//                "processInstanceStartTime": "发起时间",
//                "createTime": "到达时间",
//                "dueDate": "截止时间"
//        },
//        {
//            "id": "ID",
//                "workName": "名称",
//                "assigneeName": "经办人",
//                "name": "流程",
//                "processInstanceInitiatorName": "发起人",
//                "processInstanceStartTime": "发起",
//                "createTime": "到达",
//                "dueDate": "截止"
//        }
//        ]
//    }
//    }

    @Test
    public void objTrfMap() {
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

    }

}
