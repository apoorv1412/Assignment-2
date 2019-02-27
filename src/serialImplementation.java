import java.util.ArrayList;

public class serialImplementation implements Runnable{

    ArrayList< int[] > transactions;
    Database d;
    int cnt = 0;

    serialImplementation() {
    }

    void setD(Database d) {
        this.d = d;
    }

    void setTransactions(ArrayList< int[] > transactions) {
        this.transactions = transactions;
    }

    @Override
    public void run() {
        for (int[] a : transactions) {
            System.out.println("cnt " + cnt);
            int type = a[0];
            Flight f1 = d.getFlightList().get(a[1]);
            Flight f2 = d.getFlightList().get(a[2]);
            synchronized (d) {
                System.out.println("type " + type);
                if (type == 0)
                    d.Reserve(f1,a[3]);
                else if (type == 1)
                    d.Cancel(f1,a[3]);
                else if (type == 2)
                    d.Total_Reservations();
                else if (type == 3) {
                    System.out.println("Passenger " + a[3]);
                    d.My_Flights(a[3]);
                }
                else
                    d.Transfer(f1,f2,a[3]);
            }
            ++cnt;
        }
    }
}
