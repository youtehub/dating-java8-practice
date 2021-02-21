# 巧用Optional之优雅规避NPE问题

原创 翎野君 [翎驿](http://www.cnblogs.com/lingyejun/) *2020-01-29*

**翎野君**/文


## 避之不及的 NullPointerException



NPE : NullPointerException

空指针异常是最常见的Java异常之一，抛出NPE错误不是用户操作的错误，而是开发人员的错误，应该被避免，那么只能在每个方法中加入非空检查，阅读性和维护性都比较差。

以下是一个常见的嵌套对象：一个用户所拥有的汽车，以及为这个汽车配备的保险。

```java

public class User {

    private String userName;

    private Car car;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

public class Car {

    private String carName;

    private Insurance insurance;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}

public class Insurance {

    private String insuranceName;

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }
}
```

如果我们此时，需要获取一个用户对应的汽车保险名称，我们可能会写出来以下的代码

```java

private String getInsuranceName(User user) {
    return user.getCar().getInsurance().getInsuranceName();
}
```

 


## 02.避免NullPointerException的方法



显然上面的程序是存在诸多<u>NullPointerException</u>隐患的，为了保证程序的健壮性，我们需要尽量避免出现空指针NullPointerException，那么通常我们会有以下两种写法。



### 1.深层质疑

```java

private String getInsuranceName(User user) {
        if (user != null) {
            Car car = user.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getInsuranceName();
                }
            }
        }
        return "not found";
    }
```

### 2.及时退出

```java
private String getInsuranceName(User user) {
    if (user == null) {
        return "not found";
    }
    Car car = user.getCar();
    if (car == null) {
        return "not found";
    }
    Insurance insurance = car.getInsurance();
    if (insurance == null) {
        return "not found";
    }
    return insurance.getInsuranceName();
}
```

为了避免出现空指针，我们通常会采用以上两种写法，但是它们复杂又冗余，为了鼓励程序员写更干净的代码，代码设计变得更加的优雅。Java8提供了Optional类来优化这种写法。




## 03.Optional



### Optional入门

Java 8中引入了一个新的类java.util.Optional，这是一个封装Optional值的类。举例来说，使用新的类意味着，如果你知道一个人可能有也可能没有车，那么Person类内部的car变量就不应该声明为Car，遇到某人没有车时把null引用值给它后就有可能会出现空指针的问题，应该如下图所示直接将其声明为Optional类型。

![图片](https://img-blog.csdnimg.cn/img_convert/c6f8f653b45dd6073a73eb84ca13f019.png)

变量存在时，Optional类只是对类简单封装。变量不存在时，缺失的值会被建模成一个“空” 的Optional对象，由方法Optional.empty()返回。它返回Optional类的特定单一实例。



### null引用和Optional.empty() 有什么本质的区别吗?

从语义上，你可以把它们当作一回事儿，但是实际中它们之间的差别非常大:*如果你尝试直接引用一个null，一定会触发NullPointerException，不过使用 Optional.empty()就完全没事儿，它是Optional类的一个有效对象。*

使用Optional而不是null的一个非常重要而又实际的语义区别是，第一个例子中，我们在声明变量时使用的是Optional类型，而不是Car类型，**这句声明非常清楚地表明了这里发生变量缺失是允许的**。与此相反，使用Car这样的类型，可能将变量赋值为null，你只能依赖你对业务模型的理解，判断一个null是否属于该变量的有效值又或是异常情况。

```java
public class User {

    private String userName;

    private Optional<Car> car;

    public String getUserName() {
        return userName;
    }
    public Optional<Car> getCar() {
        return car;
    }
}
public class Car {
    private String carName;
    private Optional<Insurance> insurance;
    public String getCarName() {
        return carName;
    }
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
public class Insurance {
    private String insuranceName;
    public String getInsuranceName() {
        return insuranceName;
    }
}
```

发现Optional是如何丰富建模时的变量语义了吧。代码中person引用的是Optional<Car>，这种方式非常清晰地表达了你的模型中一个person 可能有也可能没有car的情形，同样，car可能进行了保险，也可能没有保险。

与此同时，我们看到insurance的名称insuranceName被声明成String类型，而不是Optional，这非常清楚地表明声明为insurance的类中的名称字段insuranceName是必须存在的。所以，如果你遇到一个insurance没有名称，出现空指针异常的时候，你需要调查你的数据出了什么问题，而不应该再添加一段代码，将这个问题隐藏。

使用这种方式，<span style="text-decoration:underline"> 一旦通过引用insurance获取insuranceName时发生NullPointerException，你就能非常确定地知道出错的原因，不再需要为其添加null的检查查，因为null的检查查只会掩盖问题，并未真正地修复问题 。</span>




## 04.Optional的方法介绍



### 1.创建Optional

#### of(T value)

如果构造参数是一个null，这段代码会立即抛出一个NullPointerException，而不是等到你试图访问car的属性值时才返回一个错误。

```java
public static <T> Optional<T> of(T value) {
    return new Optional<>(value);
}
```

#### ofNullable(T value)

创建一个允许null值的Optional对象


```java
public static <T> Optional<T> ofNullable(T value) {
    return value == null ? empty() : of(value);
}
```

#### empty()

创建一个空的Optional对象

```java
     public static<T> Optional<T> empty() {
        Optional<T> t = (Optional<T>) EMPTY;
        return t;
    }
```

#### 常用方法

- get()是这些方法中最简单但又最不安全的方法。如果变量存在，它直接返回封装的变量值，否则就抛出一个NoSuchElementException异常。所以，除非你非常确定Optional变量一定包含值，否则最好不要使用这个方法。
- orElse(T other)，它允许你在 Optional对象不包含值时提供一个默认值。
- orElseGet(Supplier<? extends T> other)是orElse方法的延迟调用版，Supplier方法只有在Optional对象不含值时才执行调用。
- orElseThrow(Supplier<? extends X> exceptionSupplier)和get方法非常类似，它们遭遇Optional对象为空时都会抛出一个异常，但是使用orElseThrow你可以定制希望抛出的异常类型。
- ifPresent(Consumer<? super T>)让你能在变量值存在时执行一个作为参数传入的方法，否则就不进行任何操作。



**注意：**

**orElse(T other)和orElseGet(Supplier<? extends T> other)的区别**

当value值不为null时，orElse函数依然会执行返回T的方法，而orElseGet函数并不会执行返回T的方法。



### 2.用map从Optional中提取和转换值

#### map(Function<? super T, ? extends U> mapper)
可以把Optional对象看成一种特殊的集合数据，它至多包含一个元素。如果Optional包含一个值，那函数就将该值作为参数传递给map，对该值进行转换。如果Optional为空，就什么也不做。

```java
String optionMap = Optional.ofNullable("abc").map(value -> value.toUpperCase()).get();
```

#### 使用flatMap链接Optional对象

**flatMap(Function<? super T, Optional> mapper)**

将两层的optional合并为一个


```java
String optionFlatMap = Optional.ofNullable("abc").flatMap(value -> Optional.of((value + "flat-map").toUpperCase())).get();

```

### 3.用filter剔除特定的值

**filter(Predicate<? super T> predicate)**

filter方法接受一个谓词作为参数。如果Optional对象的值存在，并且它符合谓词的条件， filter方法就返回其值;否则它就返回一个空的Optional对象。

```java
Optional<String> filterOptional = Optional.ofNullable("abc").filter(value -> Objects.equals(value, "abc"));
```





## 05.实战



**尝试获取用户的用户名称，不存在则返回默认值**

```java
String userName = Optional.ofNullable(user).orElse(new User()).getUserName();
```

**尝试获取用户的carName,不存在则返回null**

```java
String carName = Optional.ofNullable(user).map(u -> u.getCar()).map(c -> c.getCarName()).orElse(null);
```

**用户名存在的时候则转为大写**

```java
String optionMap = Optional.ofNullable("abc").map(value -> value.toUpperCase()).get();
```

**过滤出来用户名称是张三的用户**

```java
Optional<String> filterOptional = Optional.ofNullable("张三").filter(value -> Objects.equals(value, "张三"));
```

**将张三的用户名称更改为李四**

```java
Optional.ofNullable(user).filter(user1 -> Objects.equals(user1.getUserName(), "张三")).ifPresent(user1 -> {
user1.setUserName("李四");
System.out.println(user.getUserName());
});
```

