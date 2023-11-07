package lab02;

public class TimingExperiment01 {

  public static void main(String[] args) {
    long lastTime = System.currentTimeMillis();
    int advanceCount = 0;

    while (advanceCount < 100) {
      long currentTime = System.currentTimeMillis();
      if (currentTime == lastTime)
        continue;

      System.out.println("Count: " + advanceCount +": Time advanced " + (currentTime - lastTime) + " milliseconds.");
      lastTime = currentTime;
      advanceCount++;

    }
  }
}
//it advanced in 1 millisecond each time except for on the initial count time for several tests