package org.example.record;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.LinkedHashMap;
import java.util.Map;

public class RecordReflectionExample {
    public static void main(String[] args) {
        Class<Person> personClass = Person.class;
        
        // 1. 检查是否为记录类
        boolean isRecord = personClass.isRecord();
        System.out.println("Is record: " + isRecord); // true
        
        // 2. 获取记录组件（Record Components）
        RecordComponent[] components = personClass.getRecordComponents();
        for (RecordComponent component : components) {
            System.out.println("Component name: " + component.getName());
            System.out.println("Component type: " + component.getType());
            System.out.println("Accessor method: " + component.getAccessor());
        }
        
        // 3. 通过记录组件获取值
        Person person = new Person("Alice", 30);
        for (RecordComponent component : components) {
            try {
                Method accessor = component.getAccessor();
                Object value = accessor.invoke(person);
                System.out.println(component.getName() + " = " + value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // 4. 获取规范构造方法
        try {
            Class<?>[] paramTypes = new Class<?>[components.length];
            for (int i = 0; i < components.length; i++) {
                paramTypes[i] = components[i].getType();
            }
            Constructor<Person> constructor = personClass.getDeclaredConstructor(paramTypes);
            Person newPerson = constructor.newInstance("Bob", 25);
            System.out.println("Created person: " + newPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 应用：通过反射将一个 record 记录类转为 Map<String, Object> 形式
    // 优点：record 类所有字段天生可访问，不用找 Getter 方法判断是否能够访问某个 private 字段
    public static Map<String, Object> toUnmodifiableMap(Record record) {
        if (record == null) {
            return Map.of(); // 空不可变 Map
        }

        Class<?> clazz = record.getClass();
        if (!clazz.isRecord()) {
            throw new IllegalArgumentException("对象 " + clazz.getName() + " 不是 record 类型");
        }

        Map<String, Object> map = new LinkedHashMap<>();
        try {
            for (RecordComponent component : clazz.getRecordComponents()) {
                Object value = component.getAccessor().invoke(record);
                map.put(component.getName(), value);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Record 转 Map 失败: " + clazz.getName(), e);
        }

        // 返回不可变 Map（Java 10+）
        return Map.copyOf(map);
    }
}
