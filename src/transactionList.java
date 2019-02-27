import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class transactionList implements Runnable {

    private ArrayList< int[] > transactions;
    private Database d;
    ReadWriteLock[] locks;

    transactionList(Database d, ReadWriteLock[] locks) {
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
            if (type == 0) {
                Flight f1 = this.d.getFlightList().get(a[1]);
                locks[a[1]].writeLock().lock();
                d.Reserve(f1,a[3]);
                locks[a[1]].writeLock().unlock();
            }
            else if (type == 1) {
                Flight f1 = this.d.getFlightList().get(a[1]);
                locks[a[1]].writeLock().lock();
                d.Cancel(f1,a[3]);
                locks[a[1]].writeLock().unlock();
            }
            else if (type == 2) {
                for (int i = 0; i < 5; ++i)
                    locks[i].readLock().lock();
                d.My_Flights(a[3]);
                for (int i = 4; i >= 0; --i)
                    locks[i].readLock().unlock();

            }
            else if (type == 3) {
                for (int i = 0; i < 5; ++i)
                    locks[i].readLock().lock();
                d.Total_Reservations();
                for (int i = 4; i >= 0; --i)
                    locks[i].readLock().unlock();
            }

            else {
                if (a[1] > a[2]) {
                    locks[a[1]].writeLock().lock();
                    locks[a[2]].writeLock().lock();
                }
                else {
                    locks[a[2]].writeLock().lock();
                    locks[a[1]].writeLock().lock();
                }
                d.Transfer(d.getFlightList().get(a[1]), d.getFlightList().get(a[2]), a[3]);
                if (a[2] > a[1]) {
                    locks[a[1]].writeLock().unlock();
                    locks[a[2]].writeLock().unlock();
                }
                else {
                    locks[a[2]].writeLock().unlock();
                    locks[a[1]].writeLock().unlock();
                }
            }
        }
    }
}
