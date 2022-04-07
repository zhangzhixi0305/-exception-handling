import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import sun.misc.Unsafe;

import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @ClassName MyTest
 * @Author zhangzhixi
 * @Description
 * @Date 2022-3-13 21:27
 * @Version 1.0
 */
public class MyTest {

    // HttpURLConnection方式
    @Test
    public void test() {
        String SUBMIT_METHOD_GET = "GET";  // 一定要是大写，否则请求无效

        String urlStr = "http://timor.tech/api/holiday/next/2018-6-1";  // 请求http地址
//        String param = "2020";  // 请求参数

        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;  // 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(urlStr);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：GET
            connection.setRequestMethod(SUBMIT_METHOD_GET);
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，请求成功后获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                }
                result = sbf.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            assert connection != null;
            connection.disconnect();  // 关闭远程连接
        }
        System.out.println("Successfully：" + result);
    }

    @Test
    public void testThreadLocal() throws ExecutionException, InterruptedException {
        // 第一次看到FutureTask时，相信大家会震惊：啥玩意，怎么把Callable往FutureTask里塞呢？！
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + "========>正在执行");
                try {
                    Thread.sleep(3 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "success";
            }
        });


        // 看到这，你再次震惊：啥玩意，怎么又把FutureTask塞到Thread里了呢？！
        new Thread(futureTask).start();
        System.out.println(Thread.currentThread().getName() + "========>启动任务");


        // 可以获取异步结果（会阻塞3秒）
        String result = futureTask.get();
        System.out.println("任务执行结束，result====>" + result);
    }

    @Test
    public void testThreadPoolDebug() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,                           // 核心线程数1
                2,                           // 最大线程数2
                1,                           // 存活时间设为1小时，避免测试期间线程被回收
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(1)  // 阻塞队列长度为1
        );

        threadPoolExecutor.execute(() -> {
            System.out.println("执行第1个任务...." + Thread.currentThread().getName());
            sleep(100);
        });

        sleep(1);

        threadPoolExecutor.execute(() -> {
            System.out.println("执行第2个任务..." + Thread.currentThread().getName());
            sleep(100);

        });

        sleep(1);

        threadPoolExecutor.execute(() -> {
            System.out.println("执行第3个任务..." + Thread.currentThread().getName());
            sleep(100);
        });

        sleep(1);

        threadPoolExecutor.execute(() -> {
            System.out.println("执行第4个任务..." + Thread.currentThread().getName());
            sleep(100);
        });

        sleep(1);
        System.out.println("main结束");
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCallBack() throws InterruptedException, ExecutionException {
        // 提交一个任务，返回CompletableFuture
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("=============>异步线程开始...");
                System.out.println("=============>异步线程为：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("=============>异步线程结束...");
                return "supplierResult";
            }
        });

        // 阻塞获取结果
        System.out.println("异步结果是:" + completableFuture.get());
        System.out.println("main结束");
    }

    @Test
    public void testCallBack2() throws InterruptedException, ExecutionException {
        // 提交一个任务，返回CompletableFuture（注意，并不是把CompletableFuture提交到线程池，它没有实现Runnable）
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("=============>异步线程开始...");
            System.out.println("=============>异步线程为：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=============>异步线程结束...");
            return "supplierResult";
        });
        System.out.println("completableFuture执行结果是：" + completableFuture.get());
        // 异步回调：上面的Supplier#get()返回结果后，异步线程会回调BiConsumer#accept()
        completableFuture.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("=============>异步任务结束回调...");
                System.out.println("=============>回调线程为：" + Thread.currentThread().getName());
            }
        });

        // CompletableFuture的异步线程是守护线程，一旦main结束就没了，为了看到打印结果，需要让main休眠一会儿
        System.out.println("main结束");
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void testUnsafe() throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        /*申请4个字节，空间*/
        long address = unsafe.allocateMemory(4);
        unsafe.putInt(address, 66666666);
        System.out.println(unsafe.getInt(address));
        unsafe.freeMemory(address);
        System.out.println(unsafe.getInt(address));

    }

    @Test
    public void weakReference() {
        Map map = new HashMap();


        Integer integer = new Integer(1);

        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(integer, "hello");

        integer = null;
//        System.gc();

        System.out.println(weakHashMap);
    }

    public void myTest() {
        System.out.println("myTest");
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(format);

        User user = new User("张三", "123456", 1);
        System.out.println("myTest");
    }

    public User getUserByID(int id) {
        User user = new User("张三", "123456", 1);


        /**
         * SELECT
         * *
         * from T_LOG_PAGEVIEW_RERECORD
         * where
         * F_RECORD_TIME  >= TO_DATE( '2021-01-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss' )
         * and
         * F_RECORD_TIME  <= TO_DATE( '2021-01-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss' )
         */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM T_LOG_PAGEVIEW_RERECORD WHERE F_RECORD_TIME >= TO_DATE( '");
        sql.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        sql.append("', 'yyyy-mm-dd hh24:mi:ss') AND F_RECORD_TIME <= TO_DATE( '");
        sql.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        sql.append("', 'yyyy-mm-dd hh24:mi:ss')");
        return user;
    }

    public User getUserByName(String name) {
        User user = new User("张三", "123456", 1);
        return user;
    }


    @Test
    public void test1() {
        StringUtils.trimAllWhitespace("  ");

        String str = "123456789";
        byte[] bytes = str.getBytes();
        try {

            FileCopyUtils.copy(bytes, new File("H:" + File.separatorChar + "test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String username;
    private String password;
    private Integer userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}






















