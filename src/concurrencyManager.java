import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class concurrencyManager {

    Database data;
    ArrayList< int[] > transactions;
    ReadWriteLock[] locks;

    concurrencyManager(Database d, ArrayList< int[] > transactions) {
        this.data = d;
        this.transactions = transactions;
        locks = new ReentrantReadWriteLock[5];
        for (int i = 0; i < 5; ++i)
            locks[i] = new ReentrantReadWriteLock();
    }

    void runSimulation() throws InterruptedException {

        int numThreads = 5;
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);

        int numTransactions = transactions.size();
        int cnt = numTransactions/numThreads;
        transactionList[] transactionsForThread = new transactionList[numThreads];

        for (int i = 0; i < numThreads; ++i)
            transactionsForThread[i] = new transactionList(data,locks);

        ArrayList<ArrayList< int[] >> x = new ArrayList<>();
        for (int i = 0; i < numThreads; ++i) {
            x.add(new ArrayList<>());
        }

        for (int i = 0; i < numTransactions; ++i) {
            x.get(i%numThreads).add(transactions.get(i));
        }

        for (int i = 0; i < numThreads; ++i) {
            transactionsForThread[i].setTransactions(x.get(i));
        }

        for (int i = 0; i < numThreads; ++i) {
            exec.execute(transactionsForThread[i]);
        }

        if (!exec.isTerminated()) {
            exec.shutdown();
            exec.awaitTermination(5L, TimeUnit.SECONDS);
        }
    }

    void serialImplement() throws InterruptedException {

        int numThreads = 1;
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);

        int numTransactions = transactions.size();
        int cnt = numTransactions/numThreads;
        serialImplementation[] transactionsForThread = new serialImplementation[numThreads];

        for (int i = 0; i < numThreads; ++i)
            transactionsForThread[i] = new serialImplementation(data,locks);

        ArrayList<ArrayList< int[] >> x = new ArrayList<>();
        for (int i = 0; i < numThreads; ++i) {
            x.add(new ArrayList<>());
        }

        for (int i = 0; i < numTransactions; ++i) {
            x.get(i%numThreads).add(transactions.get(i));
        }

        for (int i = 0; i < numThreads; ++i) {
            transactionsForThread[i].setTransactions(x.get(i));
        }

        for (int i = 0; i < numThreads; ++i) {
            exec.execute(transactionsForThread[i]);
        }

        if (!exec.isTerminated()) {
            exec.shutdown();
            exec.awaitTermination(5L, TimeUnit.SECONDS);
        }
    }
}


