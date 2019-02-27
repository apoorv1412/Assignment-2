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
            d.addFlight(5);

        for (int i = 0; i < 5; ++i)
            d.addPassenger();

//        for (Flight f : d.getFlightList()) {
//            for (int a : )
//        }

        Random r = new Random();

        int numOfTransactions = 10;
        ArrayList< int[] > transactions = new ArrayList< int[] >();
        ArrayList< int[] > transactions2 = new ArrayList< int[] >();

        for (int i = 0; i < numOfTransactions; ++i) {
            int type = r.nextInt(5);
            int f1 = r.nextInt(5);
            int f2 = r.nextInt(5);
            while (f2 == f1)
                f2 = r.nextInt(5);
            int pass = r.nextInt(5);
            transactions.add(new int[]{type, f1, f2, pass});
        }

//        transactions2.add(new int[]{0,0,0,0});
//        transactions2.add(new int[]{4,0,1,1 });

        transactions2.add(new int[]{0,1,0,0});
        transactions2.add(new int[]{0,0,0,1});
        transactions2.add(new int[]{1,1,0,0});
        transactions2.add(new int[]{4,0,1,1});
        transactions2.add(new int[]{2,1,0,1});
        transactions2.add(new int[]{3,1,0,0});


        for (int[] a : transactions)
            System.out.println(a[0] + " " + a[1] + " " + a[2] + " " + a[3]);

        concurrencyManager manager = new concurrencyManager(d,transactions2);
        manager.serialImplement();



    }
}
