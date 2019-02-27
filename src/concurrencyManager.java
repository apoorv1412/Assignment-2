import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class concurrencyManager implements Runnable {

    HashMap<Flight, Queue<ArrayList<Integer>>> lockTable;
    Database data;
    HashMap<Flight, Integer> lockType;
    ArrayList< int[] > transactions;
    int tasknum = 0;

    concurrencyManager(Database d, ArrayList< int[] > transactions) {
        this.data = d;
        for (Flight f : this.data.getFlightList()) {
            lockTable.put(f, new LinkedList<>());
            lockType.put(f, 0);
        }
        this.transactions = transactions;
    }

    @Override
    public void run() {
        int[] tuple = transactions.get(tasknum);

    }
}
