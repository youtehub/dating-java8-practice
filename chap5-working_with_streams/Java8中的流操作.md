## Java8中的流操作

原创 翎野君  [翎驿](http://www.cnblogs.com/lingyejun) *2020-01-25*

**翎野君**/文





本次我们会使用到很多的流操作，如筛选、切片、映射、查找、匹配和归约，这些操作可以让我们能快速完成复杂的数据查询。



## 01.筛选和切片



### 用谓词筛选

Streams接口支持filter方法。该操作会接受一个谓词(一个返回 boolean的函数)作为参数，并返回一个包括所有符合谓词的元素的流。


```java
List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(toList());
```

![](https://img-blog.csdnimg.cn/20210216181833823.png)


### 筛选各异的元素

流支持一个叫作distinct的方法，它会返回一个元素各异的流。例如，以下代码会筛选出列表中所有的偶数，并确保没有重复。



```java
List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
```

![图片](https://img-blog.csdnimg.cn/img_convert/c669dce8474451d85ce90fc05dff56e4.png)

### 截短流

流支持limit(n)方法，该方法会返回一个不超过给定长度的流。所需的长度作为参数传递给limit。如果流是有序的，则最多会返回前n个元素。

请注意limit也可以用在无序流上，比如源是一个Set。这种情况下，limit的结果不会以任何顺序排列。

比如，你可以建立一个List，选出热量超过300卡路里的头三道 :



```java
List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(toList());
```

![图片](https://img-blog.csdnimg.cn/img_convert/df3068b705b418531bb1230024b4bc51.png)

图中展示了filter和limit的组合。你可以看到，该方法只选出了符合谓词的头三个元素，然后就立即返回了结果。



### 跳过元素

流还支持skip(n)方法，返回一个扔掉了前n个元素的流。如果流中元素不足n个，则返回一个空流。请注意，limit(n)和skip(n)是互斥的!例如，下面的代码将跳过超过300卡路里的头两道菜，并返回剩下的。



```java
    List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(toList());
```

## 映射



映射：**对流中每一个元素应用函数**

流支持map方法，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素。

例如，下面的代码把方法引用Dish::getName传给了map方法， 来提取流中菜品的名称:

 

```java
List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());
```

### 两个题目 

给定一个单词列表，你想要返回另一个列表，显示每个单词中有几个字母。怎么做呢?

 

```java
List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
```

现在让我们回到提取菜名的例子。如果你要找出每道菜的名称有多长，怎么做?你可以像下面这样，再链接上一个map:



```java
List<Integer> dishNameLengths = menu.stream().map(Dish::getName).map(String::length).collect(toList());
```

### 流的扁平化

让我们拓展一下: 对于一张单词表，如何返回一张列表，列出里面各不相同的字符呢?例如，给定单词列表["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"]。你可能会认为这很容易，你可以把每个单词映射成一张字符表，然后调用distinct来过过滤重复的字符。

你可能会这样写:



```java
words.stream().map(word -> word.split("")).distinct().collect(toList());
```

这个方法的问题在于，传递给map方法的Lambda为每个单词返回了一个String[](String 列表)。因此，map返回的流实际上是Stream<String[]>类型的。你真正想要的是用 Stream<String>来表示一个字符流。

![图片](https://img-blog.csdnimg.cn/img_convert/fb008c18205cf54e5839d041727ea5d2.png)



用map和Arrays.stream()，首先，你需要一个字符流，而不是数组流。有一个叫作Arrays.stream()的方法可以接受一个数组并产生一个流，例如:



```java
String[] arrayOfWords = {"Goodbye", "World"}; 
Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
```

把它用在前面的那个流水线里，看看会发生什么:



```java
words.stream().map(word -> word.split(“")).map(Arrays::stream).distinct().collect(toList());
```

当前的解决方案仍然搞不定!这是因为，你现在得到的是一个流的列表(更准确地说是 Stream<String>)先是把每个单词转换成一个字母数组，然后把每个数组变成了一个独立的流。



用flatMap 你可以像下面这样使用flatMap来解决这个问题:

 

```
List<String> uniqueCharacters =words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
```

使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流。

即，flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流。

![图片](https://img-blog.csdnimg.cn/img_convert/cd90d24e24dc49edf2b215067d604787.png)




## 03.查找和匹配



查看数据集中的某些元素是否匹配一个给定的属性。

Stream API通过allMatch、anyMatch、noneMatch、findFirst和findAny方法来完成这些工作。



### 检查谓词是否至少匹配一个元素

anyMatch方法可以回答“流中是否有一个元素能匹配给定的谓词”。比如，你可以用它来看看菜单里面是否有素菜可选择:

 

```java
if(menu.stream().anyMatch(Dish::isVegetarian)){
    System.out.println("The menu is (somewhat) vegetarian friendly!!”);
}
```

anyMatch方法返回一个boolean，因此是一个终端操作。

### 检查谓词是否匹配所有元素 

allMatch方法的工作原理和anyMatch类似，但它会看看流中的元素是否都能匹配给定的谓词。

比如，你可以用它来看是否所有菜品的热量都低于1000卡路里):

 

```java
boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
```

### 没有任何元素与给定的谓词匹配

和allMatch相对的是noneMatch。它可以确保流中没有任何元素与给定的谓词匹配。

比如， 你可以用noneMatch重写前面的例子:



```java
boolean isHealthy = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
```

### 返回当前流中的任意元素

findAny方法将返回当前流中的任意元素。它可以与其他流操作结合使用。比如，你可能想找到一道素菜。

你可以结合使用filter和findAny方法来实现这个查询:



```java
Optional<Dish> dish =menu.stream().filter(Dish::isVegetarian).findAny();
```

有些流有一个出现顺序(encounter order)来指定流中项目出现的逻辑顺序(比如由List或排序好的数据列生成的流)。对于这种流，你可能想要找到第一个元素。为此有一个findFirst 方法，它的工作方式类似于findany。例如，给定一个数字列表，下面的代码能找出第一个平方 能被3整除的数:

 

```java
List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> firstSquareDivisibleByThree =
someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
```



## 04.归约



有一些查询操作需要将流中所有元素反复结合起来，得到一个值，比如一个Integer。

这样的查询可以被归类为归约操作(将流归约成一个值)。

用函数式编程语言的术语来说，这称为折叠(fold)，因为你可以将这个操作看成把一张长长的值(你的流)反复折叠成一个小方块，而这就是折叠操作的结果。



### 元素求和

在我们研究如何使用reduce方法之前，先来看看如何使用for-each循环来对数字列表中的元素求和。

numbers中的每个元素都用加法运算符反复迭代来得到结果。通过反复使用加法，你把一个数字列表归约成了一个数字。



```java
int sum = 0;
for (int x : numbers){
  sum += x;
}```

这段代码中有两个参数:

- 总和变量的初始值，在这里是0;
- 将列表中所有元素结合在一起的操作，在这里是+。

要是还能把所有的数字相乘，而不必去复制粘贴这段代码，这岂不是很好?这正是reduce操作的用武之地，它对这种重复应用的模式做了抽象。

你可以像下面这样对流中所有的元素求和:



```java
int sum = numbers.stream().reduce(0, (a, b) -> a + b);
```

![图片](https://img-blog.csdnimg.cn/img_convert/ceed759e22a375cd83275f5d19f4fcaf.png)

reduce接受两个参数:

- 一个初始值，这里是0;
- 一个BinaryOperator<T>来将两个元素结合起来产生一个新值，这里我们用的是lambda (a, b) -> a + b。

你也很容易把所有的元素相乘，只需要将另一个Lambda:(a, b) -> a * b传递给reduce操作就可以了:



```
int product = numbers.stream().reduce(1, (a, b) -> a * b);
```

Lambda反复结合每个元素，直到流被归约成一个值。

***
***

### 最大值和最小值

来看一下如何利用刚才学到的reduce 来计算流中最大或最小的元素。正如你前面看到的，reduce接受两个参数:

- 一个初始值
- 一个Lambda来把两个流元素结合起来并产生一个新值

Lambda是一步步用加法运算符应用到流中每个元素上的。因此，你需要一个给定两个元素能够返回最大值的Lambda。

reduce操作会考虑新值和流中下一个元素，并产生一个新的最大值，直到整个流消耗完!

你可以像下面这样使用reduce来计算流中的最大值。



```java
Optional<Integer> max = numbers.stream().reduce(Integer::max);
```

![图片](https://img-blog.csdnimg.cn/img_convert/db77cdecfbdec0e379ca32c32c9dfea5.png)



![图片](https://img-blog.csdnimg.cn/img_convert/4d28d3101de3e868598903d58252eb04.png)

## 05.总结



- Streams API可以表达复杂的数据处理查询。
- 可以使用filter、distinct、skip和limit对流做筛选和切片。
- 可以使用map和flatMap提取或转换流中的元素。
- 可以使用findFirst和findAny方法查找流中的元素。你可以用allMatch、noneMatch和anyMatch方法让流匹配给定的谓词。
- 可以利用reduce方法将流中所有的元素迭代合并成一个结果，例如求和或查找最大元素。
