
package rw.gov.Model;

import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author andre
 */


public class Transaction {
    private int transactionId;
    private String type; // deposit, withdrawal, transfer
    private double amount;
    private Date timestamp;

    public Transaction() {
    }

    public Transaction(int transactionId, String type, double amount, Date timestamp) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

  
}
