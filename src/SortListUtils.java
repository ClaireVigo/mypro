import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 数组排序List
 */
public class SortListUtils {
    public static void main(String[] args) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        //测试date类型
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1079);
        map1.put("date", "2019-01-13 00:00:00");
        //map1.put("date",sdf.parse("2018-01-13 00:00:00"));
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 1092);
        map2.put("date", "2011-05-21 00:00:00");
        //map2.put("date",sdf.parse("2018-05-21 00:00:00"));
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("id", 1033);
        map3.put("date", "2018-07-19 00:00:00");
        //map3.put("date",sdf.parse("2018-07-19 00:00:00"));
        list.add(map3);

        //元数据
        System.out.println("-----元数据-----");
        for (Map<String, Object> map : list) {
            System.out.println("id:" + map.get("id") + "-----" + "date:" + map.get("date"));
        }

        //排序后的数据
        System.out.println("-----排序后的数据-----");
        //list = SortListUtils.sortListByMap(list,"id","int");
        list = SortListUtils.sortListByMap(list, "id", "int");

        //String[] keys = {"id","date"};
        //String[] type = {"int","string"};
        //int[] sortTpye = {1,0};
        //list = SortListUtils.sortListByMap(list,keys,type,sortTpye);

        for (Map<String, Object> map : list) {
            System.out.println("id:" + map.get("id") + "-----" + "date:" + map.get("date"));
        }
    }

    /**
     * List<Map<String,Object>>排序
     * 根据Map的某一个键排序
     *
     * @param list 需要排序的list
     * @param key  map的键名
     * @param type map的值的类型：int、date、string(string类型的date)
     * @return list
     */
    public static List<Map<String, Object>> sortListByMap(List<Map<String, Object>> list, String key, String type) {
        if (list == null || list.size() <= 0 || key == null || "".equals(key) || type == null || "".equals(type)) {
            return list;
        }
        if ("int".equalsIgnoreCase(type)) {
            Collections.sort(list, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    Integer num1 = Integer.valueOf(o1.get(key).toString());
                    Integer num2 = Integer.valueOf(o2.get(key).toString());
                    return num1.compareTo(num2);
                }
            });
        } else if ("date".equalsIgnoreCase(type)) {
            Collections.sort(list, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    Date d1 = (Date) o1.get("date");
                    Date d2 = (Date) o2.get("date");
                    return d1.compareTo(d2);
                }
            });
        } else if ("string".equalsIgnoreCase(type)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Collections.sort(list, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    try {
                        Date date1 = sdf.parse(o1.get(key).toString());
                        Date date2 = sdf.parse(o2.get(key).toString());
                        return date1.compareTo(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        }
        return list;
    }

}
