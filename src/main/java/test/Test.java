package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hzyouzhihao on 2016/4/26.
 */
public class Test {

    public static void main(String[] args) {
        class Ip {
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
        List<String> list = new ArrayList<String>();
        list.add("1.12.193.198");
        list.add("1.12.193.198");
        list.add("1.12.106.136");
        list.add("1.12.193.198");
        list.add("1.12.106.136");
        Collections.sort(list);
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
        for (Ip ip : result) {
            System.out.println(ip.getNum() + "-------" + ip.getIp());
        }
    }


}
