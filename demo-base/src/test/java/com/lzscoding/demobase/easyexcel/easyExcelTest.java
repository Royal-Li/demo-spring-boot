package com.lzscoding.demobase.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class easyExcelTest {

    @Test
    public void test1() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url("https://ad.e.kuaishou.com/rest/openapi/v1/region/list").method("GET", null).addHeader("Access-Token", "bc04aece5591f4d8df9d3fe711e29e67").build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONObject responseJsonObject = JSONObject.parseObject(responseString);

        Map<String, KuaiShouArea> data = JSONObject.parseObject(responseJsonObject.get("data").toString(), new TypeReference<Map<String, KuaiShouArea>>() {
        });

        List<KuaiShouArea> list = new ArrayList<>();
        Set<Map.Entry<String, KuaiShouArea>> entry = data.entrySet();
        for (Map.Entry<String, KuaiShouArea> e : entry) {
            list.add(e.getValue());
        }
        list.stream().map(x -> {
            Integer level = x.getLevel();
            if (level == 1) {
                x.setLevelName("省");
            } else if (level == 2) {
                x.setLevelName("市");
            } else if (level == 3) {
                x.setLevelName("区");
            } else {
                x.setLevelName("未知");
            }

            KuaiShouArea kuaiShouArea = data.get(x.getParent().toString());
            if (kuaiShouArea != null) {
                x.setParentName(kuaiShouArea.getName());
            }
            return x;
        }).collect(Collectors.toList());


        System.out.println(list);

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write("/Users/a58/Desktop/kuaishou.xlsx", KuaiShouArea.class).sheet("模板").doWrite(list);

    }


    /**
     * B嵌套在A里，那么我们要声明内嵌类static属性
     */
    @Data
    static class KuaiShouArea {
        @ExcelProperty("编码")
        public Integer id;
        @ExcelProperty("名称")
        public String name;
        @ExcelProperty("层级")
        public Integer level;
        @ExcelProperty("层级名称")
        public String levelName;
        @ExcelProperty("上级编码")
        public Integer parent;
        @ExcelProperty("上级名称")
        public String parentName;
//        public List<Integer> children;
//        public String fullName;
    }

    public static void main(String[] args) {
        String str1 = "\"\"";
        String str2 = "\"0\"";

        System.out.println(str1.substring(1,str1.length()-1));
        System.out.println(str2.substring(1,str2.length()-1));
    }
}
