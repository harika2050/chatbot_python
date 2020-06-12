package mypack;
import java.util.*;

/**
 *
 */
public class Payment {
    public Payment() {
    }


    public String pid;
    public String cid;
    public String cardno;
    public String bank;
    public double amount;
    public String cvv;
    public String type;

    public String getPid() {
        return pid;
    }

    public void setPid(String tranId) {
        this.pid = tranId;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   /* public void setDetail() {
        // TODO implement here
    }

    public void getMode() {
        // TODO implement here
    }*/

}