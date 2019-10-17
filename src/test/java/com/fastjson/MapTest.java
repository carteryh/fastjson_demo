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
        String json = "{\"success\":0,\"errorMsg\":\"错误消息\",\"data\":{\"total\":\"总记录数\",\"page\":{\"size\":10,\"current\":1},\"rows\":[{\"id\":\"任务ID\",\"workName\":\"任务名称\",\"assigneeName\":\"经办人姓名\",\"name\":\"流程步骤名称\",\"processInstanceInitiatorName\":\"发起人\",\"processInstanceStartTime\":\"发起时间\",\"createTime\":\"到达时间\",\"dueDate\":\"截止时间\"},{\"id\":\"ID\",\"workName\":\"名称\",\"assigneeName\":\"经办人\",\"name\":\"流程\",\"processInstanceInitiatorName\":\"发起人\",\"processInstanceStartTime\":\"发起\",\"createTime\":\"到达\",\"dueDate\":\"截止\"}]}}";

        //转map
        Map<String, Object> map = ObjectToMapUtils.trfMap(json, ".");

        //获取map值
        System.out.println(map);
        System.out.println(map.get("data.page.size"));

        //获取map值,key中无数组情况
        String condition = "[{\"key\": \"data.page.current\"}]";
        List<SearchCondition> conditions = JSON.parseArray(condition, SearchCondition.class);
        Object value = ObjectToMapUtils.getObjValue(json, conditions, null);
        System.out.println(value);

        //获取  对应value值的数组
        condition = "[{\"key\": \"data.rows.name\", \"value\":\"流程步骤名称\"}]";
        conditions = JSON.parseArray(condition, SearchCondition.class);
        Object obj = ObjectToMapUtils.getObjValue(json, conditions, null);
        System.out.println(obj);

        //获取  对应value值的数组
        condition = "[{\"key\": \"data.rows.processInstanceInitiatorName\", \"value\":\"发起人\"}]";
        conditions = JSON.parseArray(condition, SearchCondition.class);
        obj = ObjectToMapUtils.getObjValue(json, conditions, null);
        System.out.println(obj);

        map = ObjectToMapUtils.trfMap(json, "-");
        System.out.println(map);
        System.out.println(map.get("data-page-size"));

    }

}
