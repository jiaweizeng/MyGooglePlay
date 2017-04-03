package com.example.leon.googleplaydemo35;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
   }

    @Test
    public void testObserverPattern() {
        Teacher teacher = new Teacher();//被观察者

        //观察者
        Student student1 = new Student("student1");
        Student student2 = new Student("student2");

        //给被观察者添加观察者
        teacher.addObserver(student1);
        teacher.addObserver(student2);

        //发布更新
        teacher.publishMessage();
    }
}