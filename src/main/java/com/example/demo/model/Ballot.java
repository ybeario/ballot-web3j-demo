package com.example.demo.model;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.1.
 */
public class Ballot extends Contract {
    public static final String FUNC_DELEGATE = "delegate";
    public static final String FUNC_WINNINGPROPOSAL = "winningProposal";
    public static final String FUNC_GIVERIGHTTOVOTE = "giveRightToVote";
    public static final String FUNC_VOTE = "vote";
    private static final String BINARY =
            "608060405234801561001057600080fd5b506040516020806104998339810180604052602081101561003057600080fd5b505160008054600160a060020a0319163317808255600160a060020a031681526001602081905260409091205560ff811661006c600282610073565b50506100bd565b8154818355818111156100975760008381526020902061009791810190830161009c565b505050565b6100ba91905b808211156100b657600081556001016100a2565b5090565b90565b6103cd806100cc6000396000f3fe608060405234801561001057600080fd5b5060043610610068577c010000000000000000000000000000000000000000000000000000000060003504635c19a95c811461006d578063609ff1bd146100955780639e7b8d61146100b3578063b3f98adc146100d9575b600080fd5b6100936004803603602081101561008357600080fd5b5035600160a060020a03166100f9565b005b61009d610259565b6040805160ff9092168252519081900360200190f35b610093600480360360208110156100c957600080fd5b5035600160a060020a03166102c4565b610093600480360360208110156100ef57600080fd5b503560ff16610321565b3360009081526001602081905260409091209081015460ff161561011d5750610256565b5b600160a060020a038281166000908152600160208190526040909120015462010000900416158015906101755750600160a060020a0382811660009081526001602081905260409091200154620100009004163314155b156101a757600160a060020a03918216600090815260016020819052604090912001546201000090049091169061011e565b600160a060020a0382163314156101be5750610256565b6001818101805460ff1916821775ffffffffffffffffffffffffffffffffffffffff0000191662010000600160a060020a0386169081029190911790915560009081526020829052604090209081015460ff161561024b5781546001820154600280549091610100900460ff1690811061023457fe5b600091825260209091200180549091019055610253565b815481540181555b50505b50565b600080805b60025460ff821610156102bf578160028260ff1681548110151561027e57fe5b906000526020600020016000015411156102b7576002805460ff83169081106102a357fe5b906000526020600020016000015491508092505b60010161025e565b505090565b600054600160a060020a0316331415806102fa5750600160a060020a0381166000908152600160208190526040909120015460ff165b1561030457610256565b600160a060020a0316600090815260016020819052604090912055565b3360009081526001602081905260409091209081015460ff168061034a575060025460ff831610155b156103555750610256565b6001818101805460ff191690911761ff00191661010060ff85169081029190911790915581546002805491929091811061038b57fe5b600091825260209091200180549091019055505056fea165627a7a7230582094ebd872b7f6920768998c375bfc9b04bd716d70a9b753c9d0bd6f2cfdaa77140029";

    @Deprecated
    protected Ballot(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
                     BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Ballot(String contractAddress, Web3j web3j, Credentials credentials,
                     ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice,
                     BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                     ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    @Deprecated
    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
                              BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                              BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials,
                              ContractGasProvider contractGasProvider) {
        return new Ballot(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                              ContractGasProvider contractGasProvider) {
        return new Ballot(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, Credentials credentials,
                                            ContractGasProvider contractGasProvider, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint8(_numProposals)));
        return deployRemoteCall(Ballot.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, TransactionManager transactionManager,
                                            ContractGasProvider contractGasProvider, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint8(_numProposals)));
        return deployRemoteCall(Ballot.class, web3j, transactionManager, contractGasProvider, BINARY,
                encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Ballot> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
                                            BigInteger gasLimit, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint8(_numProposals)));
        return deployRemoteCall(Ballot.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Ballot> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice,
                                            BigInteger gasLimit, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint8(_numProposals)));
        return deployRemoteCall(Ballot.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public RemoteCall<TransactionReceipt> delegate(String to) {
        final Function function = new Function(
                FUNC_DELEGATE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> winningProposal() {
        final Function function = new Function(FUNC_WINNINGPROPOSAL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> giveRightToVote(String toVoter) {
        final Function function = new Function(
                FUNC_GIVERIGHTTOVOTE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(toVoter)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> vote(BigInteger toProposal) {
        final Function function = new Function(
                FUNC_VOTE,
                Arrays.<Type>asList(new Uint8(toProposal)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }
}
