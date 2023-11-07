package lab02;

public class TimingExperiment06 {

  public static void main(String[] args) {
    long secondsToRun = 10;
    long startTime = System.nanoTime();
    long lastTime = startTime;
    int advanceCount = 0;
    int loopCount = 0;
    long currentTime;
    long totalTime = 1000000000 * secondsToRun;
    long elapsedTime = 0;
    while (elapsedTime < totalTime) {
      loopCount++;
      if ((currentTime = System.nanoTime()) != lastTime) {
        lastTime = currentTime;
        elapsedTime = lastTime - startTime;
        advanceCount++;
      }
    }
    double advancesPerSecond = advanceCount / (double) secondsToRun;
    System.out.println("Time advanced " + advancesPerSecond + " times per second.");
    System.out.println("The loop tested the time " + loopCount + " times.");
  }
}

//1 Time advanced 2.38808902E7 times per second.
//The loop tested the time 905547175 times.
//2 Time advanced 2.38515499E7 times per second.
//The loop tested the time 907603110 times.
//3 Time advanced 2.38685743E7 times per second.
//The loop tested the time 912311992 times.
//4 Time advanced 2.38401957E7 times per second.
//The loop tested the time 914569692 times.
// 5 Time advanced 2.38795418E7 times per second.
//The loop tested the time 907061499 times.