package MyTest;

public class MyClass {

    // 静态变量（类变量）
    private static int staticCount = 0;

    // 实例变量
    private int instanceCount = 0;

    // 类方法（静态方法）
    public static void incrementStaticCount() {
        staticCount++;
        System.out.println("Static Count: " + staticCount);
/*        // 类方法可以访问和修改静态变量，但不能直接访问和修改实例变量
        instanceCount++;*/
    }

    // 实例方法
    public void incrementInstanceCount() {
/*        // 实例方法可以访问和修改实例变量，也可以访问静态变量
        staticCount++;*/
        instanceCount++;
        System.out.println("Instance Count: " + instanceCount);
    }

    public static void main(String[] args) {
        // 调用类方法
        MyClass.incrementStaticCount();  // 输出: Static Count: 1
        MyClass.incrementStaticCount();  // 输出: Static Count: 2
        MyClass.incrementStaticCount();  // 输出: Static Count: 3


        // 创建实例并调用实例方法
        MyClass instance1 = new MyClass();
        instance1.incrementInstanceCount();  // 输出: Instance Count: 1

        MyClass instance2 = new MyClass();
        instance2.incrementInstanceCount();  // 输出: Instance Count: 1

        MyClass instance3 = new MyClass();
        instance3.incrementInstanceCount();  // 输出: Instance Count: 1
    }
}

