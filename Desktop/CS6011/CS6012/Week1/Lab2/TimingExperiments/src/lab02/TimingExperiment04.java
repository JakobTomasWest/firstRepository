package lab02;

public class TimingExperiment04 {

  public static void main(String[] args) {
    long lastTime = System.nanoTime();
    int advanceCount = 0;
    long[] advanceAmounts = new long[100];
    while (advanceCount < 100) {
      long currentTime = System.nanoTime();
      if (currentTime == lastTime)
        continue;
      advanceAmounts[advanceCount++] = currentTime - lastTime;
      lastTime = currentTime;
    }
    for (int i = 0; i < 100; i++)
      System.out.println("Time advanced " + advanceAmounts[i] + " nanoseconds.");
  }
}

//so since the computer is to quick sometimes the current time will be = the last time
//because of this we wont add a count to the array so our time to advance can be about double some times
