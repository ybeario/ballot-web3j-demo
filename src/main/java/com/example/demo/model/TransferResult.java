package com.example.demo.model;

import java.math.BigInteger;

public class TransferResult {

    private BigInteger amount;
    private String fromAddress;
    private String toAddress;
    private String txHash;

    public TransferResult(BigInteger amount, String fromAddress, String toAddress, String txHash) {
        this.amount = amount;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.txHash = txHash;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    @Override
    public String toString() {
        return "TransferResult{" +
                "amount=" + amount +
                ", fromAddress='" + fromAddress + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", txHash='" + txHash + '\'' +
                '}';
    }
}
