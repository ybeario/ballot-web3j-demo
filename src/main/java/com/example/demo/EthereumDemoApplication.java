package com.example.demo;

import com.example.demo.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableSwagger2
public class EthereumDemoApplication {
    private Logger logger = LoggerFactory.getLogger(getClass());
    Web3j web3j = Web3j.build(new HttpService());
    @Autowired
    ContractService service;

    public static void main(String[] args) {
        SpringApplication.run(EthereumDemoApplication.class, args);
    }

    @PostConstruct
    public void listen() {
        web3j.transactionFlowable().subscribe(tx -> {
        logger.info("BlockHash: {}, BlockNumber:{},Input: {}, Creates: {}, Gas: {}", tx.getBlockHash(),
                tx.getBlockNumber(),
                tx.getInput(),tx.getCreates(),tx.getGas());
        });
    }
}



