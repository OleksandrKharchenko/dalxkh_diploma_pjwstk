package org.orders;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class OrderCryptoSenderService {
    private boolean isTransactionCompleted = false;
    final private String PRIVATE_KEY = "8fe592037f561efedcd8ceb49835ac4c61ae121fd2e373dee8257fd2f65ca73e";
    final BigInteger GAS_LIMIT = DefaultGasProvider.GAS_LIMIT;
    final BigInteger GAS_PRICE = DefaultGasProvider.GAS_PRICE;
    final String myWallet = "0x329DfA6ca4F1210231B66e6d78361D36aBF178EA";
    final Web3j client = Web3j.build(
            new HttpService("https://goerli.infura.io/v3/7f6e1ebbe3d1420faa797ec6d215f8e0"));

    public void getWalletInfo() throws ExecutionException, InterruptedException, TimeoutException {
        BigInteger bigIntegerUnscaled = client.ethGetBalance(myWallet, DefaultBlockParameter.valueOf("latest")).sendAsync().get(10, TimeUnit.SECONDS).getBalance();
        System.out.println(bigIntegerUnscaled.toString());
        BigDecimal bigIntegerScaled = new BigDecimal(bigIntegerUnscaled).divide(new BigDecimal ( 1000000000000000000L),2, RoundingMode. HALF_UP);
        System.out.println(bigIntegerScaled + " ETH ");
        client.shutdown();
    }

    private Credentials getCredentialsFromPrivateKey(){
        return Credentials.create(PRIVATE_KEY);
    }
    public void sendCryptoItem() throws Exception {
        String cryptoAddressEndpoint = "0xA8D7abFc7cd770000B0fD6F9a07D5d0Ac9361096";
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                        client, getCredentialsFromPrivateKey(),
                        cryptoAddressEndpoint,
                        BigDecimal.TEN, Convert.Unit.FINNEY)
                .send();
        System.out.println(("Transaction complete: "
                + transferReceipt.getTransactionHash()));

//        Transfer.sendFunds(client, getCredentialsFromPrivateKey(), cryptoAddressEndpoint, BigDecimal.TEN, Convert.Unit.FINNEY).sendAsync();
//        TransactionManager transactionManager = new RawTransactionManager(client, getCredentialsFromPrivateKey(), (byte)5);
//        TransactionReceipt transactionReceipt = transfer.sendFunds(cryptoAddressEndpoint, BigDecimal.TEN, Convert.Unit.FINNEY, GAS_PRICE, GAS_LIMIT).send();
//        System.out.println(transactionReceipt.getTransactionHash());
    }




}
