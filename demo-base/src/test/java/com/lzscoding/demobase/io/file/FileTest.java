package com.lzscoding.demobase.io.file;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileTest {

    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        String fileName1 = "/Users/a58/Desktop/20220522-baiduocpc-keywordid.txt";
        String fileName2 = "/Users/a58/Desktop/20220522-success-bdvid-sort.txt";
        //获取要读取的文件
        File readFile = new File(fileName1);
        //输入IO流声明
        InputStream in = null;
        InputStreamReader ir = null;
        BufferedReader br = null;
        try {
            //用流读取文件
            in = new BufferedInputStream(new FileInputStream(readFile));
            //如果你文件已utf-8编码的就按这个编码来读取，不然又中文会读取到乱码
            ir = new InputStreamReader(in, "utf-8");
            //字符输入流中读取文本,这样可以一行一行读取
            br = new BufferedReader(ir);
            String line = "";
            //一行一行读取
            while ((line = br.readLine()) != null) {
//                if (line.length() < 17) {
//                    System.out.println("长度：" + line.length() + " ---- " + line);
                if (map.get(line) == null) {
                    map.put(line, 1);
                } else {
                    map.put(line, map.get(line) + 1);
                }
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一定要关闭流,倒序关闭
            try {
                if (br != null) {
                    br.close();
                }
                if (ir != null) {
                    ir.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {

            }
        }

        map.entrySet().forEach(i -> {
            System.out.println("Id：" + i.getKey() + " -- 个数：" + i.getValue());
        });
        System.out.println("大小：" + map.size());
        map.entrySet().forEach(i -> {
            System.out.print(i.getKey() + ",");
        });


//        map.entrySet().forEach(i -> {
//            Pattern pattern = Pattern.compile("[0-9]*");
//            if (pattern.matcher(i.getKey()).matches()) {
//                BigDecimal bigDecimal = new BigDecimal(i.getKey());//good
//                BigDecimal[] bigDecimals = bigDecimal.divideAndRemainder(BigDecimal.valueOf(128));
//                System.out.println("SELECT * FROM materiel_creative_" + bigDecimals[1] + " where platform_creative_id =" + i.getKey());
//            } else {
//                System.out.println(i.getKey());
//            }
//        });
//        List<String> list = Arrays.asList("57373631317", "56831425880", "57399509468", "57399506142", "56831425886", "57399508191", "57373627389", "57373631342", "56831426628", "56831425826", "53657230370", "57395169306", "56831426714", "57395169321", "44524660765", "57395169305", "57399508682", "54708231787", "57399508916", "57373630900", "57373630898", "57399508914", "56831425872", "53662457808", "56831427006", "57395169328", "57399508912", "57373629518", "56831427022", "44524660686", "57395169334", "57399507510", "57373627051", "57395169329", "56831427078", "57395169319", "42664956005", "44524660674", "56831426951", "57395169304", "56831426680", "48284797951", "56831427034", "56831428698", "56831425882", "57373627043", "55111413150", "57373626994", "57373630903", "57395169307", "56831428654", "56831426682", "57373627023", "57373631318", "56831427798", "56831426792", "54708231784", "57395169331", "57373630445", "54708231789", "56831425828", "53657230372", "56831427596", "36265282060", "56473868473", "56831426981", "57399505829");
//        AtomicInteger sumocpc = new AtomicInteger();
//        System.out.println("-----------------");
//        map.entrySet().forEach(i -> {
//            if (list.contains(i.getKey())) {
//                sumocpc.getAndAdd(i.getValue());
//                System.out.print(i.getValue() + ",");
//            }
//        });
//        System.out.println("-----------------");
//        System.out.println(sumocpc.get());


    }
}
