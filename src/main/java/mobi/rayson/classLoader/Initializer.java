package mobi.rayson.classLoader;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-09
 *  Time: 6:38 PM
 *  Description: 类的初始化，针对的是整个类
 **/
public class Initializer {
  public static void main(String[] args) {
    System.out.println(Subclass.value);
    System.out.println(ConstClass.HELLO_WORLD);
  }
}

class SuperClass {
  // 初始化后，静态代码执行并输出
  static {
    System.out.println("Init Superclass.");
  }

  public static int value = 123; // 静态变量，会被初始化
}

class Subclass extends SuperClass {
  // 子类引用了父类的静态变量，父类需要初始化
  static {
    System.out.println("Init subclass."); // 不会被输出，因为该类未被初始化
  }
}

class ConstClass {
  static {
    System.out.println("Init const."); // 不会被输出, 因为该类不会被初始化
  }

  // 该字段在编译期间就会被放入到常量池，以后对该字段的引用都是对常量池中的引用，与类本身无关
  public static final String HELLO_WORLD  = "hello world";
}