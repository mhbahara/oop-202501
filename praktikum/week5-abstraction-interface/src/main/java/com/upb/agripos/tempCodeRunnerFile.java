import model.kontrak.Receiptable;
import model.pembayaran.Cash;
import model.pembayaran.EWallet;
import model.pembayaran.Pembayaran;
import model.pembayaran.TransferBank;
import util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        Pembayaran cash = new Cash("INV-001", 87000, 100000);
        Pembayaran ew = new EWallet("INV-002", 175000, "bungamaura@ewallet", "123456");
        Pembayaran transfer = new TransferBank("INV-003", 150000, "BCA", true);


        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());
        System.out.println(((Receiptable) transfer).cetakStruk());
    
        CreditBy.print("Bunga Maura Aulya", "240320562");
    }
}