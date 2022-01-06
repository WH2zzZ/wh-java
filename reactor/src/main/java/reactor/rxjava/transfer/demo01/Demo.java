package com.oowanghan.ractor.rxjava.transfer.demo01;

import com.oowanghan.ractor.rxjava.transfer.demo01.Course;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import org.junit.Test;

/**
 * @Author WangHan
 * @Create 2021/6/6 5:40 下午
 */
public class Demo {

    /**
     * 遍历学生列表里面的课程列表
     */
    @Test
    public void test01() {
        ArrayList<Student> studentList = getStudents();

        studentList.forEach(student -> {
            student.getCourseList().forEach(course -> {
                System.out.println(course.getId() + ":" + course.getName());
            });
        });

    }

    /**
     * rxjava改进版-map
     */
    @Test
    public void test02() {
        ArrayList<Student> studentList = getStudents();

        Observable.fromIterable(studentList).map(student -> {
            return student.getCourseList();
        }).subscribe(new Consumer<List<Course>>() {
            @Override
            public void accept(List<Course> courses) throws Exception {
                courses.forEach(course -> {
                    System.out.println(course.getId() + ":" + course.getName());
                });
            }
        });

    }

    /**
     * rxjava改进版-flatmap
     */
    @Test
    public void test03() {
        ArrayList<Student> studentList = getStudents();

        Observable.fromIterable(studentList).flatMap(student -> {
            return Observable.fromIterable(student.getCourseList());
        }).subscribe(new Consumer<Course>() {
            @Override
            public void accept(Course course) throws Exception {
                System.out.println(course.getId() + ":" + course.getName());
            }
        });

    }

    private Observer<List<String>> getCommonObserver() {
        Observer<List<String>> observer = new Observer<List<String>>() {

            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println("subscribe!");
            }

            @Override
            public void onNext(@NonNull List<String> message) {
                System.out.println("message:" + message);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("error:" + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };
        return observer;
    }

    private ArrayList<Student> getStudents() {
        Student student = new Student();
        student.setId("1");
        student.setName("marry");
        ArrayList<Course> objects = new ArrayList<>();
        objects.add(makeCourse("1-1", "chinese"));
        objects.add(makeCourse("1-2", "math"));
        student.setCourseList(objects);

        Student student2 = new Student();
        student2.setId("2");
        student2.setName("jerry");
        ArrayList<Course> objects2 = new ArrayList<>();
        objects2.add(makeCourse("2-1", "chinese"));
        objects2.add(makeCourse("2-2", "math"));
        student2.setCourseList(objects2);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);
        return studentList;
    }

    private Course makeCourse(String id, String name) {
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        return course;
    }
}
