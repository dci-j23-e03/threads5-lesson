package dzenang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedListExample {

  public static void main(String[] args) {
    // normal list
    List<Integer> normalList = new ArrayList<>();
    // synchronized wrapper around normal list
    List<Integer> synchronizedList = Collections.synchronizedList(normalList);

    // multithreaded context, we need to use synchronized collection there
    Runnable listTask = new Runnable() {
      @Override
      public void run() {
        synchronizedList.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
      }
    };

    ExecutorService executorService = Executors.newFixedThreadPool(5);
    executorService.submit(listTask);
    executorService.submit(listTask);
    try {
      executorService.awaitTermination(1, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.out.println("Thread interrupted: " + e.getMessage());
    }
    executorService.shutdown();
    // end of multithreaded context, we can continue using normal list

    // main thread, singlethreaded context
    System.out.println("Number of elements added to the synchronizedList: " + normalList.size());
    printList(normalList);
  }

  private static void printList(List<Integer> list) {
    for (Integer item : list) {
      System.out.println(item);
    }
  }
}