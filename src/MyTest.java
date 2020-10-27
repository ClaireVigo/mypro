import java.text.SimpleDateFormat;
import java.util.*;

public class MyTest {

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();

        map1.put("name", "张三");
        map1.put("approvalDepartment", "企法部");
        map1.put("approvalDate", " 2018-08-03 16:19:36:403000");
        list.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "李四");
        map2.put("approvalDepartment", "企法部");
        map2.put("approvalDate", " 2018-08-04 16:19:36:403000");
        list.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "赵柳");
        map3.put("approvalDepartment", "文书部");
        map3.put("approvalDate", " 2018-08-11 16:19:36:403000");
        list.add(map3);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("name", "王五");
        map4.put("approvalDepartment", "企法部");
        map4.put("approvalDate", " 2018-08-01 16:19:36:403000");
        list.add(map4);

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("name", "赵2");
        map5.put("approvalDepartment", "机电部");
        map5.put("approvalDate", " 2018-08-02 16:19:36:403000");
        list.add(map5);

        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("name", "赵3");
        map6.put("approvalDepartment", "企法部");
        map6.put("approvalDate", " 2018-08-06 16:19:36:403000");
        list.add(map6);

        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("name", "赵8");
        map7.put("approvalDepartment", "机电部");
        map7.put("approvalDate", " 2018-08-01 16:19:36:403000");
        list.add(map7);

        MyTest m = new MyTest();
        List<Map<String, Object>> newList = m.filterBySameDept(list);

        for (Map<String, Object> s : newList) {
            System.out.println("name:" + s.get("name"));
            System.out.println("approvalDepartment:" + s.get("approvalDepartment"));
            System.out.println("approvalDate:" + s.get("approvalDate"));
            System.out.println("---------------------");
            ;

        }

    }


    public List<Map<String, Object>> filterBySameDept(List<Map<String, Object>> list) {
        List<Map<String, Object>> newList = list;
        for (int i = 0; i < newList.size(); i++) {
            for (int j = i + 1; j < newList.size(); j++) {
                if (newList.get(j).get("approvalDepartment").equals(newList.get(i).get("approvalDepartment"))) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    try {
                        Date d1 = sdf.parse(newList.get(j).get("approvalDate").toString());
                        Date d2 = sdf.parse(newList.get(i).get("approvalDate").toString());
                        Integer result = d1.compareTo(d2);
                        if (result > 0) {
                            newList.remove(i);
                        } else if (result < 0) {
                            newList.remove(j);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return newList;
    }
}
