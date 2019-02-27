import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        Database d = new Database();
        for (int i = 0; i < 5; ++i)
            d.addFlight(10 + i);

        for (int i = 0; i < 50; ++i)
            d.addPassenger();

//        for (Flight f : d.getFlightList()) {
//            for (int a : )
//        }

        Random r = new Random();

        int numOfTransactions = 10;
        ArrayList< int[] > transactions = new ArrayList< int[] >();

        for (int i = 0; i < numOfTransactions; ++i) {
            int type = r.nextInt(5);
            int f1 = r.nextInt(5);
            int f2 = r.nextInt(5);
            while (f2 == f1)
                f2 = r.nextInt(5);
            int pass = r.nextInt(50);
            transactions.add(new int[]{type, f1, f2, pass});
        }

        for (int[] a : transactions)
            System.out.println(a[0] + " " + a[1] + " " + a[2] + " " + a[3]);

        int numOfThreads = 2;
        ExecutorService exec = Executors.newFixedThreadPool(numOfThreads);

        ArrayList< int[] > x = new ArrayList< int[] >();
        int cnt = numOfTransactions/numOfThreads, ptr = 0;
        serialImplementation[] y = new serialImplementation[cnt];

        for (int i = 0; i < cnt; ++i)
            y[i] = new serialImplementation();

        for (int i = 0; i < numOfTransactions; ++i) {
            if (i == cnt) {
                y[ptr].setD(d);
                y[ptr].setTransactions(x);
                x = new ArrayList<int[]>();
                ++ptr;
            }
            else
                x.add(transactions.get(i));
        }

        for (int i = 0; i < cnt-1; ++i)
            exec.execute(y[i]);

        if (!exec.isTerminated()) {
            exec.shutdown();
            exec.awaitTermination(5L, TimeUnit.SECONDS);
        }

    }
}
