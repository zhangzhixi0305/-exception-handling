package memberinnerclass;

import java.io.Serializable;

/**
 * @ClassName MemberInner
 * @Author zhangzhixi
 * @Description
 * @Date 2022-3-25 10:21
 * @Version 1.0
 */
public class MemberInner implements Serializable {

    private static final long serialVersionUID = -3395823135060848500L;
    private int a = 1;
    String name = "张三";

    class Inner {
        String name = "李四";
        private int b = 2;

        public void test() {
            System.out.println("调用外部类a：" + a);
            System.out.println("调用内部类b：" + b);
            System.out.println("外部类中相同的属性：" + MemberInner.this.name);
            System.out.println("内部类中相同的属性：" + this.name);

        }
    }

    public static void main(String[] args) {
        MemberInner memberInner = new MemberInner();
        Inner inner = memberInner.new Inner();
        inner.test();
    }
}
