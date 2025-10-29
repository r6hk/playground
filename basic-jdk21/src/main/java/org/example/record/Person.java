package org.example.record;

// 允许实现接口，不允许继承其他类（所有 record 都隐式继承自 java.lang.Record）
public record Person(
        // 字段都是 private final 的，不允许额外加访问修饰符
        String name,
        int age
) {

    public Person {
        // 紧凑构造方法，可以用来验证参数
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        // 不需要赋值语句，编译器会自动添加
    }

    public Person(String name) {
        // 自定义构造方法中必须非递归地调用其他构造方法
        this(name, 0);
    }

    public Person(int a, String b) {

    }

    // 自动生成 toString, equals, hashcode 方法
}

