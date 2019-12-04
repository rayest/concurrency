package mobi.rayson.thread;

public class WindowTicketSaleApp {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new WindowTicketSale(), "w1");
        Thread t2 = new Thread(new WindowTicketSale(), "w2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("售罄 --> 关窗 --> 下班");
    }
}
