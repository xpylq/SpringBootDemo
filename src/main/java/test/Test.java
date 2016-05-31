package test;


import yzh.spring.boot.domain.User;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by hzyouzhihao on 2016/4/26.
 */
public class Test {

    static class Ip {

        int num;

        String ip;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    static class IpComparator implements Comparator<String> {

        public int compare(String o1, String o2) {
            int flag = 0;
            String[] o1Strs = o1.split("\\.");
            String[] o2Strs = o2.split("\\.");
            //如果不符合规范，就默认最小排序
            if (o1Strs.length != 4 || o2Strs.length != 4) {
                return -1;
            }
            for (int i = 0; i < o1Strs.length; i++) {
                flag = Long.valueOf(o1Strs[i]).compareTo(Long.valueOf(o2Strs[i]));
                if (flag != 0) {
                    break;
                }
            }
            return flag;
        }
    }


    public static void main(String[] args) {
//        String inputFilePath = "e:\\input.txt";
//        String outputFilePath = "e:\\output.txt";
//
//        processData(inputFilePath, outputFilePath);
        // test();
        test1();
    }

    /**
     * 测试对LIST进行分批查询
     */
    public static void test() {
        int SEARCH_LIMIT = 2;
        List<String> goodsNos = new ArrayList<String>();
        for (int i = 0; i < 8; i++) {
            goodsNos.add(String.valueOf(i));
        }
        int fromIndex = 0;
        int toIndex = 0;
        while (toIndex < goodsNos.size()) {
            List<String> subGoodsNos;
            toIndex += SEARCH_LIMIT;
            if (toIndex >= goodsNos.size()) {
                subGoodsNos = goodsNos.subList(fromIndex, goodsNos.size());
            } else {
                subGoodsNos = goodsNos.subList(fromIndex, toIndex);
            }
            fromIndex = toIndex;
            System.out.println("-----------分页-----------");
            System.out.println(subGoodsNos);
        }
    }

    /**
     * 测试set的不可重复性
     */
    public static void test1() {
        List<User> list = new ArrayList<User>();
        User user1 = new User();
        User user2 = new User();
        user1.setUserName("youzhihao");
        user1.setUserPassword("123");
        user2.setUserName("youzhihao");
        user2.setUserPassword("123");
        list.add(user1);
        list.add(user2);
        Set<String> set = new HashSet<String>();
        for (User user : list) {
            set.add(user.getUserName() + user.getUserPassword());
        }
        for (String content : set) {
            System.out.println(content);
        }
    }


    public static void processData(String inputFilePath, String outputFilePath) {

        List<String> list = inputDataByFile(inputFilePath);
        Collections.sort(list, new IpComparator());
        String temp = "";
        int num = 0;
        List<Ip> result = new ArrayList<Ip>();
        for (String str : list) {
            if (!temp.equals(str)) {
                temp = str;
                num++;
            }
            Ip ip = new Ip();
            ip.setIp(str);
            ip.setNum(num);
            result.add(ip);
        }
        outputData2File(result, outputFilePath);
    }

    public static List<String> inputDataByFile(String inputFilePath) {
        List<String> datas = null;
        try {
            Path path = FileSystems.getDefault().getPath(inputFilePath);
            datas = Files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static void outputData2File(List<Ip> list, String outputFilePath) {
        try {
            File file = new File(outputFilePath);
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Ip ip : list) {
                writer.write(ip.getNum() + "--------" + ip.getIp());
                writer.newLine();
            }
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
