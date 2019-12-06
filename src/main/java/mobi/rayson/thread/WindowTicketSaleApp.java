package mobi.rayson.thread;

public class WindowTicketSaleApp {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new WindowTicketSale(), "w1");
        Thread t2 = new Thread(new WindowTicketSale(), "w2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("t1 is alive: " + t1.isAlive());
        System.out.println("t2 is alive: " + t2.isAlive());
        System.out.println("售罄 --> 关窗 --> 下班");
    }
}
