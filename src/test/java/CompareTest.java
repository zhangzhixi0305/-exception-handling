import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @ClassName CompareTest
 * @Author zhangzhixi
 * @Description
 * @Date 2022-3-31 16:03
 * @Version 1.0
 */
public class CompareTest {
    public static void main(String[] args) {
        Student student1 = new Student(1, "张三", 23);
        Student student2 = new Student(2, "李四", 21);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        /*
        SELECT
            vvm.F_TERMINAL_TYPE AS f_type,
            COUNT( vvm.ID ) AS f_count,
            trunc( SUM( vvm.f_total_time ) / 60 / 60, 2 ) AS f_time
        FROM
            T_VIDEO_VISIT_NUM_DAY vvm
        WHERE
            vvm.F_VALID_FLAG = 0
            AND vvm.f_start_time >= to_date( '2021-01-01', 'yyyy/mm/dd' )
            AND vvm.f_start_time <  to_date( '2021-01-02', 'yyyy/mm/dd' )
        GROUP BY
            vvm.F_TERMINAL_TYPE
        */

    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class Student {
    private Integer id;
    private String name;
    private Integer age;
}