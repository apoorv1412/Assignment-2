import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class serialImplementation implements Runnable {

    private ArrayList< int[] > transactions;
    private Database d;
    ReadWriteLock[] locks;

    serialImplementation(Database d, ReadWriteLock[] locks) {
        this.d = d;
        this.transactions = transactions;
        this.locks = locks;
    }

    ArrayList<int[]> getTransactions() {
        return this.transactions;
    }

    void setTransactions(ArrayList< int[] > transactions) {
        this.transactions = transactions;
    }

    @Override
    public void run() {
        for (int[] a : transactions) {
            int type = a[0];
            Flight f1 = d.getFlightList().get(a[1]);
            Flight f2 = d.getFlightList().get(a[2]);
            locks[0].writeLock().lock();
            if (type == 0)
                d.Reserve(f1,a[3]);
            else if (type == 1)
                d.Cancel(f1,a[3]);
            else if (type == 2)
                d.My_Flights(a[3]);
            else if (type == 3)
                d.Total_Reservations();
            else
                d.Transfer(f1,f2,a[3]);
            locks[0].writeLock().unlock();
        }
    }
}
