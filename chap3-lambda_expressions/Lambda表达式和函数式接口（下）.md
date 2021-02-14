## Lambda表达式和函数式接口（下）

原创 翎野君 [翎驿](http://www.cnblogs.com/lingyejun)   <font color='gray'>2020-01-23</font>



**翎野君**/文


## 01.使用函数式接口





- 函数式接口定义且只定义了一个抽象方法。
- 函数式接口很有用， 因为抽象方法的签名可以描述Lambda表达式的签名。
- 为了应用不同的Lambda表达式，你需要一套能够描述常见函数描述符的函数式接口。
- Java 8的库设计师帮我们在java.util.function包中引入了几个新的函数式接口。

 

## 02.常用的函数式接口





一元函数

- Function（一般函数）
- Consumer（消费者）
- Predicate（谓词函数）
- Supplier（供应者）

二元函数

- BiFunction（一般函数）
- BiConsumer（消费者）
- BiPredicate（谓词函数）





```java
public class FunctionLearn {

        /**
         * Function
         */
        public static void learnFunction() {
            Function<String, String> functionStr = (String s) -> s + "。";
            System.out.println(functionStr.apply("Hello World"));

            Function<Integer, Integer> function1 = (Integer a) -> a + 2;
            Integer x = function1.apply(5);
            System.out.println(x);
            Function<Integer, Integer> function2 = (Integer a) -> a * 2;

            // 组合两个Function函数，a * 2 compose a+2 = (a+2) * 2
            Function<Integer, Integer> function3 = function2.compose(function1);
            System.out.println("Function3 : " + function3.apply(20));

            // 先后顺序拼接两个Function a *2 andThen a+2 = (a*2) + 2
            Function<Integer, Integer> function4 = function2.andThen(function1);
            System.out.println("Function4 : " + function4.apply(15));

            // 输入啥返回啥
            Function.identity();
        }

        /**
         * Consumer
         */
        public static void learnConsumer() {
            Consumer<Integer> consumer1 = (Integer a) -> System.out.println("Consumer 1 : " + a);
            // 吃掉外部传进来的T，在方法内部消化掉，什么也不返回
            consumer1.accept(100);
            Consumer<Integer> consumer2 = (Integer a) -> System.out.println("Consumer 2 : " + a + "Done");
            Consumer<Integer> consumer3 = consumer1.andThen(consumer2);
            consumer3.accept(10);
            consumer1.andThen(consumer2).accept(10);
        }

        /**
         * Supplier
         */
        public static void learnSupplier() {
            // 无中生有，凭空生成一个东西出来
            Supplier<Integer> supplier = () -> 10;
            Integer a = supplier.get();
            System.out.println(a);
        }

        /**
         * Predicate
         */
        public static void learnPredicate() {
            Predicate<Integer> predicate1 = (Integer a) -> a > 10;
            System.out.println(predicate1.test(20));

            Predicate<Integer> predicate2 = (Integer a) -> a < 20;
            // and 与操作
            Predicate<Integer> predicate3 = predicate1.and(predicate2);
            System.out.println(predicate3.test(9));

            Predicate<Integer> predicate4 = (Integer a) -> a > 8;
            // or 或操作
            System.out.println(predicate1.or(predicate4).test(7));

            // ! 取反操作
            System.out.println(predicate4.negate().test(7));
        }

        /**
         * BiFunction
         */
        public static void learnBiFunction() {
            BiFunction<Integer, Integer, Integer> biFunction1 = (Integer a, Integer b) -> a + b;
            System.out.println(biFunction1.apply(10, 15));
            Function<Integer, Integer> biFunction2 = (Integer a) -> a * 2;
            System.out.println(biFunction1.andThen(biFunction2).apply(10, 15));
        }

        /**
         * BiConsumer
         */
        public static void learnBiConsumer() {
            BiConsumer<Integer, Integer> biConsumer1 = (Integer a, Integer b) -> System.out.println(a + b);
            biConsumer1.accept(1, 2);
            BiConsumer<Integer, Integer> biConsumer2 = (Integer a, Integer b) -> System.out.println(a * b);
            biConsumer1.andThen(biConsumer2).accept(1, 2);
        }

        /**
         * BiPredicate
         */
        public static void learnBiPredicate() {
            BiPredicate<Integer, Integer> biPredicate1 = (Integer a, Integer b) -> a > 10 && b < 15;
            System.out.println(biPredicate1.test(11, 14));
            BiPredicate<Integer, Integer> biPredicate2 = (Integer a, Integer b) -> a > 13;

            System.out.println(biPredicate1.and(biPredicate2).test(11, 14));
            System.out.println(biPredicate1.or(biPredicate2).test(11, 14));
            System.out.println(biPredicate2.negate().test(11, 14));
        }

        public static void main(String[] args) {

            learnFunction();
            learnConsumer();
            learnSupplier();
            learnPredicate();
            learnBiFunction();
            learnBiConsumer();
            learnBiPredicate();
        }
    }
```


## 03.方法引用



***简介***

方法引用可以被看作仅仅调用特定方法的Lambda的一种快捷写法。

当你需要使用方法引用时，目标引用放在分隔符::前，方法的名称放在后面。

例如， Phone::getPrice就是引用了Phone类中定义的方法getPrice。请记住，不需要括号，因为你没有实际调用这个方法。方法引用就是Lambda表达式(Phone a) -> a.getPrice()的快捷写法。



***构建方法引用***

方法引用主要有三类。

1. 指向静态方法的方法引用(例如Integer的parseInt方法，写作Integer::parseInt)。
2. 指向任意类型实例方法的方法引用(例如String的length方法，写作 String::length)。
3. 指向现有对象的实例方法的方法引用(假设你有一个局部变量mobileCategory用于存放Category类型的对象，它支持实例方法getValue，那么你就可以写mobileCategory::getValue)。

![](https://img-blog.csdnimg.cn/20210211213705964.png)


***释义***

第二种和第三种方法引用可能乍看起来有点儿晕。

类似于String::length的第二种方法引用的思想就是你在引用一个对象的方法，而这个对象本身是Lambda的一个参数。例如，Lambda表达式(String s) -> s.toUppeCase()可以写作String::toUpperCase。

但第三种方法引用指的是，你在Lambda中调用一个已经存在的外部对象中的方法。例如，Lambda表达式()->mobileCategory.getValue()可以写作mobileCategory::getValue。



```java

public class MethodReference {

    public static void main(String[] args) {
        Function<String, Integer> function = (String s) -> Integer.parseInt(s);
        Integer a = function.apply("15");
        System.out.println(a);

        // 指向静态方法的方法引用(例如Integer的parseInt方法，写作Integer::parseInt)。
        Function<String, Integer> function1 = Integer::parseInt;
        System.out.println(function1.apply("20"));

        // 指向任意类型实例方法的方法引用
        Function<String, Integer> function2 = (String s) -> s.length();
        System.out.println(function2.apply("abc"));
        Function<String, Integer> function3 = String::length;
        System.out.println(function3.apply("abcd"));

        // 指向现有对象的实例方法的方法引用
        // 调用外部的对象
        PhonePredicate phonePredicate = (Phone phone) -> true;
        Function<Phone, Boolean> function4 = (Phone phone) -> phonePredicate.test(phone);
        System.out.println(function4.apply(new Phone()));
        Function<Phone, Boolean> function5 = phonePredicate::test;
        System.out.println(function5.apply(new Phone()));

        Supplier<Phone> supplier = Phone::new;
        Function<Integer,Phone> function6 = Phone::new;
    }
}

```



***构造函数引用***

对于一个现有构造函数，我们可以利用它的名称和关键字new来创建它的一个引用: ClassName::new。

它的功能与指向静态方法的引用类似。

*无参构造函数*

例如，假设有一个构造函数没有参数。 它适合Supplier的签名() -> Phone。



```java
Supplier<Phone> c1 = Phone::new;
Phone a1 = c1.get();
```

这就等价于:


```java
Supplier<Phone> c1 = () -> new Phone();
Phone a1 = c1.get();
```

*有参构造函数*

如果你的构造函数的签名是Phone(Integer price)，那么它就适合Function接口的签名。

```java
Function<Integer, Phone> c2 = Phone::new;
Phone a2 = c2.apply(110);
```

这就等价于:

```java
Function<Integer, Phone> c2 = (price) -> new Phone(price);
Phone a2 = c2.apply(110);
```


## 04.Lambda和方法引用实战
再研究开始的那个问题- -用不同的排序策略给个Phone列表排序，并需要展示如何把一个原始粗暴的解决方案变得精炼简单。

第1步:传递代码

第2步:使用匿名类

第3步:用Lambda表达式

第4步:用方法引用


## 05.小结


- Lambda表达式可以理解为一种匿名函数:它没有名称，但有参数列表、函数主体、返回类型，可能还有一个可以抛出的异常的列表。
- 函数式接口就是仅仅声明了一个抽象方法的接口。
- 只有在接受函数式接口的地方才可以使用Lambda表达式。
- Lambda表达式允许你直接内联，为函数式接口的抽象方法提供实现，并且将整个表达式作为函数式接口的一个实例。
- Java 8自带一些常用的函数式接口，放在java.util.function包里，包括Predicate<T>、Function<T,R>、Supplier<T>、Consumer<T>。
- Comparator、Predicate和Function等函数式接口都有几个可以用来结合Lambda表达式的默认方法。

