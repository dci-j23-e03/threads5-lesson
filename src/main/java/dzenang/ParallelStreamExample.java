package dzenang;

import java.util.Arrays;
import java.util.List;

public class ParallelStreamExample {

  public static void main(String[] args) {

    List<Integer> numberList = Arrays.asList(1, 2, 3, 4);

    // streams can run in parallel if we specify that explicitly
    // by default, streams are sequential
    // don't worry if you don't understand streams completely now, we will cover them later
    numberList.stream().parallel().forEach(number ->
        System.out.println(number + " " + Thread.currentThread().getName())
    );
  }
}
