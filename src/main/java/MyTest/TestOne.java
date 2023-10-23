package MyTest;

public class TestOne {
    private int value;

    public TestOne add(int value) {
        this.value += value;
        return this; // 返回当前对象的实例
    }

    public int getValue() {
        return value;
    }
    public static void main(String[] args) {
        TestOne calculator = new TestOne();
        calculator.add(5).add(10);
        System.out.println(calculator.getValue()); // 输出 15
    }
}


