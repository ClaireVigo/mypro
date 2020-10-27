import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 一个List<Map<String,Object>>集合包含id和date两个字段，先根据id正序排，再根据date倒序排
 */
public class SortListByMap {

    public static void main(String[] args) throws Exception {

        try {
            System.out.println("-----------未处理数据-----------");
            //-----测试数据-----开始-----
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("id", "2");
            map1.put("date", "2017-01-01 00:00:00:669000");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("id", "1");
            map2.put("date", "2015-01-01 00:00:00:669000");

            Map<String, Object> map3 = new HashMap<>();
            map3.put("id", "3");
            map3.put("date", "2017-01-01 00:00:00:669000");

            Map<String, Object> map4 = new HashMap<>();
            map4.put("id", "2");
            map4.put("date", "2013-01-02 00:00:00:669000");

            Map<String, Object> map5 = new HashMap<>();
            map5.put("id", "4");
            map5.put("date", "2010-01-01 00:00:00:669000");

            Map<String, Object> map6 = new HashMap<>();
            map6.put("id", "2");
            map6.put("date", "2018-01-01 00:00:00:669000");

            Map<String, Object> map7 = new HashMap<>();
            map7.put("id", "1");
            map7.put("date", "2018-01-01 00:00:00:669000");
            list.add(map1);
            list.add(map2);
            list.add(map3);
            list.add(map4);
            list.add(map5);
            list.add(map6);
            list.add(map7);

            for (Map<String, Object> map : list) {
                System.out.println(map.get("id") + "---" + map.get("date"));
            }
            //-----测试数据-----结束-----


            System.out.println("-----------根据id正序排序-----------");
            //-----根据id排序-----开始-----
            Collections.sort(list, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    Integer s1 = Integer.valueOf(o1.get("id").toString());
                    Integer s2 = Integer.valueOf(o2.get("id").toString());
                    return s1.compareTo(s2);
                }
            });

            for (Map<String, Object> map : list) {
                System.out.println(map.get("id") + "---" + map.get("date"));
            }
            //-----根据id排序-----结束-----


            System.out.println("-----------再根据date倒序排序-----------");
            //-----再根据date排序-----开始-----
            List<Map<String, Object>> finalList = new ArrayList<>();
            List<Map<String, Object>> newList = null;
            for (int i = 0; i < list.size(); i++) {
                newList = new ArrayList<>();
                if (i == 0) {//第一个
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(i).get("id").toString().equals(list.get(j).get("id"))) {
                            newList.add(list.get(j));
                        }
                    }
                    Collections.sort(newList, new Comparator<Map<String, Object>>() {
                        @Override
                        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            try {
                                Date d1 = sdf.parse(o1.get("date").toString());
                                Date d2 = sdf.parse(o2.get("date").toString());
                                return d1.compareTo(d2);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                    Collections.reverse(newList);//正排注释这一行
                } else {//其他
                    String id = list.get(i).get("id").toString();
                    String idLast = list.get(i - 1).get("id").toString();
                    if (id.equals(idLast)) {
                        continue;
                    } else {
                        newList.add(list.get(i));
                        for (int j = i + 1; j < list.size(); j++) {
                            if (id.equals(list.get(j).get("id"))) {
                                newList.add(list.get(j));
                            }
                        }

                        Collections.sort(newList, new Comparator<Map<String, Object>>() {
                            @Override
                            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                try {
                                    Date d1 = sdf.parse(o1.get("date").toString().toString());
                                    Date d2 = sdf.parse(o2.get("date").toString().toString());
                                    return d1.compareTo(d2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return 0;
                            }
                        });
                        Collections.reverse(newList);//正排注释这一行
                    }
                }
                for (Map<String, Object> map : newList) {
                    finalList.add(map);
                }
            }
            //-----再根据date排序-----结束-----


            for (Map<String, Object> map : finalList) {
                System.out.println(map.get("id") + "---" + map.get("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
