package com.example.demo.controller;

import com.example.demo.model.Proposal;
import com.example.demo.model.ProposalCommand;
import com.example.demo.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author ：Y.Bear
 * @date ：Created in 2019/1/15 12:09
 * @description：controller
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/contract")
public class ContractController {

    final ContractService service;

    @Autowired
    public ContractController(ContractService service) {
        this.service = service;
    }

    @PostMapping("/deploy")
    public Proposal createBallotContract(@RequestBody ProposalCommand command) throws Exception {
        Proposal newProposal = new Proposal(command.getProposalNums());
        return service.createBallotContract(newProposal);
    }


    @PostMapping("/vote")
    public TransactionReceipt vote(int index) {
        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = service.vote(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionReceipt;
    }

    @GetMapping("/winner")
    public Proposal getWinningProposal() {
        Proposal winningProposal=null;
        try {
            winningProposal= service.getWinningProposal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return winningProposal;
    }


}
