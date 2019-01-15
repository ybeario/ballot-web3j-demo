package com.example.demo.model;

import java.math.BigInteger;

/**
 * @author ：Y.Bear
 * @date ：Created in 2019/1/11 15:37
 * @description：新提案
 * @modified By：
 * @version: v1.0$
 */
public class Proposal {
    private int proposalNums;

    private String contractAddress;

    private String creator;

    private BigInteger winnerIndex;

    public Proposal() {
    }

    public Proposal(int proposalNums) {
        this.proposalNums = proposalNums;
    }

    public Proposal(int proposalNums, String contractAddress, String creator) {
        this.proposalNums = proposalNums;
        this.contractAddress = contractAddress;
        this.creator = creator;
    }

    public Proposal(int proposalNums, String contractAddress, String creator, BigInteger winnerIndex) {
        this.proposalNums = proposalNums;
        this.contractAddress = contractAddress;
        this.creator = creator;
        this.winnerIndex = winnerIndex;
    }

    public int getProposalNums() {
        return proposalNums;
    }

    public void setProposalNums(int proposalNums) {
        this.proposalNums = proposalNums;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public BigInteger getWinnerIndex() {
        return winnerIndex;
    }

    public void setWinnerIndex(BigInteger winnerIndex) {
        this.winnerIndex = winnerIndex;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "proposalNums=" + proposalNums +
                ", contractAddress='" + contractAddress + '\'' +
                ", creator='" + creator + '\'' +
                ", winnerIndex='" + winnerIndex + '\'' +
                '}';
    }
}
