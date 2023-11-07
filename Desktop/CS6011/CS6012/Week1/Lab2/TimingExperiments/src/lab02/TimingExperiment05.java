package lab02;

public class TimingExperiment05 {

  public static void main(String[] args) {
    long secondsToRun = 10;
    long startTime = System.nanoTime();
    long lastTime = startTime;
    int advanceCount = 0;
    int loopCount = 0;
    while (lastTime - startTime < 1000000000 * secondsToRun) {
      loopCount++;
      long currentTime = System.nanoTime();
      if (currentTime == lastTime)
        continue;
      lastTime = currentTime;
      advanceCount++;
    }
    double advancesPerSecond = advanceCount / (double) secondsToRun;
    System.out.println("Time advanced " + advancesPerSecond + " times per second.");
    System.out.println("The loop tested the time " + loopCount + " times.");
  }
}
//the Loop occurs many times for a milisecond
//  What is the difference between the nanosecond timer granularity,
//        and the loop run time granularity?
//        It appears that the nanosecond timer is fairly accurate,
//        but it is still unclear how much time the loop takes vs.
//        how much time the call to System.nanoTime() takes.

//1 Time advanced 2.39139496E7 times per second.
//The loop tested the time 993389145 times.
//2 Time advanced 2.38218108E7 times per second.
//The loop tested the time 966898961 times.
//3 Time advanced 2.37961315E7 times per second.
//The loop tested the time 998485609 times.
//4 Time advanced 2.38463664E7 times per second.
//The loop tested the time 972058445 times.
//5 Time advanced 2.38026112E7 times per second.
//The loop tested the time 981885364 times.