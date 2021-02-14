# Lambda表达式和函数式接口（上）

原创  翎野君  [翎驿](http://www.cnblogs.com/lingyejun)   <font color="gray"> 2020-01-22</font>



**翎野君**/文



## Lambda简介



我们可以把Lambda表达式理解为简洁地表示**可传递的匿名函数的一种方式**。它没有名称，但它有参数列表、函数主体、返回类型，可能还有一个可以抛出的异常列表。

- 匿名：我们说匿名，是因为它不像普通的方法那样有一个明确的名称。
- 函数：我们说它是函数，是因为Lambda函数不像方法那样属于某个特定的类。但和方法一样，Lambda有参数列表、函数主体、返回类型，还可能有可以抛出的异常列表。
- 传递：Lambda表达式可以作为参数传递给方法或存储在变量中。 
- 简洁：无需像匿名类那样写很多模板代码。


## 如何使用Lambda



Lambda的基本语法是


```
(parameters) -> expression (parameters) -> { statements; }
```

示例：


```
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```


Lambda表达式由三部分组成

- 参数列表：这里它采用了Comparator中compare方法的参数，两个Apple。
- 箭头：箭头->把参数列表与Lambda主体分隔开。
- Lambda主体：比较两个Apple的重量。表达式就是Lambda的返回值了。 

Lambda的基本语法是

- (parameters) -> expression
- (parameters) -> { statements; }


## 函数式接口

***什么是函数式接口？***

一言以蔽之，函数式接口就是只定义一个抽象方法的接口。 

可以在函数式接口上使用Lambda表达式。



***函数式接口可以做什么？***

Lambda表达式允许我们直接以，内联的形式，为函数式接口的抽象方法，提供实现。并把整个表达式作为函数式接口的实例。

简单来说，**Lambda就是函数式接口的一个具体实现的实例**。


## 函数描述符



函数式接口的抽象方法的签名基本上就是Lambda表达式的签名。我们将这种抽象方法叫作函数描述符。

例如，Runnable接口可以看作一个什么也不接受什么也不返回(void)的函数签名，因为它只有一个叫作run的抽象方法，这个方法什么也不接受，什么也不返回(void)。

() -> void代表了参数列表为空，且返回void的函数。 



```java
public void process(Runnable r) {
        r.run();
    }
```



```java
    process(() ->System.out.println("This is awesome!!”));
```

函数签名相匹配，所以这个是可以正确执行的。


## @FunctionalInterface


@FunctionalInterface**这个标注用于表示该接口会设计成一个函数式接口**。

请注意，@FunctionalInterface不是必需的，但对于为此设计的接口而言，使用它是比较好的做法。它就像是@Override标注，表示方法被重写了。

## 来一个实践
看看在实践中如何利用Lambda和行为参数化来让代码更为灵活，更为简洁。

1.记得行为参数化

2.使用函数式接口来传递行为

3.执行一个行为

4.传递ambda
