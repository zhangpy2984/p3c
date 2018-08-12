/**
 * @author zhangpengyue
 * @date 2018/8/12
 */
public class T {
    public static void main(String[] args) {
        Object a = 3L;
        System.out.println(a.getClass().getName());
        try {
            Class.forName("java.lang.Long");
            System.out.println(a instanceof Long);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println(2 instanceof Class.forName(""));
    }
}
