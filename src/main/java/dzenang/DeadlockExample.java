package dzenang;

public class DeadlockExample {
  static class Friend {
    private final String name;

    public Friend(String name) {
      this.name = name;
    }
    public synchronized void bow(Friend bower) {
      System.out.printf("%s: %s has bowed to me!%n", this.name, bower.name);
      bower.bowBack(this);
    }
    public synchronized void bowBack(Friend bower) {
      System.out.printf("%s: %s has bowed back to me!%n", this.name, bower.name);
    }
  }

  public static void main(String[] args) {
    Friend friend1 = new Friend("Friend1");
    Friend friend2 = new Friend("Friend2");

    new Thread(new Runnable() { public void run() { friend1.bow(friend2);}}).start();
    new Thread(new Runnable() { public void run() { friend2.bow(friend1);}}).start();
  }
}
