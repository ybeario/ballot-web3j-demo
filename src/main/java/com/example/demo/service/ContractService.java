package com.example.demo.service;

import com.example.demo.model.Ballot;
import com.example.demo.model.Proposal;
import com.example.demo.model.TransferResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

/**
 * @author ：Y.Bear
 * @date ：Created in 2019/1/15 10:11
 * @description：包含部署合约执行合约方法
 * @modified By：
 * @version: 1.0$
 */
@Service
public class ContractService {
    private static final String PASSWORD = "ybear007";
    Web3j web3j = Web3j.build(new HttpService());
    GasProviderImpl gasProvider = new GasProviderImpl();
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Credentials credentials;
    private Credentials toCredentials;
    private String file;
    private Proposal proposal;


    @PostConstruct
    public void init() throws Exception {
        //创建或读取账户
        file = WalletUtils.generateLightNewWalletFile(PASSWORD, null);
        credentials = WalletUtils.loadCredentials(PASSWORD, file);
        logger.info("Credentials created: file={}, address={}", file, credentials.getAddress());
        //通过获取coinbase向目标账户转账（确保有Ether用于合约的部署与执行）
        EthCoinbase coinbase = web3j.ethCoinbase().send();
        EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(coinbase.getAddress(),
                DefaultBlockParameterName.LATEST).send();
        Transaction transaction = Transaction.createEtherTransaction(coinbase.getAddress(),
                transactionCount.getTransactionCount(), BigInteger.valueOf(20_000_000_000L),
                BigInteger.valueOf(21_000), credentials.getAddress(), BigInteger.valueOf(900_000_000_000_000_000L));
        web3j.ethSendTransaction(transaction).send();
        EthGetBalance balance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        logger.info(credentials.getAddress() + "Balance: {}", balance.getBalance().longValue());
    }

    /**
     * 通过rawTransaction完成转账   tip: rawTransaction与Transaction的区别
     *
     * @param amount
     * @return
     */
    public TransferResult transfer(BigInteger amount) throws Exception {
        String toFile = WalletUtils.generateLightNewWalletFile(PASSWORD, null);
        toCredentials = WalletUtils.loadCredentials(PASSWORD, toFile);
        logger.info("toCredentials created: file={}, address={}", toFile, toCredentials.getAddress());
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                nonce, gasProvider.getGasPrice(), gasProvider.getGasLimit(), toCredentials.getAddress(),
                BigInteger.valueOf(500_000L));
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        String transactionHash = ethSendTransaction.getTransactionHash();
        logger.info("transactionHash: " + transactionHash);
        EthGetBalance balance =
                web3j.ethGetBalance(toCredentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        logger.info(toCredentials.getAddress() + "Balance: {}", balance.getBalance().longValue());
        EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();
        if (transactionReceipt.getTransactionReceipt().isPresent()) {
            BigInteger cumulativeGasUsed = transactionReceipt.getTransactionReceipt().get().getCumulativeGasUsed();
            logger.info("Gas:{}", cumulativeGasUsed.multiply(web3j.ethGasPrice().sendAsync().get().getGasPrice()));
        }
        return new TransferResult(amount, credentials.getAddress(), toCredentials.getAddress(), transactionHash);
    }

    public Proposal createBallotContract(Proposal newProposal) throws Exception {
        proposal = newProposal;
        credentials = WalletUtils.loadCredentials(PASSWORD, file);
        logger.info("Credentials loaded: file={}, address={}", file, credentials.getAddress());
        Ballot ballot = Ballot.deploy(web3j, credentials, gasProvider,
                BigInteger.valueOf(proposal.getProposalNums())).sendAsync().get();
        proposal.setContractAddress(ballot.getContractAddress());
        proposal.setCreator(credentials.getAddress());
        Optional<TransactionReceipt> tr = ballot.getTransactionReceipt();
        LogReceipt(tr);
        logger.info("Ballot: {}  -->  {}", ballot.getContractAddress(), ballot.isValid());
        return proposal;
    }

    public TransactionReceipt vote(int index) throws Exception {
        Ballot ballot = loadBallot();
        TransactionReceipt voteReceipt = null;
        if (ballot.isValid()) {
            voteReceipt = ballot.vote(BigInteger.valueOf(index)).sendAsync().get();
            logger.info("voteReceipt: {}", voteReceipt);
        }
        return voteReceipt;
    }

    public Proposal getWinningProposal() throws Exception {
        Ballot ballot = loadBallot();
        BigInteger proposalIndex = null;
        if (ballot.isValid()) {
            proposalIndex = ballot.winningProposal().sendAsync().get();
            logger.info("proposalIndex: {}", proposalIndex);
        }
        proposal.setWinnerIndex(proposalIndex);
        return proposal;
    }

    public  void delegate(String toAddress){
        //todo
    }

    public void giveRightToVote(String voter){
        //todo
    }

    public Ballot loadBallot() throws IOException {
        logger.info("contract address:{}", proposal.getContractAddress());
        Ballot ballot = Ballot.load(proposal.getContractAddress(), web3j, credentials, gasProvider);
        logger.info("Ballot Contract {} loaded: {}", ballot.getContractAddress(), ballot.isValid());
        return ballot;
    }

    private void LogReceipt(Optional<TransactionReceipt> tr) {
        if (tr.isPresent()) {
            logger.info("Transaction receipt: transactionHash: {}, blockHash {}, CumulativeGasUsed: {}, GasUsed: {}, " +
                            "BlockNumber: {}",
                    tr.get().getTransactionHash(), tr.get().getBlockHash(), tr.get().getCumulativeGasUsed(),
                    tr.get().getGasUsed(), tr.get().getBlockNumber());
        }
    }
}
