package com.fastjson.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class JsonUtils {

	public static void main(String[] args) throws IOException {
		/// 读取json字符串
		String json = "{\"report\":[{\"key\":\"data_type\",\"value\":\"运营商\"},{\"key\":\"source_name\",\"value\":\"chinamobilezj\"},{\"key\":\"source_name_zh\",\"value\":\"浙江移动\"},{\"key\":\"data_gain_time\",\"value\":\"2017-10-13\"},{\"key\":\"task_id\",\"value\":\"78c15210-afbb-11e7-8963-00163e0e0050\"},{\"key\":\"update_time\",\"value\":\"2017-10-13 10:08:57\"},{\"key\":\"version\",\"value\":\"1.0\"}],\"user_basic\":[{\"key\":\"name\",\"value\":\"张三\"},{\"key\":\"id_card\",\"value\":\"371521199305281420\"},{\"key\":\"gender\",\"value\":\"男\"},{\"key\":\"age\",\"value\":\"24\"},{\"key\":\"constellation\",\"value\":\"双子座\"},{\"key\":\"province\",\"value\":\"山东省\"},{\"key\":\"city\",\"value\":\"聊城市\"},{\"key\":\"region\",\"value\":\"阳谷县\"},{\"key\":\"native_place\",\"value\":\"山东省聊城市阳谷县\"}],\"cell_phone\":[{\"key\":\"mobile\",\"value\":\"13100000000\"},{\"key\":\"carrier_name\",\"value\":\"李四\"},{\"key\":\"carrier_idcard\",\"value\":\"运营商未提供身份证\"},{\"key\":\"reg_time\",\"value\":\"2013-09-21 00:00:00\"},{\"key\":\"in_time\",\"value\":\"50\"},{\"key\":\"email\",\"value\":\"运营商未提供邮箱\"},{\"key\":\"address\",\"value\":\"运营商未提供通讯地址\"},{\"key\":\"reliability\",\"value\":\"实名认证\"},{\"key\":\"phone_attribution\",\"value\":\"浙江绍兴\"},{\"key\":\"live_address\",\"value\":\"杭州\"},{\"key\":\"available_balance\",\"value\":\"4286\"},{\"key\":\"package_name\",\"value\":\"4G飞享套餐\"},{\"key\":\"bill_certification_day\",\"value\":\"2017-10-13\"}],\"basic_check_items\":[{\"result\":\"无数据\",\"comment\":null,\"check_item\":\"idcard_check\"},{\"result\":\"未提供邮箱\",\"comment\":null,\"check_item\":\"email_check\"},{\"result\":\"未提供通讯地址\",\"comment\":null,\"check_item\":\"address_check\"},{\"result\":\"通话记录正常\",\"comment\":null,\"check_item\":\"call_data_check\"},{\"result\":\"运营商未提供身份证\",\"comment\":null,\"check_item\":\"idcard_match\"},{\"result\":\"匹配失败\",\"comment\":null,\"check_item\":\"name_match\"},{\"result\":\"否\",\"comment\":null,\"check_item\":\"is_name_and_idcard_in_court_black\"},{\"result\":\"否\",\"comment\":\"\",\"check_item\":\"is_name_and_idcard_in_finance_black\"},{\"result\":\"否\",\"comment\":\"\",\"check_item\":\"is_name_and_mobile_in_finance_black\"},{\"result\":\"3\",\"comment\":null,\"check_item\":\"mobile_silence_3m\"},{\"result\":\"3\",\"comment\":null,\"check_item\":\"mobile_silence_6m\"},{\"result\":\"0\",\"comment\":null,\"check_item\":\"arrearage_risk_3m\"},{\"result\":\"0\",\"comment\":null,\"check_item\":\"arrearage_risk_6m\"},{\"result\":\"0\",\"comment\":null,\"check_item\":\"binding_risk\"}],\"application_check\":[{\"app_point\":\"contact\",\"check_points\":{\"relationship\":\"\",\"key_value\":\"13777777777\",\"contact_name\":\"111\",\"check_mobile\":\"无该联系人电话的通话记录\",\"check_xiaohao\":\"该联系人号码非临时小号\"}},{\"app_point\":\"contact\",\"check_points\":{\"relationship\":\"\",\"key_value\":\"138888888\",\"contact_name\":\"111\",\"check_mobile\":\"无该联系人电话的通话记录\",\"check_xiaohao\":\"该联系人号码非临时小号\"}}],\"behavior_check\":[{\"result\":\"朋友圈主要活跃在杭州地区(居住地)\",\"evidence\":\"杭州地区通话时间占比为21%\",\"score\":2,\"check_point\":\"regular_circle\",\"check_point_cn\":\"朋友圈在哪里\"},{\"result\":\"长期使用（24个月以上，包含24）\",\"evidence\":\"根据号码[15715848975]运营商提供的认证时间,推算该号码使用50个月\",\"score\":1,\"check_point\":\"phone_used_time\",\"check_point_cn\":\"号码使用时间\"},{\"result\":\"180天内有6天无通话记录\",\"evidence\":\"根据运营商通话详单数据，连续三天以上无通话记录0次\",\"score\":2,\"check_point\":\"phone_silent\",\"check_point_cn\":\"手机静默情况\"},{\"result\":\"关机共6天\",\"evidence\":\"根据运营商详单数据，180天内关机6天，连续三天以上关机0次\",\"score\":2,\"check_point\":\"phone_power_off\",\"check_point_cn\":\"关机情况\"},{\"result\":\"数量正常（10 - 100）\",\"evidence\":\"互通过电话的号码有49个，比例为13%，其中未被标记号码49个，被标记号码0个\",\"score\":1,\"check_point\":\"contact_each_other\",\"check_point_cn\":\"互通过电话的号码数量\"},{\"result\":\"无通话记录\",\"evidence\":\"未发现与澳门地区电话通话记录\",\"score\":1,\"check_point\":\"contact_macao\",\"check_point_cn\":\"与澳门地区电话通话情况\"},{\"result\":\"无通话记录\",\"evidence\":\"未发现与110电话通话记录\",\"score\":1,\"check_point\":\"contact_110\",\"check_point_cn\":\"与110电话通话情况\"},{\"result\":\"无通话记录\",\"evidence\":\"未发现与120电话通话记录\",\"score\":1,\"check_point\":\"contact_120\",\"check_point_cn\":\"与120电话通话情况\"},{\"result\":\"无通话记录\",\"evidence\":\"未发现与律师电话通话记录\",\"score\":1,\"check_point\":\"contact_lawyer\",\"check_point_cn\":\"与律师电话通话情况\"},{\"result\":\"无通话记录\",\"evidence\":\"未发现与法院电话通话记录\",\"score\":1,\"check_point\":\"contact_court\",\"check_point_cn\":\"与法院电话通话情况\"},{\"result\":\"无该类号码记录\",\"evidence\":\"[总计]主叫0次共0分钟；被叫0次共0分钟；号码数0个\",\"score\":1,\"check_point\":\"contact_loan\",\"check_point_cn\":\"与贷款类号码联系情况\"},{\"result\":\"无该类号码记录\",\"evidence\":\"[总计]主叫0次共0分钟；被叫0次共0分钟；号码数0个\",\"score\":1,\"check_point\":\"contact_bank\",\"check_point_cn\":\"与银行类号码联系情况\"},{\"result\":\"无该类号码记录\",\"evidence\":\"[总计]主叫0次共0分钟；被叫0次共0分钟；号码数0个\",\"score\":1,\"check_point\":\"contact_credit_card\",\"check_point_cn\":\"与信用卡类号码联系情况\"},{\"result\":\"无该类号码记录\",\"evidence\":\"[总计]主叫0次共0分钟；被叫0次共0分钟；号码数0个\",\"score\":1,\"check_point\":\"contact_collection\",\"check_point_cn\":\"与催收类号码联系情况\"},{\"result\":\"很少夜间活动（低于20%)\",\"evidence\":\"晚间活跃频率占全天的0.1%\",\"score\":1,\"check_point\":\"contact_night\",\"check_point_cn\":\"夜间活动情况\"},{\"result\":\"无数据\",\"evidence\":\"未提供电商数据\",\"score\":0,\"check_point\":\"dwell_used_time\",\"check_point_cn\":\"居住地本地（省份）地址在电商中使用时长\"},{\"result\":\"无数据\",\"evidence\":\"未提供电商数据\",\"score\":0,\"check_point\":\"ebusiness_info\",\"check_point_cn\":\"总体电商使用情况\"},{\"result\":\"无数据\",\"evidence\":\"未提供电商数据\",\"score\":0,\"check_point\":\"person_ebusiness_info\",\"check_point_cn\":\"申请人本人电商使用情况\"},{\"result\":\"无数据\",\"evidence\":\"未提供电商数据\",\"score\":0,\"check_point\":\"virtual_buying\",\"check_point_cn\":\"虚拟商品购买情况\"},{\"result\":\"无数据\",\"evidence\":\"未提供电商数据\",\"score\":0,\"check_point\":\"lottery_buying\",\"check_point_cn\":\"彩票购买情况\"},{\"result\":\"无数据\",\"evidence\":\"未提供电商数据\",\"score\":0,\"check_point\":\"person_addr_changed\",\"check_point_cn\":\"申请人本人地址变化情况\"},{\"result\":\"无数据\",\"evidence\":\"未提供学信网数据\",\"score\":0,\"check_point\":\"school_status\",\"check_point_cn\":\"申请人的学籍状态是否为在校学生\"},{\"result\":\"无数据\",\"evidence\":\"未提供学历数据\",\"score\":0,\"check_point\":\"education_info\",\"check_point_cn\":\"申请人的学历情况\"},{\"result\":\"无数据\",\"evidence\":\"未提供电商数据\",\"score\":0,\"check_point\":\"work_addr_info\",\"check_point_cn\":\"申请人本人最近使用工作地址情况\"},{\"result\":\"无数据\",\"evidence\":\"未提供电商数据\",\"score\":0,\"check_point\":\"live_addr_info\",\"check_point_cn\":\"申请人本人最近使用居住地址情况\"},{\"result\":\"无数据\",\"evidence\":\"未提供电商数据\",\"score\":0,\"check_point\":\"school_addr_info\",\"check_point_cn\":\"申请人本人最近使用学校地址情况\"},{\"result\":\"数量众多（100以上，不含100）\",\"evidence\":\"[总计]号码数384个；主叫228次共434分钟；被叫431次共490分钟，联系列表：[中介]号码数1个；主叫0次共0分钟；被叫1次共0分钟，[快递公司]号码数10个；主叫2次共0分钟；被叫9次共2分钟，[通信服务机构]号码数5个；主叫20次共32分钟；被叫11次共8分钟\",\"score\":1,\"check_point\":\"phone_call\",\"check_point_cn\":\"号码通话情况\"}],\"friend_circle\":{\"summary\":[{\"key\":\"friend_num_3m\",\"value\":\"183\"},{\"key\":\"good_friend_num_3m\",\"value\":\"2\"},{\"key\":\"friend_city_center_3m\",\"value\":\"杭州\"},{\"key\":\"is_city_match_friend_city_center_3m\",\"value\":\"否\"},{\"key\":\"inter_peer_num_3m\",\"value\":\"22\"},{\"key\":\"friend_num_6m\",\"value\":\"384\"},{\"key\":\"good_friend_num_6m\",\"value\":\"4\"},{\"key\":\"friend_city_center_6m\",\"value\":\"杭州\"},{\"key\":\"is_city_match_friend_city_center_6m\",\"value\":\"否\"},{\"key\":\"inter_peer_num_6m\",\"value\":\"49\"}],\"peer_num_top_list\":[{\"key\":\"peer_num_top3_3m\",\"top_item\":[{\"peer_number\":\"13101111111\",\"peer_num_loc\":\"贵阳\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt\":35,\"call_time\":5582,\"dial_cnt\":12,\"dialed_cnt\":23,\"dial_time\":1755,\"dialed_time\":3827},{\"peer_number\":\"13102222222\",\"peer_num_loc\":\"温州\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt\":20,\"call_time\":2252,\"dial_cnt\":19,\"dialed_cnt\":1,\"dial_time\":2210,\"dialed_time\":42},{\"peer_number\":\"13103333333\",\"peer_num_loc\":\"宁波\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt\":10,\"call_time\":522,\"dial_cnt\":5,\"dialed_cnt\":5,\"dial_time\":251,\"dialed_time\":271}]},{\"key\":\"peer_num_top3_6m\",\"top_item\":[{\"peer_number\":\"13101111111\",\"peer_num_loc\":\"温州\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt\":41,\"call_time\":3808,\"dial_cnt\":26,\"dialed_cnt\":15,\"dial_time\":2862,\"dialed_time\":946},{\"peer_number\":\"13102222222\",\"peer_num_loc\":\"贵阳\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt\":37,\"call_time\":6094,\"dial_cnt\":13,\"dialed_cnt\":24,\"dial_time\":2186,\"dialed_time\":3908},{\"peer_number\":\"057110086\",\"peer_num_loc\":\"杭州\",\"group_name\":\"通信服务机构\",\"company_name\":\"中国移动客服热线\",\"call_cnt\":12,\"call_time\":1320,\"dial_cnt\":12,\"dialed_cnt\":0,\"dial_time\":1320,\"dialed_time\":0}]}],\"location_top_list\":[{\"key\":\"location_top3_3m\",\"top_item\":[{\"location\":\"杭州\",\"peer_number_cnt\":124,\"call_cnt\":173,\"call_time\":5373,\"dial_cnt\":56,\"dialed_cnt\":117,\"dial_time\":2880,\"dialed_time\":2493},{\"location\":\"贵阳\",\"peer_number_cnt\":1,\"call_cnt\":35,\"call_time\":5582,\"dial_cnt\":12,\"dialed_cnt\":23,\"dial_time\":1755,\"dialed_time\":3827},{\"location\":\"温州\",\"peer_number_cnt\":9,\"call_cnt\":35,\"call_time\":3178,\"dial_cnt\":29,\"dialed_cnt\":6,\"dial_time\":2971,\"dialed_time\":207}]},{\"key\":\"location_top3_6m\",\"top_item\":[{\"location\":\"杭州\",\"peer_number_cnt\":235,\"call_cnt\":326,\"call_time\":11847,\"dial_cnt\":98,\"dialed_cnt\":228,\"dial_time\":5544,\"dialed_time\":6303},{\"location\":\"绍兴\",\"peer_number_cnt\":57,\"call_cnt\":87,\"call_time\":2505,\"dial_cnt\":20,\"dialed_cnt\":67,\"dial_time\":653,\"dialed_time\":1852},{\"location\":\"温州\",\"peer_number_cnt\":13,\"call_cnt\":64,\"call_time\":7531,\"dial_cnt\":40,\"dialed_cnt\":24,\"dial_time\":6264,\"dialed_time\":1267}]}]},\"cell_behavior\":[{\"behavior\":[{\"sms_cnt\":70,\"cell_phone_num\":\"13100000000\",\"net_flow\":0,\"total_amount\":3899,\"cell_mth\":\"2017-10\",\"cell_loc\":\"浙江绍兴\",\"cell_operator_zh\":\"浙江移动\",\"cell_operator\":\"chinamobilezj\",\"call_cnt\":40,\"call_time\":1336,\"dial_cnt\":18,\"dial_time\":673,\"dialed_cnt\":22,\"dialed_time\":663,\"rechange_cnt\":1,\"rechange_amount\":5000},{\"sms_cnt\":176,\"cell_phone_num\":\"13100000000\",\"net_flow\":0,\"total_amount\":10776,\"cell_mth\":\"2017-09\",\"cell_loc\":\"浙江绍兴\",\"cell_operator_zh\":\"浙江移动\",\"cell_operator\":\"chinamobilezj\",\"call_cnt\":107,\"call_time\":12909,\"dial_cnt\":40,\"dial_time\":5462,\"dialed_cnt\":67,\"dialed_time\":7447,\"rechange_cnt\":4,\"rechange_amount\":12000},{\"sms_cnt\":285,\"cell_phone_num\":\"13100000000\",\"net_flow\":0,\"total_amount\":8751,\"cell_mth\":\"2017-08\",\"cell_loc\":\"浙江绍兴\",\"cell_operator_zh\":\"浙江移动\",\"cell_operator\":\"chinamobilezj\",\"call_cnt\":81,\"call_time\":10353,\"dial_cnt\":41,\"dial_time\":5654,\"dialed_cnt\":40,\"dialed_time\":4699,\"rechange_cnt\":2,\"rechange_amount\":2000},{\"sms_cnt\":115,\"cell_phone_num\":\"13100000000\",\"net_flow\":0,\"total_amount\":11414,\"cell_mth\":\"2017-07\",\"cell_loc\":\"浙江绍兴\",\"cell_operator_zh\":\"浙江移动\",\"cell_operator\":\"chinamobilezj\",\"call_cnt\":164,\"call_time\":14319,\"dial_cnt\":60,\"dial_time\":7036,\"dialed_cnt\":104,\"dialed_time\":7283,\"rechange_cnt\":4,\"rechange_amount\":20000},{\"sms_cnt\":116,\"cell_phone_num\":\"13100000000\",\"net_flow\":0,\"total_amount\":7860,\"cell_mth\":\"2017-06\",\"cell_loc\":\"浙江绍兴\",\"cell_operator_zh\":\"浙江移动\",\"cell_operator\":\"chinamobilezj\",\"call_cnt\":115,\"call_time\":9998,\"dial_cnt\":35,\"dial_time\":5761,\"dialed_cnt\":80,\"dialed_time\":4237,\"rechange_cnt\":2,\"rechange_amount\":6000},{\"sms_cnt\":56,\"cell_phone_num\":\"13100000000\",\"net_flow\":0,\"total_amount\":8800,\"cell_mth\":\"2017-05\",\"cell_loc\":\"浙江绍兴\",\"cell_operator_zh\":\"浙江移动\",\"cell_operator\":\"chinamobilezj\",\"call_cnt\":108,\"call_time\":5252,\"dial_cnt\":28,\"dial_time\":1254,\"dialed_cnt\":80,\"dialed_time\":3998,\"rechange_cnt\":2,\"rechange_amount\":10000}],\"phone_num\":\"13100000000\"}],\"call_contact_detail\":[{\"city\":\"温州\",\"p_relation\":\"\",\"peer_num\":\"13101111111\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt_1w\":1,\"call_cnt_1m\":3,\"call_cnt_3m\":20,\"call_cnt_6m\":41,\"call_time_3m\":2252,\"call_time_6m\":3808,\"dial_cnt_3m\":19,\"dial_cnt_6m\":26,\"dial_time_3m\":2210,\"dial_time_6m\":2862,\"dialed_cnt_3m\":1,\"dialed_cnt_6m\":15,\"dialed_time_3m\":42,\"dialed_time_6m\":946,\"call_cnt_morning_3m\":3,\"call_cnt_morning_6m\":7,\"call_cnt_noon_3m\":1,\"call_cnt_noon_6m\":1,\"call_cnt_afternoon_3m\":4,\"call_cnt_afternoon_6m\":9,\"call_cnt_evening_3m\":12,\"call_cnt_evening_6m\":24,\"call_cnt_night_3m\":0,\"call_cnt_night_6m\":0,\"call_cnt_weekday_3m\":11,\"call_cnt_weekday_6m\":21,\"call_cnt_weekend_3m\":6,\"call_cnt_weekend_6m\":13,\"call_cnt_holiday_3m\":3,\"call_cnt_holiday_6m\":7,\"call_if_whole_day_3m\":false,\"call_if_whole_day_6m\":false,\"trans_start\":\"2017-04-18 19:29:38\",\"trans_end\":\"2017-10-07 07:48:15\",\"max_call_time_6m\":298,\"min_call_time_6m\":7,\"avg_call_time_6m\":92},{\"city\":\"北京\",\"p_relation\":\"\",\"peer_num\":\"13102222222\",\"group_name\":\"未知\",\"company_name\":\"未知\",\"call_cnt_1w\":0,\"call_cnt_1m\":0,\"call_cnt_3m\":35,\"call_cnt_6m\":37,\"call_time_3m\":5582,\"call_time_6m\":6094,\"dial_cnt_3m\":12,\"dial_cnt_6m\":13,\"dial_time_3m\":1755,\"dial_time_6m\":2186,\"dialed_cnt_3m\":23,\"dialed_cnt_6m\":24,\"dialed_time_3m\":3827,\"dialed_time_6m\":3908,\"call_cnt_morning_3m\":6,\"call_cnt_morning_6m\":6,\"call_cnt_noon_3m\":3,\"call_cnt_noon_6m\":3,\"call_cnt_afternoon_3m\":2,\"call_cnt_afternoon_6m\":2,\"call_cnt_evening_3m\":22,\"call_cnt_evening_6m\":23,\"call_cnt_night_3m\":2,\"call_cnt_night_6m\":3,\"call_cnt_weekday_3m\":27,\"call_cnt_weekday_6m\":28,\"call_cnt_weekend_3m\":8,\"call_cnt_weekend_6m\":9,\"call_cnt_holiday_3m\":0,\"call_cnt_holiday_6m\":0,\"call_if_whole_day_3m\":false,\"call_if_whole_day_6m\":false,\"trans_start\":\"2017-07-14 23:58:20\",\"trans_end\":\"2017-08-23 23:12:15\",\"max_call_time_6m\":894,\"min_call_time_6m\":30,\"avg_call_time_6m\":164},{\"city\":\"杭州\",\"p_relation\":\"\",\"peer_num\":\"057110086\",\"group_name\":\"通信服务机构\",\"company_name\":\"中国移动客服热线\",\"call_cnt_1w\":0,\"call_cnt_1m\":2,\"call_cnt_3m\":7,\"call_cnt_6m\":12,\"call_time_3m\":736,\"call_time_6m\":1320,\"dial_cnt_3m\":7,\"dial_cnt_6m\":12,\"dial_time_3m\":736,\"dial_time_6m\":1320,\"dialed_cnt_3m\":0,\"dialed_cnt_6m\":0,\"dialed_time_3m\":0,\"dialed_time_6m\":0,\"call_cnt_morning_3m\":2,\"call_cnt_morning_6m\":5,\"call_cnt_noon_3m\":2,\"call_cnt_noon_6m\":2,\"call_cnt_afternoon_3m\":1,\"call_cnt_afternoon_6m\":2,\"call_cnt_evening_3m\":2,\"call_cnt_evening_6m\":3,\"call_cnt_night_3m\":0,\"call_cnt_night_6m\":0,\"call_cnt_weekday_3m\":6,\"call_cnt_weekday_6m\":7,\"call_cnt_weekend_3m\":1,\"call_cnt_weekend_6m\":5,\"call_cnt_holiday_3m\":0,\"call_cnt_holiday_6m\":0,\"call_if_whole_day_3m\":false,\"call_if_whole_day_6m\":false,\"trans_start\":\"2017-06-10 07:58:44\",\"trans_end\":\"2017-09-25 09:50:34\",\"max_call_time_6m\":351,\"min_call_time_6m\":1,\"avg_call_time_6m\":110}],\"sms_contact_detail\":[{\"peer_num\":\"10086\",\"sms_cnt_1w\":63,\"sms_cnt_1m\":160,\"sms_cnt_3m\":566,\"sms_cnt_6m\":780,\"send_cnt_3m\":8,\"send_cnt_6m\":14,\"receive_cnt_3m\":558,\"receive_cnt_6m\":766},{\"peer_num\":\"10086700\",\"sms_cnt_1w\":0,\"sms_cnt_1m\":0,\"sms_cnt_3m\":10,\"sms_cnt_6m\":18,\"send_cnt_3m\":1,\"send_cnt_6m\":3,\"receive_cnt_3m\":9,\"receive_cnt_6m\":15}],\"contact_region\":[{\"key\":\"contact_region_3m\",\"desc\":\"联系人手机号码归属地 (近3月联系次数降序)\",\"region_list\":[{\"region_loc\":\"杭州\",\"region_uniq_num_cnt\":124,\"region_call_cnt\":173,\"region_call_time\":5373,\"region_dial_cnt\":56,\"region_dial_time\":2880,\"region_dialed_cnt\":117,\"region_dialed_time\":2493,\"region_avg_dial_time\":51,\"region_avg_dialed_time\":21,\"region_dial_cnt_pct\":0.32,\"region_dial_time_pct\":0.54,\"region_dialed_cnt_pct\":0.68,\"region_dialed_time_pct\":0.46},{\"region_loc\":\"贵阳\",\"region_uniq_num_cnt\":1,\"region_call_cnt\":35,\"region_call_time\":5582,\"region_dial_cnt\":12,\"region_dial_time\":1755,\"region_dialed_cnt\":23,\"region_dialed_time\":3827,\"region_avg_dial_time\":146,\"region_avg_dialed_time\":166,\"region_dial_cnt_pct\":0.34,\"region_dial_time_pct\":0.31,\"region_dialed_cnt_pct\":0.66,\"region_dialed_time_pct\":0.69},{\"region_loc\":\"北海\",\"region_uniq_num_cnt\":1,\"region_call_cnt\":1,\"region_call_time\":4,\"region_dial_cnt\":0,\"region_dial_time\":0,\"region_dialed_cnt\":1,\"region_dialed_time\":4,\"region_avg_dial_time\":0,\"region_avg_dialed_time\":4,\"region_dial_cnt_pct\":0,\"region_dial_time_pct\":0,\"region_dialed_cnt_pct\":1,\"region_dialed_time_pct\":1}]}],\"call_risk_analysis\":[{\"analysis_item\":\"110\",\"analysis_desc\":\"110\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"120\",\"analysis_desc\":\"120\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"loan\",\"analysis_desc\":\"贷款\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"credit_card\",\"analysis_desc\":\"信用卡\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"macao\",\"analysis_desc\":\"澳门地区\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"collection\",\"analysis_desc\":\"催收公司\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"lawyer\",\"analysis_desc\":\"律师\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"agency\",\"analysis_desc\":\"中介\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":1,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0.17,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":41,\"avg_call_time_3m\":0,\"avg_call_time_6m\":7,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":1,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0.17,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":41,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":7}}},{\"analysis_item\":\"fraud\",\"analysis_desc\":\"诈骗电话\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"nuisance\",\"analysis_desc\":\"骚扰电话\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":2,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0.33,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":16,\"avg_call_time_3m\":0,\"avg_call_time_6m\":3,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":2,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0.33,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":16,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":3}}},{\"analysis_item\":\"court\",\"analysis_desc\":\"法院\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}}],\"main_service\":[{\"service_num\":\"057110086\",\"group_name\":\"通信服务机构\",\"company_name\":\"中国移动客服热线\",\"total_service_cnt\":12,\"service_details\":[{\"interact_mth\":\"2017-09\",\"interact_cnt\":4,\"interact_time\":159,\"dial_cnt\":4,\"dialed_cnt\":0,\"dial_time\":159,\"dialed_time\":0},{\"interact_mth\":\"2017-08\",\"interact_cnt\":2,\"interact_time\":226,\"dial_cnt\":2,\"dialed_cnt\":0,\"dial_time\":226,\"dialed_time\":0},{\"interact_mth\":\"2017-07\",\"interact_cnt\":1,\"interact_time\":351,\"dial_cnt\":1,\"dialed_cnt\":0,\"dial_time\":351,\"dialed_time\":0},{\"interact_mth\":\"2017-06\",\"interact_cnt\":5,\"interact_time\":584,\"dial_cnt\":5,\"dialed_cnt\":0,\"dial_time\":584,\"dialed_time\":0}]},{\"service_num\":\"10000\",\"group_name\":\"通信服务机构\",\"company_name\":\"中国电信综合服务\",\"total_service_cnt\":6,\"service_details\":[{\"interact_mth\":\"2017-09\",\"interact_cnt\":4,\"interact_time\":492,\"dial_cnt\":4,\"dialed_cnt\":0,\"dial_time\":492,\"dialed_time\":0},{\"interact_mth\":\"2017-08\",\"interact_cnt\":2,\"interact_time\":31,\"dial_cnt\":2,\"dialed_cnt\":0,\"dial_time\":31,\"dialed_time\":0}]},{\"service_num\":\"10010\",\"group_name\":\"通信服务机构\",\"company_name\":\"中国联通客服热线\",\"total_service_cnt\":1,\"service_details\":[{\"interact_mth\":\"2017-09\",\"interact_cnt\":1,\"interact_time\":6,\"dial_cnt\":1,\"dialed_cnt\":0,\"dial_time\":6,\"dialed_time\":0}]},{\"service_num\":\"057510086\",\"group_name\":\"通信服务机构\",\"company_name\":\"中国移动客服热线\",\"total_service_cnt\":11,\"service_details\":[{\"interact_mth\":\"2017-10\",\"interact_cnt\":1,\"interact_time\":12,\"dial_cnt\":0,\"dialed_cnt\":1,\"dial_time\":0,\"dialed_time\":12},{\"interact_mth\":\"2017-09\",\"interact_cnt\":1,\"interact_time\":11,\"dial_cnt\":0,\"dialed_cnt\":1,\"dial_time\":0,\"dialed_time\":11},{\"interact_mth\":\"2017-08\",\"interact_cnt\":1,\"interact_time\":90,\"dial_cnt\":0,\"dialed_cnt\":1,\"dial_time\":0,\"dialed_time\":90},{\"interact_mth\":\"2017-07\",\"interact_cnt\":1,\"interact_time\":7,\"dial_cnt\":0,\"dialed_cnt\":1,\"dial_time\":0,\"dialed_time\":7},{\"interact_mth\":\"2017-06\",\"interact_cnt\":7,\"interact_time\":511,\"dial_cnt\":1,\"dialed_cnt\":6,\"dial_time\":105,\"dialed_time\":406}]}],\"call_service_analysis\":[{\"analysis_item\":\"bank\",\"analysis_desc\":\"银行\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"railway_airway\",\"analysis_desc\":\"航旅机构\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"special_service\",\"analysis_desc\":\"特种服务\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"express\",\"analysis_desc\":\"快递公司\",\"analysis_point\":{\"call_cnt_1m\":1,\"call_cnt_3m\":6,\"call_cnt_6m\":11,\"avg_call_cnt_3m\":2,\"avg_call_cnt_6m\":1.83,\"call_time_1m\":18,\"call_time_3m\":102,\"call_time_6m\":167,\"avg_call_time_3m\":34,\"avg_call_time_6m\":28,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":1,\"call_dial_cnt_3m\":2,\"call_dial_cnt_6m\":2,\"avg_call_dial_cnt_3m\":0.67,\"avg_call_dial_cnt_6m\":0.33,\"call_dial_time_1m\":18,\"call_dial_time_3m\":41,\"call_dial_time_6m\":41,\"avg_call_dial_time_3m\":14,\"avg_call_dial_time_6m\":7},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":4,\"call_dialed_cnt_6m\":9,\"avg_call_dialed_cnt_3m\":1.33,\"avg_call_dialed_cnt_6m\":1.5,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":61,\"call_dialed_time_6m\":126,\"avg_call_dialed_time_3m\":20,\"avg_call_dialed_time_6m\":21}}},{\"analysis_item\":\"ins_fin\",\"analysis_desc\":\"保险公司\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"car_firm\",\"analysis_desc\":\"汽车公司\",\"analysis_point\":{\"call_cnt_1m\":0,\"call_cnt_3m\":0,\"call_cnt_6m\":0,\"avg_call_cnt_3m\":0,\"avg_call_cnt_6m\":0,\"call_time_1m\":0,\"call_time_3m\":0,\"call_time_6m\":0,\"avg_call_time_3m\":0,\"avg_call_time_6m\":0,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":0,\"call_dial_cnt_3m\":0,\"call_dial_cnt_6m\":0,\"avg_call_dial_cnt_3m\":0,\"avg_call_dial_cnt_6m\":0,\"call_dial_time_1m\":0,\"call_dial_time_3m\":0,\"call_dial_time_6m\":0,\"avg_call_dial_time_3m\":0,\"avg_call_dial_time_6m\":0},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":0,\"call_dialed_cnt_3m\":0,\"call_dialed_cnt_6m\":0,\"avg_call_dialed_cnt_3m\":0,\"avg_call_dialed_cnt_6m\":0,\"call_dialed_time_1m\":0,\"call_dialed_time_3m\":0,\"call_dialed_time_6m\":0,\"avg_call_dialed_time_3m\":0,\"avg_call_dialed_time_6m\":0}}},{\"analysis_item\":\"carrier\",\"analysis_desc\":\"通信服务机构\",\"analysis_point\":{\"call_cnt_1m\":8,\"call_cnt_3m\":18,\"call_cnt_6m\":31,\"avg_call_cnt_3m\":6,\"avg_call_cnt_6m\":5.17,\"call_time_1m\":515,\"call_time_3m\":1385,\"call_time_6m\":2487,\"avg_call_time_3m\":462,\"avg_call_time_6m\":415,\"call_analysis_dial_point\":{\"call_dial_cnt_1m\":7,\"call_dial_cnt_3m\":14,\"call_dial_cnt_6m\":20,\"avg_call_dial_cnt_3m\":4.67,\"avg_call_dial_cnt_6m\":3.33,\"call_dial_time_1m\":503,\"call_dial_time_3m\":1265,\"call_dial_time_6m\":1954,\"avg_call_dial_time_3m\":422,\"avg_call_dial_time_6m\":326},\"call_analysis_dialed_point\":{\"call_dialed_cnt_1m\":1,\"call_dialed_cnt_3m\":4,\"call_dialed_cnt_6m\":11,\"avg_call_dialed_cnt_3m\":1.33,\"avg_call_dialed_cnt_6m\":1.83,\"call_dialed_time_1m\":12,\"call_dialed_time_3m\":120,\"call_dialed_time_6m\":533,\"avg_call_dialed_time_3m\":40,\"avg_call_dialed_time_6m\":89}}}],\"active_degree\":[{\"item\":{\"item_1m\":\"28\",\"item_3m\":\"86\",\"item_6m\":\"174\",\"avg_item_3m\":\"28.67\",\"avg_item_6m\":\"29.00\"},\"app_point\":\"call_day\",\"app_point_zh\":\"通话活跃天数\"},{\"item\":{\"item_1m\":\"19\",\"item_3m\":\"55\",\"item_6m\":\"104\",\"avg_item_3m\":\"18.33\",\"avg_item_6m\":\"17.33\"},\"app_point\":\"sms_day\",\"app_point_zh\":\"短信活跃天数\"},{\"item\":{\"item_1m\":\"3\",\"item_3m\":\"9\",\"item_6m\":\"15\",\"avg_item_3m\":\"3.00\",\"avg_item_6m\":\"2.50\"},\"app_point\":\"recharge_cnt\",\"app_point_zh\":\"充值次数\"},{\"item\":{\"item_1m\":\"94\",\"item_3m\":\"326\",\"item_6m\":\"659\",\"avg_item_3m\":\"108.67\",\"avg_item_6m\":\"109.83\"},\"app_point\":\"call_cnt\",\"app_point_zh\":\"通话次数\"},{\"item\":{\"item_1m\":\"70\",\"item_3m\":\"183\",\"item_6m\":\"384\",\"avg_item_3m\":\"61.00\",\"avg_item_6m\":\"64.00\"},\"app_point\":\"peer_num_cnt\",\"app_point_zh\":\"通话号码数目\"},{\"item\":{\"item_1m\":\"13\",\"item_3m\":\"27\",\"item_6m\":\"43\",\"avg_item_3m\":\"9.00\",\"avg_item_6m\":\"7.17\"},\"app_point\":\"peer_loc_cnt\",\"app_point_zh\":\"通话号码归属地总数\"},{\"item\":{\"item_1m\":\"37\",\"item_3m\":\"136\",\"item_6m\":\"228\",\"avg_item_3m\":\"45.33\",\"avg_item_6m\":\"38.00\"},\"app_point\":\"dial_cnt\",\"app_point_zh\":\"主叫次数\"},{\"item\":{\"item_1m\":\"57\",\"item_3m\":\"190\",\"item_6m\":\"431\",\"avg_item_3m\":\"63.33\",\"avg_item_6m\":\"71.83\"},\"app_point\":\"dialed_cnt\",\"app_point_zh\":\"被叫次数\"},{\"item\":{\"item_1m\":\"27\",\"item_3m\":\"72\",\"item_6m\":\"129\",\"avg_item_3m\":\"24.00\",\"avg_item_6m\":\"21.50\"},\"app_point\":\"dial_peer_num_cnt\",\"app_point_zh\":\"主叫号码数\"},{\"item\":{\"item_1m\":\"48\",\"item_3m\":\"133\",\"item_6m\":\"304\",\"avg_item_3m\":\"44.33\",\"avg_item_6m\":\"50.67\"},\"app_point\":\"dialed_peer_num_cnt\",\"app_point_zh\":\"被叫号码数\"},{\"item\":{\"item_1m\":\"167\",\"item_3m\":\"605\",\"item_6m\":\"842\",\"avg_item_3m\":\"201.67\",\"avg_item_6m\":\"140.33\"},\"app_point\":\"sms_cnt\",\"app_point_zh\":\"短信次数\"},{\"item\":{\"item_1m\":\"6572\",\"item_3m\":\"31748\",\"item_6m\":\"55455\",\"avg_item_3m\":\"10583\",\"avg_item_6m\":\"9243\"},\"app_point\":\"call_time\",\"app_point_zh\":\"通话时长（秒）\"},{\"item\":{\"item_1m\":\"2748\",\"item_3m\":\"15098\",\"item_6m\":\"26055\",\"avg_item_3m\":\"5033\",\"avg_item_6m\":\"4343\"},\"app_point\":\"dial_time\",\"app_point_zh\":\"主叫时长（秒）\"},{\"item\":{\"item_1m\":\"3824\",\"item_3m\":\"16650\",\"item_6m\":\"29400\",\"avg_item_3m\":\"5550\",\"avg_item_6m\":\"4900\"},\"app_point\":\"dialed_time\",\"app_point_zh\":\"被叫时长（秒）\"},{\"item\":{\"item_1m\":\"59973632\",\"item_3m\":\"278659835\",\"item_6m\":\"417738630\",\"avg_item_3m\":\"92886612\",\"avg_item_6m\":\"69623105\"},\"app_point\":\"net_total\",\"app_point_zh\":\"流量套餐总量（KB）\"},{\"item\":{\"item_1m\":\"9297202\",\"item_3m\":\"43858552\",\"item_6m\":\"128377549\",\"avg_item_3m\":\"14619517\",\"avg_item_6m\":\"21396258\"},\"app_point\":\"net_used\",\"app_point_zh\":\"流量套餐使用量（KB）\"},{\"item\":{\"item_1m\":\"70\",\"item_3m\":\"97\",\"item_6m\":\"84\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"avg_call_time\",\"app_point_zh\":\"均次通话时长（秒）\"},{\"item\":{\"item_1m\":\"12\",\"item_3m\":\"30\",\"item_6m\":\"73\",\"avg_item_3m\":\"10.00\",\"avg_item_6m\":\"12.17\"},\"app_point\":\"no_dial_day\",\"app_point_zh\":\"无呼出天数\"},{\"item\":{\"item_1m\":\"0.40\",\"item_3m\":\"0.33\",\"item_6m\":\"0.41\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"no_dial_day_pct\",\"app_point_zh\":\"无呼出天数占比\"},{\"item\":{\"item_1m\":\"2\",\"item_3m\":\"4\",\"item_6m\":\"6\",\"avg_item_3m\":\"1.33\",\"avg_item_6m\":\"1.00\"},\"app_point\":\"no_call_day\",\"app_point_zh\":\"无通话天数\"},{\"item\":{\"item_1m\":\"0.07\",\"item_3m\":\"0.04\",\"item_6m\":\"0.03\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"no_call_day_pct\",\"app_point_zh\":\"无通话天数占比\"},{\"item\":{\"item_1m\":\"14\",\"item_3m\":\"35\",\"item_6m\":\"77\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"max_power_on_day\",\"app_point_zh\":\"最大连续开机天数\"},{\"item\":{\"item_1m\":\"2\",\"item_3m\":\"4\",\"item_6m\":\"6\",\"avg_item_3m\":\"1.33\",\"avg_item_6m\":\"1.00\"},\"app_point\":\"power_off_day\",\"app_point_zh\":\"关机天数\"},{\"item\":{\"item_1m\":\"0.07\",\"item_3m\":\"0.04\",\"item_6m\":\"0.03\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"power_off_day_pct\",\"app_point_zh\":\"关机天数占比\"},{\"item\":{\"item_1m\":\"0\",\"item_3m\":\"0\",\"item_6m\":\"0\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"continue_power_off_cnt\",\"app_point_zh\":\"连续3天以上关机次数\"}],\"consumption_detail\":[{\"item\":{\"item_1m\":\"3899\",\"item_3m\":\"42837\",\"item_6m\":\"74511\",\"avg_item_3m\":\"14279\",\"avg_item_6m\":\"12419\"},\"app_point\":\"total_fee\",\"app_point_zh\":\"消费总金额（分）\"},{\"item\":{\"item_1m\":\"0\",\"item_3m\":\"0\",\"item_6m\":\"0\",\"avg_item_3m\":\"0\",\"avg_item_6m\":\"0\"},\"app_point\":\"net_fee\",\"app_point_zh\":\"网络流量消费金额（分）\"},{\"item\":{\"item_1m\":\"10\",\"item_3m\":\"1417\",\"item_6m\":\"2823\",\"avg_item_3m\":\"472\",\"avg_item_6m\":\"471\"},\"app_point\":\"voice_fee\",\"app_point_zh\":\"通话消费金额（分）\"},{\"item\":{\"item_1m\":\"0\",\"item_3m\":\"120\",\"item_6m\":\"210\",\"avg_item_3m\":\"40\",\"avg_item_6m\":\"35\"},\"app_point\":\"sms_fee\",\"app_point_zh\":\"短信消费金额（分）\"},{\"item\":{\"item_1m\":\"214\",\"item_3m\":\"1214\",\"item_6m\":\"2714\",\"avg_item_3m\":\"405\",\"avg_item_6m\":\"452\"},\"app_point\":\"vas_fee\",\"app_point_zh\":\"增值业务消费金额（分）\"},{\"item\":{\"item_1m\":\"0\",\"item_3m\":\"6589\",\"item_6m\":\"10189\",\"avg_item_3m\":\"2196\",\"avg_item_6m\":\"1698\"},\"app_point\":\"extra_fee\",\"app_point_zh\":\"其它消费金额（分）\"},{\"item\":{\"item_1m\":\"11000\",\"item_3m\":\"29000\",\"item_6m\":\"55000\",\"avg_item_3m\":\"9667\",\"avg_item_6m\":\"9167\"},\"app_point\":\"recharge_amount\",\"app_point_zh\":\"充值金额（分）\"},{\"item\":{\"item_1m\":\"5000\",\"item_3m\":\"5000\",\"item_6m\":\"5000\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"max_single_recharge\",\"app_point_zh\":\"单次充值最大金额（分）\"}],\"call_time_detail\":[{\"item\":{\"item_1m\":\"642\",\"item_3m\":\"1825\",\"item_6m\":\"2243\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"max_single_time\",\"app_point_zh\":\"单次通话最长时长（秒）\"},{\"item\":{\"item_1m\":\"1\",\"item_3m\":\"1\",\"item_6m\":\"1\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"min_single_time\",\"app_point_zh\":\"单次通话最短时长（秒）\"},{\"item\":{\"item_1m\":\"77\",\"item_3m\":\"233\",\"item_6m\":\"499\",\"avg_item_3m\":\"77.67\",\"avg_item_6m\":\"83.17\"},\"app_point\":\"cnt_1min_within\",\"app_point_zh\":\"时长在1min内的通话次数\"},{\"item\":{\"item_1m\":\"8\",\"item_3m\":\"65\",\"item_6m\":\"118\",\"avg_item_3m\":\"21.67\",\"avg_item_6m\":\"19.67\"},\"app_point\":\"cnt_1min_5min\",\"app_point_zh\":\"时长在1min-5min内的通话次数\"},{\"item\":{\"item_1m\":\"8\",\"item_3m\":\"20\",\"item_6m\":\"26\",\"avg_item_3m\":\"6.67\",\"avg_item_6m\":\"4.33\"},\"app_point\":\"cnt_5min_10min\",\"app_point_zh\":\"时长在5min-10min内的通话次数\"},{\"item\":{\"item_1m\":\"1\",\"item_3m\":\"8\",\"item_6m\":\"16\",\"avg_item_3m\":\"2.67\",\"avg_item_6m\":\"2.67\"},\"app_point\":\"cnt_10min_over\",\"app_point_zh\":\"时长在10min以上的通话次数\"},{\"item\":{\"item_1m\":\"93\",\"item_3m\":\"324\",\"item_6m\":\"657\",\"avg_item_3m\":\"108.00\",\"avg_item_6m\":\"109.50\"},\"app_point\":\"daytime_cnt\",\"app_point_zh\":\"白天(7:00-0:00)通话次数\"},{\"item\":{\"item_1m\":\"1\",\"item_3m\":\"2\",\"item_6m\":\"2\",\"avg_item_3m\":\"0.67\",\"avg_item_6m\":\"0.33\"},\"app_point\":\"night_cnt\",\"app_point_zh\":\"夜晚(0:00-7:00)通话次数\"},{\"item\":{\"item_1m\":\"6567\",\"item_3m\":\"31690\",\"item_6m\":\"55397\",\"avg_item_3m\":\"10563\",\"avg_item_6m\":\"9233\"},\"app_point\":\"daytime_time\",\"app_point_zh\":\"白天(7:00-0:00)通话时长（秒）\"},{\"item\":{\"item_1m\":\"5\",\"item_3m\":\"58\",\"item_6m\":\"58\",\"avg_item_3m\":\"19\",\"avg_item_6m\":\"10\"},\"app_point\":\"night_time\",\"app_point_zh\":\"夜晚(0:00-7:00)通话时长（秒）\"},{\"item\":{\"item_1m\":\"2\",\"item_3m\":\"2\",\"item_6m\":\"99\",\"avg_item_3m\":\"0.67\",\"avg_item_6m\":\"16.50\"},\"app_point\":\"local_cnt\",\"app_point_zh\":\"本机号码归属地通话次数\"},{\"item\":{\"item_1m\":\"92\",\"item_3m\":\"324\",\"item_6m\":\"560\",\"avg_item_3m\":\"108.00\",\"avg_item_6m\":\"93.33\"},\"app_point\":\"remote_cnt\",\"app_point_zh\":\"本机号码归属地以外通话次数\"},{\"item\":{\"item_1m\":\"12\",\"item_3m\":\"12\",\"item_6m\":\"4331\",\"avg_item_3m\":\"4\",\"avg_item_6m\":\"722\"},\"app_point\":\"local_time\",\"app_point_zh\":\"本机号码归属地通话时长（秒）\"},{\"item\":{\"item_1m\":\"6560\",\"item_3m\":\"31736\",\"item_6m\":\"51124\",\"avg_item_3m\":\"10579\",\"avg_item_6m\":\"8521\"},\"app_point\":\"remote_time\",\"app_point_zh\":\"本机号码归属地以外通话时长（秒）\"}],\"call_family_detail\":[{\"item\":{\"item_1m\":\"是\",\"item_3m\":\"是\",\"item_6m\":\"是\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"is_family_member\",\"app_point_zh\":\"是否为亲情网用户\"},{\"item\":{\"item_1m\":\"否\",\"item_3m\":\"否\",\"item_6m\":\"否\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"is_family_master\",\"app_point_zh\":\"是否为亲情网户主\"},{\"item\":{\"item_1m\":\"1\",\"item_3m\":\"3\",\"item_6m\":\"6\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"continue_recharge_month_cnt\",\"app_point_zh\":\"连续充值月数\"},{\"item\":{\"item_1m\":\"否\",\"item_3m\":\"否\",\"item_6m\":\"否\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"is_address_match_attribution\",\"app_point_zh\":\"常联系地址是否为手机归属地\"},{\"item\":{\"item_1m\":\"否\",\"item_3m\":\"否\",\"item_6m\":\"否\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"call_cnt_less\",\"app_point_zh\":\"通话次数 小于 使用月数＊1次\"},{\"item\":{\"item_1m\":\"否\",\"item_3m\":\"否\",\"item_6m\":\"否\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"call_cnt_more\",\"app_point_zh\":\"通话次数 大于 使用月数＊300次\"},{\"item\":{\"item_1m\":\"0\",\"item_3m\":\"0\",\"item_6m\":\"0\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"unpaid_month_cnt\",\"app_point_zh\":\"连续欠费月数\"},{\"item\":{\"item_1m\":\"1\",\"item_3m\":\"1\",\"item_6m\":\"3\",\"avg_item_3m\":null,\"avg_item_6m\":null},\"app_point\":\"live_month_cnt\",\"app_point_zh\":\"本机号码归属地使用月数\"}],\"call_duration_detail\":[{\"key\":\"call_duration_detail_3m\",\"desc\":\"通话时段（近三月）\",\"duration_list\":[{\"item\":{\"total_cnt\":9,\"uniq_num_cnt\":4,\"total_time\":769,\"dial_cnt\":3,\"dialed_cnt\":6,\"dial_time\":416,\"dialed_time\":353,\"latest_call_time\":\"20171007-074815\",\"farthest_call_time\":\"20170717-080443\"},\"time_step\":\"morning\",\"time_step_zh\":\"早晨[5:30-9:00]\"},{\"item\":{\"total_cnt\":45,\"uniq_num_cnt\":27,\"total_time\":6007,\"dial_cnt\":19,\"dialed_cnt\":26,\"dial_time\":2449,\"dialed_time\":3558,\"latest_call_time\":\"20171013-094156\",\"farthest_call_time\":\"20170716-093935\"},\"time_step\":\"forenoon\",\"time_step_zh\":\"上午[9:00-11:30]\"},{\"item\":{\"total_cnt\":62,\"uniq_num_cnt\":49,\"total_time\":4087,\"dial_cnt\":15,\"dialed_cnt\":47,\"dial_time\":1122,\"dialed_time\":2965,\"latest_call_time\":\"20171012-125349\",\"farthest_call_time\":\"20170718-123731\"},\"time_step\":\"noon\",\"time_step_zh\":\"中午[11:30-13:30]\"},{\"item\":{\"total_cnt\":52,\"uniq_num_cnt\":34,\"total_time\":10587,\"dial_cnt\":33,\"dialed_cnt\":19,\"dial_time\":5861,\"dialed_time\":4726,\"latest_call_time\":\"20171007-153228\",\"farthest_call_time\":\"20170716-150533\"},\"time_step\":\"afternoon\",\"time_step_zh\":\"下午[13:30-17:30]\"},{\"item\":{\"total_cnt\":62,\"uniq_num_cnt\":47,\"total_time\":3658,\"dial_cnt\":23,\"dialed_cnt\":39,\"dial_time\":1697,\"dialed_time\":1961,\"latest_call_time\":\"20171012-180027\",\"farthest_call_time\":\"20170717-183651\"},\"time_step\":\"dusk\",\"time_step_zh\":\"傍晚[17:30-19:30]\"},{\"item\":{\"total_cnt\":88,\"uniq_num_cnt\":53,\"total_time\":6490,\"dial_cnt\":40,\"dialed_cnt\":48,\"dial_time\":3449,\"dialed_time\":3041,\"latest_call_time\":\"20171012-221205\",\"farthest_call_time\":\"20170716-203358\"},\"time_step\":\"evening\",\"time_step_zh\":\"晚上[19:30-23:30]\"},{\"item\":{\"total_cnt\":8,\"uniq_num_cnt\":7,\"total_time\":150,\"dial_cnt\":3,\"dialed_cnt\":5,\"dial_time\":104,\"dialed_time\":46,\"latest_call_time\":\"20170930-000849\",\"farthest_call_time\":\"20170721-235611\"},\"time_step\":\"daybreak\",\"time_step_zh\":\"凌晨[23:30-1:30]\"},{\"item\":{\"total_cnt\":0,\"uniq_num_cnt\":0,\"total_time\":0,\"dial_cnt\":0,\"dialed_cnt\":0,\"dial_time\":0,\"dialed_time\":0,\"latest_call_time\":\"该时段无通话记录\",\"farthest_call_time\":\"该时段无通话记录\"},\"time_step\":\"midnight\",\"time_step_zh\":\"深夜[1:30-5:30]\"}]},{\"key\":\"call_duration_detail_6m\",\"desc\":\"通话时段（近六月）\",\"duration_list\":[{\"item\":{\"total_cnt\":17,\"uniq_num_cnt\":11,\"total_time\":1108,\"dial_cnt\":5,\"dialed_cnt\":12,\"dial_time\":624,\"dialed_time\":484,\"latest_call_time\":\"20171007-074815\",\"farthest_call_time\":\"20170418-084334\"},\"time_step\":\"morning\",\"time_step_zh\":\"早晨[5:30-9:00]\"},{\"item\":{\"total_cnt\":79,\"uniq_num_cnt\":51,\"total_time\":8345,\"dial_cnt\":32,\"dialed_cnt\":47,\"dial_time\":3902,\"dialed_time\":4443,\"latest_call_time\":\"20171013-094156\",\"farthest_call_time\":\"20170420-101850\"},\"time_step\":\"forenoon\",\"time_step_zh\":\"上午[9:00-11:30]\"},{\"item\":{\"total_cnt\":126,\"uniq_num_cnt\":97,\"total_time\":10054,\"dial_cnt\":34,\"dialed_cnt\":92,\"dial_time\":3238,\"dialed_time\":6816,\"latest_call_time\":\"20171012-125349\",\"farthest_call_time\":\"20170419-120846\"},\"time_step\":\"noon\",\"time_step_zh\":\"中午[11:30-13:30]\"},{\"item\":{\"total_cnt\":114,\"uniq_num_cnt\":78,\"total_time\":14273,\"dial_cnt\":49,\"dialed_cnt\":65,\"dial_time\":6999,\"dialed_time\":7274,\"latest_call_time\":\"20171007-153228\",\"farthest_call_time\":\"20170423-164502\"},\"time_step\":\"afternoon\",\"time_step_zh\":\"下午[13:30-17:30]\"},{\"item\":{\"total_cnt\":149,\"uniq_num_cnt\":114,\"total_time\":7307,\"dial_cnt\":47,\"dialed_cnt\":102,\"dial_time\":2952,\"dialed_time\":4355,\"latest_call_time\":\"20171012-180027\",\"farthest_call_time\":\"20170417-191601\"},\"time_step\":\"dusk\",\"time_step_zh\":\"傍晚[17:30-19:30]\"},{\"item\":{\"total_cnt\":165,\"uniq_num_cnt\":102,\"total_time\":13787,\"dial_cnt\":57,\"dialed_cnt\":108,\"dial_time\":7805,\"dialed_time\":5982,\"latest_call_time\":\"20171012-221205\",\"farthest_call_time\":\"20170417-203653\"},\"time_step\":\"evening\",\"time_step_zh\":\"晚上[19:30-23:30]\"},{\"item\":{\"total_cnt\":9,\"uniq_num_cnt\":7,\"total_time\":581,\"dial_cnt\":4,\"dialed_cnt\":5,\"dial_time\":535,\"dialed_time\":46,\"latest_call_time\":\"20170930-000849\",\"farthest_call_time\":\"20170714-235820\"},\"time_step\":\"daybreak\",\"time_step_zh\":\"凌晨[23:30-1:30]\"},{\"item\":{\"total_cnt\":0,\"uniq_num_cnt\":0,\"total_time\":0,\"dial_cnt\":0,\"dialed_cnt\":0,\"dial_time\":0,\"dialed_time\":0,\"latest_call_time\":\"该时段无通话记录\",\"farthest_call_time\":\"该时段无通话记录\"},\"time_step\":\"midnight\",\"time_step_zh\":\"深夜[1:30-5:30]\"}]}],\"roam_analysis\":[{\"roam_location\":\"杭州\",\"roam_day_cnt_3m\":78,\"roam_day_cnt_6m\":146,\"continue_roam_cnt_3m\":5,\"continue_roam_cnt_6m\":11,\"max_roam_day_cnt_3m\":34,\"max_roam_day_cnt_6m\":60},{\"roam_location\":\"温州\",\"roam_day_cnt_3m\":7,\"roam_day_cnt_6m\":12,\"continue_roam_cnt_3m\":1,\"continue_roam_cnt_6m\":2,\"max_roam_day_cnt_3m\":7,\"max_roam_day_cnt_6m\":7},{\"roam_location\":\"丽水\",\"roam_day_cnt_3m\":0,\"roam_day_cnt_6m\":2,\"continue_roam_cnt_3m\":0,\"continue_roam_cnt_6m\":1,\"max_roam_day_cnt_3m\":0,\"max_roam_day_cnt_6m\":2},{\"roam_location\":\"湖州\",\"roam_day_cnt_3m\":1,\"roam_day_cnt_6m\":1,\"continue_roam_cnt_3m\":0,\"continue_roam_cnt_6m\":0,\"max_roam_day_cnt_3m\":1,\"max_roam_day_cnt_6m\":1}],\"roam_detail\":[{\"roam_day\":\"2017-10-12\",\"roam_location\":\"杭州\"},{\"roam_day\":\"2017-10-6\",\"roam_location\":\"杭州\"}],\"collection_contact\":[{\"phone_num\":\"13777777777\",\"contact_name\":\"111\",\"relationship\":\"\",\"phone_num_loc\":\"\",\"call_cnt\":0,\"call_time\":0,\"dial_cnt\":0,\"dial_time\":0,\"dialed_cnt\":0,\"dialed_time\":0,\"trans_start\":\"\",\"trans_end\":\"\",\"sms_cnt\":0},{\"phone_num\":\"138888888\",\"contact_name\":\"111\",\"relationship\":\"\",\"phone_num_loc\":\"\",\"call_cnt\":0,\"call_time\":0,\"dial_cnt\":0,\"dial_time\":0,\"dialed_cnt\":0,\"dialed_time\":0,\"trans_start\":\"\",\"trans_end\":\"\",\"sms_cnt\":0}],\"user_info_check\":[{\"check_search_info\":{\"searched_org_cnt\":0,\"searched_org_type\":[],\"idcard_with_other_names\":[\"李子\",\"王五\"],\"idcard_with_other_phones\":[\"13882288228\",\"13992299229\"],\"phone_with_other_names\":[\"王五\",\"鲁华\"],\"phone_with_other_idcards\":[\"610424199303060488\"],\"register_org_cnt\":0,\"register_org_type\":[],\"arised_open_web\":[]},\"check_black_info\":{\"phone_gray_score\":61,\"contacts_class1_blacklist_cnt\":0,\"contacts_class2_blacklist_cnt\":21,\"contacts_class1_cnt\":381,\"contacts_router_cnt\":9,\"contacts_router_ratio\":0.02}}],\"trip_info\":[{\"trip_dest\":\"温州\",\"trip_start_time\":\"2017-04-28 11:08:18\",\"trip_end_time\":\"2017-04-28 21:36:10\",\"trip_leave\":\"杭州\",\"trip_type\":\"工作日\"},{\"trip_dest\":\"丽水\",\"trip_start_time\":\"2017-04-28 21:36:10\",\"trip_end_time\":\"2017-04-30 15:50:12\",\"trip_leave\":\"温州\",\"trip_type\":\"节假日\"},{\"trip_dest\":\"杭州\",\"trip_start_time\":\"2017-10-07 15:32:28\",\"trip_end_time\":\"2017-10-12 22:12:05\",\"trip_leave\":\"温州\",\"trip_type\":\"节假日\"}]}";
		parseJson2Java(json);
	}

	/**
	 * 将json字符串转换为对应的javabean
	 * <p>
	 * <p>
	 * 用法:<br>
	 * 将json字符串拷贝至本项目中/Json/JsonString.txt 文件中去,然后调用该方法,<br>
	 * 就会在本项目中/Json/JsonBean.java中生成一个对应的JavaBean类<br>
	 * <br>
	 * 注意:<br>
	 * 如果json字符串中有null或者空集合[]这种无法判断类型的,会统一使用Object类型
	 * @throws IOException 
	 */
	public static void parseJson2Java(String jsonStr) throws IOException {
		// 解析获取整个json结构集合
		List<Json2JavaElement> jsonBeanTree = getJsonBeanTree(jsonStr);

		// 利用获取到的json结构集合,创建对应的javabean文件内容
		String javaBeanStr = createJavaBean(jsonBeanTree);

		// 将生成的内容写入到文件中去
		File outFile = new File("e:" + File.separator + "Json" + File.separator + "JsonBean.java");
		if(!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdir();
		}
		FileWriter fw = new FileWriter(outFile);
		IOUtils.write(javaBeanStr, fw);
	}

	/**
	 * 将json字符串转换为对应的javabean
	 *
	 * @return 生成的javabean代码
	 */
	public static String getJavaFromJson(String jsonStr) {
		// 解析获取整个json结构集合
		List<Json2JavaElement> jsonBeanTree = getJsonBeanTree(jsonStr);

		// 利用获取到的json结构集合,创建对应的javabean文件内容
		String javaBeanStr = createJavaBean(jsonBeanTree);

		return javaBeanStr;
	}

	/**
	 * 根据解析好的数据创建生成对应的javabean类字符串
	 *
	 * @param jsonBeanTree 解析好的数据集合
	 * @return 生成的javabean类字符串
	 */
	public static String createJavaBean(List<Json2JavaElement> jsonBeanTree) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbGetterAndSetter = new StringBuilder();
		sb.append("\n");

		// 是否包含自定义子类
		boolean hasCustomeClass = false;
		List<String> customClassNames = new ArrayList<String>();

		// 由于在循环的时候有移除操作,所以使用迭代器遍历
		Iterator<Json2JavaElement> iterator = jsonBeanTree.iterator();
		while (iterator.hasNext()) {
			Json2JavaElement j2j = iterator.next();

			// 保存自定义类名称至集合中,注意已经包含的不再添加
			if (j2j.getCustomClassName() != null && !customClassNames.contains(j2j.getCustomClassName())) {
				customClassNames.add(j2j.getCustomClassName());
			}

			if (j2j.getParentJb() != null) {
				// 如果有parent,则为自定义子类,设置标识符不做其他操作
				hasCustomeClass = true;
			}
			else {
				// 如果不是自定义子类,则根据类型名和控件对象名生成变量申明语句
				genFieldd(sb, sbGetterAndSetter, j2j, 0);

				// 已经使用的数据会移除,则集合中只会剩下自定义子类相关的元素数据,将在后续的循环中处理
				iterator.remove();
			}
		}

		// 设置所有自定义类
		if (hasCustomeClass) {
			for (String customClassName : customClassNames) {
				// 根据名称申明子类

				// public class CustomClass {
				sb.append("\n");
				sb.append(StringUtils.formatSingleLine(1, "public class " + customClassName + " {"));

				StringBuilder sbSubGetterAndSetter = new StringBuilder();
				// 循环余下的集合
				Iterator<Json2JavaElement> customIterator = jsonBeanTree.iterator();
				while (customIterator.hasNext()) {
					Json2JavaElement j2j = customIterator.next();

					// 根据当前数据的parent名称,首字母转为大写生成parent的类名
					String parentClassName = StringUtils.firstToUpperCase(j2j.getParentJb().getName());

					// 如果当前数据属于本次外层循环需要处理的子类
					if (parentClassName.equals(customClassName)) {
						// 根据类型名和控件对象名生成变量申明语句
						genFieldd(sb, sbSubGetterAndSetter, j2j, 1);

						// 已经使用的数据会移除,减少下一次外层循环的遍历次数
						customIterator.remove();
					}
				}

				sb.append(sbSubGetterAndSetter.toString());
				sb.append(StringUtils.formatSingleLine(1, "}"));
			}
		}

		sb.append(sbGetterAndSetter.toString());
		sb.append("\n");
		return sb.toString();
	}

	/**
	 * 生成变量相关代码
	 *
	 * @param sb 添加申明变量部分
	 * @param sbGetterAndSetter 添加getter和setter方法部分
	 * @param j2j 变量信息
	 * @param extraTabNum 额外缩进量\t
	 */
	private static void genFieldd(StringBuilder sb, StringBuilder sbGetterAndSetter, Json2JavaElement j2j,
			int extraTabNum) {
		// 先判断是否有注释,有的话添加之
		// /**
		// * 姓名
		// */
		String des = j2j.getDes();
		if (des != null && des.length() > 0) {
			sb.append(StringUtils.formatSingleLine(1 + extraTabNum, "/**"));
			sb.append(StringUtils.formatSingleLine(1 + extraTabNum, " * " + des));
			sb.append(StringUtils.formatSingleLine(1 + extraTabNum, " */"));
		}

		// 申明变量
		// private String name;
		sb.append(StringUtils.formatSingleLine(1 + extraTabNum,
				"private " + getTypeName(j2j) + " " + j2j.getName() + ";"));

		// 生成变量对应的getter和setter方法
		// public String getName() {
		// return name;
		// }
		sbGetterAndSetter.append("\n");
		sbGetterAndSetter.append(StringUtils.formatSingleLine(1 + extraTabNum,
				"public " + getTypeName(j2j) + " get" + StringUtils.firstToUpperCase(j2j.getName()) + "() {"));
		sbGetterAndSetter.append(StringUtils.formatSingleLine(2 + extraTabNum, "return " + j2j.getName() + ";"));
		sbGetterAndSetter.append(StringUtils.formatSingleLine(1 + extraTabNum, "}"));

		// public void setName(String name) {
		// this.name = name;
		// }
		sbGetterAndSetter.append("\n");
		sbGetterAndSetter.append(StringUtils.formatSingleLine(1 + extraTabNum, "public void set"
				+ StringUtils.firstToUpperCase(j2j.getName()) + "(" + getTypeName(j2j) + " " + j2j.getName() + ") {"));
		sbGetterAndSetter.append(
				StringUtils.formatSingleLine(2 + extraTabNum, "this." + j2j.getName() + " = " + j2j.getName() + ";"));
		sbGetterAndSetter.append(StringUtils.formatSingleLine(1 + extraTabNum, "}"));
	}

	/**
	 * 递归遍历整个json数据结构,保存至jsonBeans集合中
	 *
	 * @param jsonStr json字符串
	 * @return 解析好的数据集合
	 */
	public static List<Json2JavaElement> getJsonBeanTree(String jsonStr) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jsonStr);

		// 根element可能是对象也可能是数组
		JsonObject rootJo = null;
		if (element.isJsonObject()) {
			rootJo = element.getAsJsonObject();
		}
		else if (element.isJsonArray()) {
			// 集合中如果有数据,则取第一个解析
			JsonArray jsonArray = element.getAsJsonArray();
			if (jsonArray.size() > 0) {
				rootJo = jsonArray.get(0).getAsJsonObject();
			}
		}

		jsonBeans = new ArrayList<Json2JavaElement>();
		recursionJson(rootJo, null);
		return jsonBeans;
	}

	/**
	 * 保存递归获取到数据的集合
	 */
	private static List<Json2JavaElement> jsonBeans = new ArrayList<Json2JavaElement>();

	/**
	 * 递归获取json数据
	 *
	 * @param jo 当前递归解析的json对象
	 * @param parent 已经解析好的上一级数据,无上一级时传入null
	 */
	private static void recursionJson(JsonObject jo, Json2JavaElement parent) {
		if (jo == null) {
			return;
		}

		// 循环整个json对象的键值对
		for (Entry<String, JsonElement> entry : jo.entrySet()) {
			// json对象的键值对建构为 {"key":value}
			// 其中,值可能是基础类型,也可能是集合或者对象,先解析为json元素
			String name = entry.getKey();
			JsonElement je = entry.getValue();

			Json2JavaElement j2j = new Json2JavaElement();
			j2j.setName(name);
			if (parent != null) {
				j2j.setParentJb(parent);
			}

			// 获取json元素的类型,可能为多种情况,如下
			Class<?> type = getJsonType(je);
			if (type == null) {
				// 自定义类型

				// json键值的首字母转为大写,作为自定义类名
				j2j.setCustomClassName(StringUtils.firstToUpperCase(name));
				// ?
				j2j.setSouceJo(je.getAsJsonObject());
				jsonBeans.add(j2j);

				// 自定义类需要继续递归,解析自定义类中的json结构
				recursionJson(je.getAsJsonObject(), j2j);
			}
			else if (type.equals(JsonArray.class)) {
				// 集合类型

				// 重置集合数据,并获取当前json元素的集合类型信息
				deepLevel = 0;
				arrayType = new ArrayType();
				getJsonArrayType(je.getAsJsonArray());

				j2j.setArray(true);
				j2j.setArrayDeep(deepLevel);

				if (arrayType.getJo() != null) {
					j2j.setCustomClassName(StringUtils.firstToUpperCase(name));
					// 集合内的末点元素类型为自定义类, 递归
					recursionJson(arrayType.getJo(), j2j);
				}
				else {
					j2j.setType(arrayType.getType());
				}
				jsonBeans.add(j2j);
			}
			else {
				// 其他情况,一般都是String,int等基础数据类型

				j2j.setType(type);
				jsonBeans.add(j2j);
			}
		}
	}

	/**
	 * 集合深度,如果是3则为ArrayList<ArrayList<ArrayList<>>>
	 */
	private static int deepLevel = 0;
	/**
	 * 集合类型数据,用于保存递归获取到的集合信息
	 */
	private static ArrayType arrayType = new ArrayType();

	/**
	 * 递归获取集合的深度和类型等信息
	 *
	 * @param jsonArray json集合数据
	 */
	private static void getJsonArrayType(JsonArray jsonArray) {
		// 每次递归,集合深度+1
		deepLevel++;

		if (jsonArray.size() == 0) {
			// 如果集合为空,则集合内元素类型无法判断,直接设为Object
			arrayType.setArrayDeep(deepLevel);
			arrayType.setType(Object.class);
		}
		else {
			// 如果集合非空则取出第一个元素进行判断
			JsonElement childJe = jsonArray.get(0);

			// 获取json元素的类型
			Class<?> type = getJsonType(childJe);

			if (type == null) {
				// 自定义类型

				// 设置整个json对象,用于后续进行进一步解析处理
				arrayType.setJo(childJe.getAsJsonObject());
				arrayType.setArrayDeep(deepLevel);
			}
			else if (type.equals(JsonArray.class)) {
				// 集合类型

				// 如果集合里面还是集合,则递归本方法
				getJsonArrayType(childJe.getAsJsonArray());
			}
			else {
				// 其他情况,一般都是String,int等基础数据类型

				arrayType.setArrayDeep(deepLevel);
				arrayType.setType(type);
			}
		}
	}

	/**
	 * 获取json元素的类型
	 *
	 * @param je json元素
	 * @return 类型
	 */
	private static Class<?> getJsonType(JsonElement je) {
		Class<?> clazz = null;

		if (je.isJsonNull()) {
			// 数据为null时,无法获取类型,则视为object类型
			clazz = Object.class;
		}
		else if (je.isJsonPrimitive()) {
			// primitive类型为基础数据类型,如String,int等
			clazz = getJsonPrimitiveType(je);
		}
		else if (je.isJsonObject()) {
			// 自定义类型参数则返回null,让json的解析递归进行进一步处理
			clazz = null;
		}
		else if (je.isJsonArray()) {
			// json集合类型
			clazz = JsonArray.class;
		}
		return clazz;
	}

	/**
	 * 将json元素中的json基础类型,转换为String.class,int.class等具体的类型
	 *
	 * @param je json元素
	 * @return 具体数据类型, 无法预估的类型统一视为Object.class类型
	 */
	private static Class<?> getJsonPrimitiveType(JsonElement je) {
		Class<?> clazz = Object.class;
		JsonPrimitive jp = je.getAsJsonPrimitive();
		// json中的类型会将数字集合成一个总的number类型,需要分别判断
		if (jp.isNumber()) {
			String num = jp.getAsString();
			if (num.contains(".")) {
				// 如果包含"."则为小数,先尝试解析成float,如果失败则视为double
				try {
					Float.parseFloat(num);
					clazz = float.class;
				}
				catch (NumberFormatException e) {
					clazz = double.class;
				}
			}
			else {
				// 如果不包含"."则为整数,先尝试解析成int,如果失败则视为long
				try {
					Integer.parseInt(num);
					clazz = int.class;
				}
				catch (NumberFormatException e) {
					clazz = long.class;
				}
			}
		}
		else if (jp.isBoolean()) {
			clazz = boolean.class;
		}
		else if (jp.isString()) {
			clazz = String.class;
		}
		// json中没有其他具体类型如byte等
		return clazz;
	}

	/**
	 * 获取类型名称字符串
	 *
	 * @param j2j 转换数据元素
	 * @return 类型名称, 无法获取时, 默认Object
	 */
	private static String getTypeName(Json2JavaElement j2j) {
		String name = "Object";

		Class<?> type = j2j.getType();
		if (j2j.getCustomClassName() != null && j2j.getCustomClassName().length() > 0) {
			// 自定义类,直接用自定义的名称customClassName
			name = j2j.getCustomClassName();
		}
		else {
			// 非自定义类即可以获取类型,解析类型class的名称,如String.class就对应String
			name = type.getName();
			int lastIndexOf = name.lastIndexOf(".");
			if (lastIndexOf != -1) {
				name = name.substring(lastIndexOf + 1);
			}
		}

		// 如果集合深度大于0,则为集合数据,根据深度进行ArrayList嵌套
		// 深度为3就是ArrayList<ArrayList<ArrayList<type>>>
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < j2j.getArrayDeep(); i++) {
			sb.append("List<");
		}
		sb.append(name);
		for (int i = 0; i < j2j.getArrayDeep(); i++) {
			sb.append(">");
		}
		return sb.toString();
	}
}