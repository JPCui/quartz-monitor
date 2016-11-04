package cn.cjp.weather.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cn.cjp.util.Collections;

public class CityCodes {

	public static String get(String key) {
		return map.get(key);
	}
	
	public static void main(String[] args) {
		System.out.println(searchCitys("北京"));
	}
	
	public static Collection<String> searchCitys(String city) {
		return Collections.searchSub(CityCodes.map.keySet(), city + ".*");
	}

	public static final Map<String, String> map = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("北京", "101010100");
			put("朝阳", "101010300");
			put("顺义", "101010400");
			put("怀柔", "101010500");
			put("通州", "101010600");
			put("昌平", "101010700");
			put("延庆", "101010800");
			put("丰台", "101010900");
			put("石景山", "101011000");
			put("大兴", "101011100");
			put("房山", "101011200");
			put("密云", "101011300");
			put("门头沟", "101011400");
			put("平谷", "101011500");
			put("八达岭", "101011600");
			put("佛爷顶", "101011700");
			put("汤河口", "101011800");
			put("密云上甸子", "101011900");
			put("斋堂", "101012000");
			put("霞云岭", "101012100");
			put("北京城区", "101012200");
			put("海淀", "101010200");
			put("天津", "101030100");
			put("宝坻", "101030300");
			put("东丽", "101030400");
			put("西青", "101030500");
			put("北辰", "101030600");
			put("蓟县", "101031400");
			put("汉沽", "101030800");
			put("静海", "101030900");
			put("津南", "101031000");
			put("塘沽", "101031100");
			put("大港", "101031200");
			put("武清", "101030200");
			put("宁河", "101030700");
			put("上海", "101020100");
			put("宝山", "101020300");
			put("嘉定", "101020500");
			put("南汇", "101020600");
			put("浦东", "101021300");
			put("青浦", "101020800");
			put("松江", "101020900");
			put("奉贤", "101021000");
			put("崇明", "101021100");
			put("徐家汇", "101021200");
			put("闵行", "101020200");
			put("金山", "101020700");
			put("石家庄", "101090101");
			put("张家口", "101090301");
			put("承德", "101090402");
			put("唐山", "101090501");
			put("秦皇岛", "101091101");
			put("沧州", "101090701");
			put("衡水", "101090801");
			put("邢台", "101090901");
			put("邯郸", "101091001");
			put("保定", "101090201");
			put("廊坊", "101090601");
			put("郑州", "101180101");
			put("新乡", "101180301");
			put("许昌", "101180401");
			put("平顶山", "101180501");
			put("信阳", "101180601");
			put("南阳", "101180701");
			put("开封", "101180801");
			put("洛阳", "101180901");
			put("商丘", "101181001");
			put("焦作", "101181101");
			put("鹤壁", "101181201");
			put("濮阳", "101181301");
			put("周口", "101181401");
			put("漯河", "101181501");
			put("驻马店", "101181601");
			put("三门峡", "101181701");
			put("济源", "101181801");
			put("安阳", "101180201");
			put("合肥", "101220101");
			put("芜湖", "101220301");
			put("淮南", "101220401");
			put("马鞍山", "101220501");
			put("安庆", "101220601");
			put("宿州", "101220701");
			put("阜阳", "101220801");
			put("亳州", "101220901");
			put("黄山", "101221001");
			put("滁州", "101221101");
			put("淮北", "101221201");
			put("铜陵", "101221301");
			put("宣城", "101221401");
			put("六安", "101221501");
			put("巢湖", "101221601");
			put("池州", "101221701");
			put("蚌埠", "101220201");
			put("杭州", "101210101");
			put("舟山", "101211101");
			put("湖州", "101210201");
			put("嘉兴", "101210301");
			put("金华", "101210901");
			put("绍兴", "101210501");
			put("台州", "101210601");
			put("温州", "101210701");
			put("丽水", "101210801");
			put("衢州", "101211001");
			put("宁波", "101210401");
			put("重庆", "101040100");
			put("合川", "101040300");
			put("南川", "101040400");
			put("江津", "101040500");
			put("万盛", "101040600");
			put("渝北", "101040700");
			put("北碚", "101040800");
			put("巴南", "101040900");
			put("长寿", "101041000");
			put("黔江", "101041100");
			put("万州天城", "101041200");
			put("万州龙宝", "101041300");
			put("涪陵", "101041400");
			put("开县", "101041500");
			put("城口", "101041600");
			put("云阳", "101041700");
			put("巫溪", "101041800");
			put("奉节", "101041900");
			put("巫山", "101042000");
			put("潼南", "101042100");
			put("垫江", "101042200");
			put("梁平", "101042300");
			put("忠县", "101042400");
			put("石柱", "101042500");
			put("大足", "101042600");
			put("荣昌", "101042700");
			put("铜梁", "101042800");
			put("璧山", "101042900");
			put("丰都", "101043000");
			put("武隆", "101043100");
			put("彭水", "101043200");
			put("綦江", "101043300");
			put("酉阳", "101043400");
			put("秀山", "101043600");
			put("沙坪坝", "101043700");
			put("永川", "101040200");
			put("福州", "101230101");
			put("泉州", "101230501");
			put("漳州", "101230601");
			put("龙岩", "101230701");
			put("晋江", "101230509");
			put("南平", "101230901");
			put("厦门", "101230201");
			put("宁德", "101230301");
			put("莆田", "101230401");
			put("三明", "101230801");
			put("兰州", "101160101");
			put("平凉", "101160301");
			put("庆阳", "101160401");
			put("武威", "101160501");
			put("金昌", "101160601");
			put("嘉峪关", "101161401");
			put("酒泉", "101160801");
			put("天水", "101160901");
			put("武都", "101161001");
			put("临夏", "101161101");
			put("合作", "101161201");
			put("白银", "101161301");
			put("定西", "101160201");
			put("张掖", "101160701");
			put("广州", "101280101");
			put("惠州", "101280301");
			put("梅州", "101280401");
			put("汕头", "101280501");
			put("深圳", "101280601");
			put("珠海", "101280701");
			put("佛山", "101280800");
			put("肇庆", "101280901");
			put("湛江", "101281001");
			put("江门", "101281101");
			put("河源", "101281201");
			put("清远", "101281301");
			put("云浮", "101281401");
			put("潮州", "101281501");
			put("东莞", "101281601");
			put("中山", "101281701");
			put("阳江", "101281801");
			put("揭阳", "101281901");
			put("茂名", "101282001");
			put("汕尾", "101282101");
			put("韶关", "101280201");
			put("南宁", "101300101");
			put("柳州", "101300301");
			put("来宾", "101300401");
			put("桂林", "101300501");
			put("梧州", "101300601");
			put("防城港", "101301401");
			put("贵港", "101300801");
			put("玉林", "101300901");
			put("百色", "101301001");
			put("钦州", "101301101");
			put("河池", "101301201");
			put("北海", "101301301");
			put("崇左", "101300201");
			put("贺州", "101300701");
			put("贵阳", "101260101");
			put("安顺", "101260301");
			put("都匀", "101260401");
			put("兴义", "101260906");
			put("铜仁", "101260601");
			put("毕节", "101260701");
			put("六盘水", "101260801");
			put("遵义", "101260201");
			put("凯里", "101260501");
			put("昆明", "101290101");
			put("红河", "101290301");
			put("文山", "101290601");
			put("玉溪", "101290701");
			put("楚雄", "101290801");
			put("普洱", "101290901");
			put("昭通", "101291001");
			put("临沧", "101291101");
			put("怒江", "101291201");
			put("香格里拉", "101291301");
			put("丽江", "101291401");
			put("德宏", "101291501");
			put("景洪", "101291601");
			put("大理", "101290201");
			put("曲靖", "101290401");
			put("保山", "101290501");
			put("呼和浩特", "101080101");
			put("乌海", "101080301");
			put("集宁", "101080401");
			put("通辽", "101080501");
			put("阿拉善左旗", "101081201");
			put("鄂尔多斯", "101080701");
			put("临河", "101080801");
			put("锡林浩特", "101080901");
			put("呼伦贝尔", "101081000");
			put("乌兰浩特", "101081101");
			put("包头", "101080201");
			put("赤峰", "101080601");
			put("南昌", "101240101");
			put("上饶", "101240301");
			put("抚州", "101240401");
			put("宜春", "101240501");
			put("鹰潭", "101241101");
			put("赣州", "101240701");
			put("景德镇", "101240801");
			put("萍乡", "101240901");
			put("新余", "101241001");
			put("九江", "101240201");
			put("吉安", "101240601");
			put("武汉", "101200101");
			put("黄冈", "101200501");
			put("荆州", "101200801");
			put("宜昌", "101200901");
			put("恩施", "101201001");
			put("十堰", "101201101");
			put("神农架", "101201201");
			put("随州", "101201301");
			put("荆门", "101201401");
			put("天门", "101201501");
			put("仙桃", "101201601");
			put("潜江", "101201701");
			put("襄樊", "101200201");
			put("鄂州", "101200301");
			put("孝感", "101200401");
			put("黄石", "101200601");
			put("咸宁", "101200701");
			put("成都", "101270101");
			put("自贡", "101270301");
			put("绵阳", "101270401");
			put("南充", "101270501");
			put("达州", "101270601");
			put("遂宁", "101270701");
			put("广安", "101270801");
			put("巴中", "101270901");
			put("泸州", "101271001");
			put("宜宾", "101271101");
			put("内江", "101271201");
			put("资阳", "101271301");
			put("乐山", "101271401");
			put("眉山", "101271501");
			put("凉山", "101271601");
			put("雅安", "101271701");
			put("甘孜", "101271801");
			put("阿坝", "101271901");
			put("德阳", "101272001");
			put("广元", "101272101");
			put("攀枝花", "101270201");
			put("银川", "101170101");
			put("中卫", "101170501");
			put("固原", "101170401");
			put("石嘴山", "101170201");
			put("吴忠", "101170301");
			put("西宁", "101150101");
			put("黄南", "101150301");
			put("海北", "101150801");
			put("果洛", "101150501");
			put("玉树", "101150601");
			put("海西", "101150701");
			put("海东", "101150201");
			put("海南", "101150401");
			put("济南", "101120101");
			put("潍坊", "101120601");
			put("临沂", "101120901");
			put("菏泽", "101121001");
			put("滨州", "101121101");
			put("东营", "101121201");
			put("威海", "101121301");
			put("枣庄", "101121401");
			put("日照", "101121501");
			put("莱芜", "101121601");
			put("聊城", "101121701");
			put("青岛", "101120201");
			put("淄博", "101120301");
			put("德州", "101120401");
			put("烟台", "101120501");
			put("济宁", "101120701");
			put("泰安", "101120801");
			put("西安", "101110101");
			put("延安", "101110300");
			put("榆林", "101110401");
			put("铜川", "101111001");
			put("商洛", "101110601");
			put("安康", "101110701");
			put("汉中", "101110801");
			put("宝鸡", "101110901");
			put("咸阳", "101110200");
			put("渭南", "101110501");
			put("太原", "101100101");
			put("临汾", "101100701");
			put("运城", "101100801");
			put("朔州", "101100901");
			put("忻州", "101101001");
			put("长治", "101100501");
			put("大同", "101100201");
			put("阳泉", "101100301");
			put("晋中", "101100401");
			put("晋城", "101100601");
			put("吕梁", "101101100");
			put("乌鲁木齐", "101130101");
			put("石河子", "101130301");
			put("昌吉", "101130401");
			put("吐鲁番", "101130501");
			put("库尔勒", "101130601");
			put("阿拉尔", "101130701");
			put("阿克苏", "101130801");
			put("喀什", "101130901");
			put("伊宁", "101131001");
			put("塔城", "101131101");
			put("哈密", "101131201");
			put("和田", "101131301");
			put("阿勒泰", "101131401");
			put("阿图什", "101131501");
			put("博乐", "101131601");
			put("克拉玛依", "101130201");
			put("拉萨", "101140101");
			put("山南", "101140301");
			put("阿里", "101140701");
			put("昌都", "101140501");
			put("那曲", "101140601");
			put("日喀则", "101140201");
			put("林芝", "101140401");
			put("台北县", "101340101");
			put("高雄", "101340201");
			put("台中", "101340401");
			put("海口", "101310101");
			put("三亚", "101310201");
			put("东方", "101310202");
			put("临高", "101310203");
			put("澄迈", "101310204");
			put("儋州", "101310205");
			put("昌江", "101310206");
			put("白沙", "101310207");
			put("琼中", "101310208");
			put("定安", "101310209");
			put("屯昌", "101310210");
			put("琼海", "101310211");
			put("文昌", "101310212");
			put("保亭", "101310214");
			put("万宁", "101310215");
			put("陵水", "101310216");
			put("西沙", "101310217");
			put("南沙岛", "101310220");
			put("乐东", "101310221");
			put("五指山", "101310222");
			put("琼山", "101310102");
			put("长沙", "101250101");
			put("株洲", "101250301");
			put("衡阳", "101250401");
			put("郴州", "101250501");
			put("常德", "101250601");
			put("益阳", "101250700");
			put("娄底", "101250801");
			put("邵阳", "101250901");
			put("岳阳", "101251001");
			put("张家界", "101251101");
			put("怀化", "101251201");
			put("黔阳", "101251301");
			put("永州", "101251401");
			put("吉首", "101251501");
			put("湘潭", "101250201");
			put("南京", "101190101");
			put("镇江", "101190301");
			put("苏州", "101190401");
			put("南通", "101190501");
			put("扬州", "101190601");
			put("宿迁", "101191301");
			put("徐州", "101190801");
			put("淮安", "101190901");
			put("连云港", "101191001");
			put("常州", "101191101");
			put("泰州", "101191201");
			put("无锡", "101190201");
			put("盐城", "101190701");
			put("哈尔滨", "101050101");
			put("牡丹江", "101050301");
			put("佳木斯", "101050401");
			put("绥化", "101050501");
			put("黑河", "101050601");
			put("双鸭山", "101051301");
			put("伊春", "101050801");
			put("大庆", "101050901");
			put("七台河", "101051002");
			put("鸡西", "101051101");
			put("鹤岗", "101051201");
			put("齐齐哈尔", "101050201");
			put("大兴安岭", "101050701");
			put("长春", "101060101");
			put("延吉", "101060301");
			put("四平", "101060401");
			put("白山", "101060901");
			put("白城", "101060601");
			put("辽源", "101060701");
			put("松原", "101060801");
			put("吉林", "101060201");
			put("通化", "101060501");
			put("沈阳", "101070101");
			put("鞍山", "101070301");
			put("抚顺", "101070401");
			put("本溪", "101070501");
			put("丹东", "101070601");
			put("葫芦岛", "101071401");
			put("营口", "101070801");
			put("阜新", "101070901");
			put("辽阳", "101071001");
			put("铁岭", "101071101");
			put("朝阳", "101071201");
			put("盘锦", "101071301");
			put("大连", "101070201");
			put("锦州", "101070701");
		}
	};

}
