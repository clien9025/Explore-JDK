
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import zhanyang.ZhanYangArrayList;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class ArrayListTest {

    private List<String> createList() {
        return new ZhanYangArrayList<>();
    }

    @Test
    public void testArrayListAdd() {
        List<String> list = createList();

        assertTrue(list.add("AAA"));

        list.add("BBB");
        list.add("CCC");
        list.add("DDD");

        assertEquals(4, list.size());
        assertEquals("AAA", list.get(0));
        assertEquals("DDD", list.get(3));

    }

    /*
    AddAll 方法
     */
    @Test
    public void testArrayListAddTwo() {
        List<String> list = createList();

        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");

        assertEquals(4, list.size());

        // TODO 完成 list 的 addall 方法的单元测试,并总结 addall 的用法
        List<String> list1 = Arrays.asList("five", "six", "seven");
        assertEquals(3, list1.size());
        list.addAll(list1);
        assertEquals(7, list.size());
        assertTrue(list.containsAll(list1));
        assertEquals("five", list.get(4));
        assertEquals("six", list.get(5));
        assertEquals("seven", list.get(6));

    }

    // TODO 帮我完善对于 ArrayList 的单元测试,给出所有方法详细的完善的单元测试

    @Test
    public void testArrayListRemove() {
        List<String> list = createList();

        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");

        // 移除一个元素
        assertTrue(list.remove("One"));
        assertFalse(list.contains("One"));
        assertEquals(3, list.size());

        // 移除不存在的元素
        assertFalse(list.remove("Five"));
    }

    /*
    Clear 清除
     */
    @Test
    public void testArrayListClearEmpty() {
        // Arrange
        List<String> list = createList();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");

        // Act
        list.clear();

        // Assert
        assertAll("List state after clear",
                () -> assertTrue(list.isEmpty(), "List should be empty"),
                () -> assertEquals(0, list.size(), "List size should be 0")
        );
    }

    /*
    Remove,Size
     */
    @Test
    public void testArrayListSize() {
        List<String> list = createList();

        list.add("One");
        list.add("Two");
        list.add("Three");

        assertEquals(3, list.size());

        list.add("Four");
        list.remove(1);

        assertEquals(3, list.size());
    }

    /*
    For -- 迭代数组
     */
    @Test
    public void testArrayListFor() {
        List<String> list = createList();

        list.add("Zhao");
        list.add("Qian");
        list.add("Sun");
        list.add("Li");

        for (int a = 0; a < list.size(); a++) {
            System.out.println(list.get(a));
        }
    }

    /*
    For-each 迭代数组
     */
    @Test
    public void testArrayListForEach() {
        List<String> list = createList();
        list.add("Zhao");
        list.add("Qian");
        list.add("Sun");
        list.add("Li");

        // TODO 怎么使用 assert 断言语句来验证 List 的 For-each 迭代
        String[] expectedValue = {"Zhao", "Qian", "Sun", "Li"};
        int index = 0;
        for (String a : list) {
            // 如果索引超过了期望值数组的长度，则立即失败
            assertTrue(index < expectedValue.length, "List has more items than expected");
            assertEquals(a, expectedValue[index]);
            index++;
        }
        // 验证是否所有的元素都被迭代了
        assertEquals(index, expectedValue.length, "Not all list items were checked");
    }

    /*
     ContainsAll 原数组 list 是否包含传入集合里的所有元素
     Contains 原数组含有指定的"元素"才返回true
     */
    @Test
    public void ArrayListToArray() {
        List<String> list = createList();
        list.add("Zhao");
        list.add("Qian");
        list.add("Sun");
        list.add("Li");

        ArrayList<String> list1 = new ArrayList<String>(Arrays.asList("Zhao", "Zhou", "Qian", "Wang"));
        ArrayList<String> list2 = new ArrayList<String>(Arrays.asList("Zhao", "Qian"));

        assertTrue(list.containsAll(list2));// true

        assertTrue(list.contains("Zhao"));//true
        assertTrue(list.contains(list1.get(0)));//true

    }

    // TODO 完成 list 的 toArray 方法的单元测试,并且总结 toArray 的用法和使用场景
    @Test
    public void ArrayListToArrayToT() {
        List<String> list = createList();
        list.add("One");
        list.add("Two");
        list.add("Three");

        // 测试1
        Object[] array = list.toArray();// 返回一个包含此列表中所有元素的数组
        assertEquals(3, array.length);
        assertEquals("One", array[0]);
        assertEquals("Two", array[1]);
        assertEquals("Three", array[2]);

        //测试2
        String[] stringsList = new String[list.size()];

        String[] array1 = list.toArray(stringsList);
        assertEquals(3, array1.length);
        assertEquals("One", array1[0]);
        assertEquals("Two", array1[1]);
        assertEquals("Three", array1[2]);
    }


    @Test
    public void testRetainAll() {
        List<String> list = createList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        // 使用retainAll方法，尝试保留"B"和"C"
        boolean isModified = list.retainAll(Arrays.asList("B", "C"));

        // 验证列表确实发生了变化
        assertTrue(isModified);

        // 验证列表现在只包含"B"和"C"
        assertEquals(2, list.size());
        assertTrue(list.contains("B"));
        assertTrue(list.contains("C"));
        assertFalse(list.contains("A"));
        assertFalse(list.contains("D"));

        // 再次调用retainAll，这次尝试保留已经在列表中的元素"B"和"C"
        isModified = list.retainAll(Arrays.asList("B", "C"));

        // 验证列表没有发生变化
        assertFalse(isModified);
    }

    @Test
    public void testIteratorTraversal() {
        List<String> list = createList();
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator<String> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        System.out.println(iterator.hasNext());// true

        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.hasNext());// true

        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.hasNext());// true

        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
        System.out.println(iterator.hasNext());// false(迭代器没有更多元素,此时 cursor=size)
    }

    /*
    iterator 为空时,调用 next() 方法 是否会出现 NoSuchElementException
     */

    @Test
    public void testNextOnEmptyIterator() {
        List<String> list = createList();
        Iterator<String> iterator = list.iterator();

        assertThrows(NoSuchElementException.class, iterator::next);
//        // 等价形式
//        assertThrows(NoSuchElementException.class, () -> iterator.next());


//        // 4
//        test(() -> iterator.next());


//
        // 2
//        Executable executable = new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                iterator.next();
//            }
//        };
//        test(executable);

        // 3
//        test(new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                iterator.next();
//            }
//        });


        /*
        1
         */
//        test(new TestExecutable(iterator));
    }

    /*
    1
     */
//    private class TestExecutable implements Executable {
//
//        private final Iterator<String> iterator;
//
//        private TestExecutable(Iterator<String> iterator) {
//            this.iterator = iterator;
//        }
//
//        @Override
//        public void execute() throws Throwable {
//            iterator.next();
//        }
//    }
//
//    public void test(Executable executable) {
//        try {
//            executable.execute();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Test
    public void testRemove() {
        List<String> list = createList();
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator<String> iterator = list.iterator();
//        iterator.next();
        System.out.println(iterator.next());

        iterator.remove();
        assertFalse(list.contains("A"));
        assertEquals(2, list.size());
    }

    // 并发修改
    @Test
    public void testConcurrentModification() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator<String> iterator = list.iterator();
        list.add("D");
//        iterator.next();

        assertThrows(ConcurrentModificationException.class, iterator::next);



//        List<String> list = createList();
//        list.add("A");
//        list.add("B");
//        list.add("C");
//
//        Iterator<String> iterator = list.iterator();
//        list.add("D");
//
//        boolean caughtException = false;
//        try {
//            iterator.next();
//        } catch (ConcurrentModificationException e) {
//            caughtException = true;
//        }
//
//        assertTrue(caughtException, "Expected ConcurrentModificationException was not thrown.");
    }

    @Test
    public void testIterator() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        List<String> targetList = createList();
        targetList.add("A");
        targetList.add("B");
        targetList.add("C");

        for (String string : targetList) {
            assertTrue(list.contains(string));
        }

    }
}


//    @Test
//    public void ArrayListAddall() {
//        List<String> list = createList();
//
//        list.
//    }

//    @Test
//    public void ArrayListAddallIndex() {
//        List<String> list = createList();
//
//        list.addAll()
//    }
//
//    @Test
//    public void ArrayListClear() {
//        List<String> list = createList();
//
//        list.clear();
//    }
//    @Test
//    public void ArrayListAdd() {
//        List<String> list = createList();
//        list.containsAll()
//    }
//    @Test
//    public void ArrayListAdd() {
//        List<String> list = createList();
//
//        list.add()
//    }
//    @Test
//    public void ArrayListAdd() {
//        List<String> list = createList();
//
//        list.add()
//    }

