package lab02;

public class SquareRootTiming {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        double result = 1;
        for (int i = 1; i <=10; i++){

            // uhhhh i tried for 10 minutes to think of how but this would take me all day probably
            long stopTime = System.nanoTime();
            System.out.println("It takes exactly " + (stopTime - startTime) + " nanoseconds to compute the square roots of the "
                    + " numbers 1..10.");
        }

    }
}

//sqrt 1 = 1 sqrt = 1.4.1
