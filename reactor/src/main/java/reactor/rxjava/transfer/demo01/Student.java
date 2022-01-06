package reactor.rxjava.transfer.demo01;

import lombok.Data;

import java.util.List;

/**
 * @Author WangHan
 * @Create 2021/6/6 5:30 下午
 */
@Data
public class Student {

    private String id;

    private String name;

    private List<Course> courseList;
}
