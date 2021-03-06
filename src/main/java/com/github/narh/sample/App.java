/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.narh.sample;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.narh.sample.exec.AppExec;

public class App {

  public String getGreeting() {
    return "Hello world.";
  }

  public static void main(String... args) throws Exception {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

    Arrays.asList(args).stream().parallel().forEach(Try(exec -> {
      ((AppExec) context.getBean(exec)).exec();
    }, (error, exec) -> error.printStackTrace()));

    context.close();
  }

  public static  <T> Consumer<T> Try(ThrowableConsumer<T> onTry, BiConsumer<Exception, T> onCatch) {
    return x -> {
      try {
        onTry.accept(x);
      }
      catch (Exception t) {
        onCatch.accept(t, x);
      }
    };
  }
}
