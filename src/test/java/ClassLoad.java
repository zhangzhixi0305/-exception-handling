/**
 * @ClassName ClassLoad
 * @Author zhangzhixi
 * @Description
 * @Date 2022-3-27 15:20
 * @Version 1.0
 */
public class ClassLoad {
    public static void main(String[] args) {
        System.out.println(Test.str);
    }

    public static class Test {

        static {
            String[] atr = {"3", "1"};
            String[] strings = new String[]{"1", "2"};
            System.out.println("静态代码块被加载了~");
        }

        public static final String str = "hello";
    }
}
