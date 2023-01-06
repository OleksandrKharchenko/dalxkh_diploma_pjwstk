package org.orders;

import okhttp3.OkHttpClient;
import org.payments.CryptoPaymentBalance;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OrderCryptoReceiverSenderService {
    private boolean isTransactionCompleted = false;
    final private String PRIVATE_KEY = "8fe592037f561efedcd8ceb49835ac4c61ae121fd2e373dee8257fd2f65ca73e";
    final BigInteger GAS_LIMIT = DefaultGasProvider.GAS_LIMIT;
    final BigInteger GAS_PRICE = DefaultGasProvider.GAS_PRICE;
    final String myWallet = "0x329DfA6ca4F1210231B66e6d78361D36aBF178EA";
    final okhttp3.OkHttpClient httpClient = new OkHttpClient();
    final Web3j web3jClient = Web3j.build(new HttpService("https://goerli.infura.io/v3/7f6e1ebbe3d1420faa797ec6d215f8e0", httpClient));

    public double getBalance() throws IOException {
        BigInteger bigIntegerUnscaled = web3jClient.ethGetBalance(myWallet, DefaultBlockParameterName.LATEST).send().getBalance();
        double d = bigIntegerScaledToDouble(bigIntegerUnscaled);
        System.out.println("current balance: " + d + " ETH ");
        httpClient.connectionPool().evictAll();
        web3jClient.shutdown();
        return d;
    }

    public void getWalletInfo() throws IOException {
        BigInteger bigIntegerUnscaled = web3jClient.ethGetBalance(myWallet, DefaultBlockParameterName.LATEST).send().getBalance();
        double d = bigIntegerScaledToDouble(bigIntegerUnscaled);
        System.out.println(d + " ETH ");
        httpClient.connectionPool().evictAll();
        web3jClient.shutdown();
    }

    public String verifyCryptoTxPayment(double amount, String txHash) throws IOException {
        try {
            TransactionReceipt transactionReceipt = web3jClient.ethGetTransactionReceipt(txHash).send().getTransactionReceipt().get();
            System.out.println(transactionReceipt.getStatus());
            if (transactionReceipt.getStatus().equals("0x1")) {
                EthTransaction eTx = web3jClient.ethGetTransactionByHash(txHash).send();
                String checkReceiverAddress= eTx.getTransaction().get().getTo();
                System.out.println(checkReceiverAddress);
                if(checkReceiverAddress.equals(myWallet.toLowerCase())){
                    BigInteger bigIntegerUnscaled = eTx.getTransaction().get().getValue();
                    double d = bigIntegerScaledToDouble(bigIntegerUnscaled);
                    System.out.println("d value: " + d + " amount value: " + amount);
                    if(d >= amount){
                        System.out.println("OK");
                        return "OK";
                    }
                    System.out.println("amount sent is wrong");
                    //TODO SEND AMOUNT BACK TO SENDER
                    return "Amount sent is wrong";
                 }
                System.out.println("Wrong receiver address, please, provide TX hash with 0x329DfA6ca4F1210231B66e6d78361D36aBF178EA as a receiver");
                return "Wrong receiver address, please, provide TX hash with 0x329DfA6ca4F1210231B66e6d78361D36aBF178EA as a receiver";
                }
            return "Transaction " + txHash +" found but is not completed yet in blockchain... you can increase gas or wait... ";
        }catch (NoSuchElementException nsex){
            System.out.println("Eth tx not found");
            return "Eth tx not found " + txHash;
        }
    }

    public double bigIntegerScaledToDouble (BigInteger bigInteger){
        BigDecimal bigDecimal = new BigDecimal(bigInteger).divide(new BigDecimal ( 1000000000000000000L),3, RoundingMode. HALF_UP);
        return bigDecimal.doubleValue();
    }

    public void getTxsValue () throws IOException {
        String txHash = "0x91be25a1713097937049439ac71909616eb55d3e2fa912031c4063814afdea8f";
        EthTransaction eTx = web3jClient.ethGetTransactionByHash("0x91be25a1713097937049439ac71909616eb55d3e2fa912031c4063814afdea8f").send();
        System.out.println(eTx.getTransaction().get().getValue());
        TransactionReceipt transactionReceipt = web3jClient.ethGetTransactionReceipt(txHash).send().getTransactionReceipt().get();
        System.out.println(transactionReceipt.getStatus());
    }

    private Credentials getCredentialsFromPrivateKey(){
        return Credentials.create(PRIVATE_KEY);
    }

    public String sendCryptoItem(String cryptoAddressEndpoint) throws Exception {
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                        web3jClient, getCredentialsFromPrivateKey(),
                        cryptoAddressEndpoint,
                        BigDecimal.ONE, Convert.Unit.FINNEY)
                .send();
        System.out.println(("Transaction complete: "
                + transferReceipt.getTransactionHash()));
        httpClient.connectionPool().evictAll();
        web3jClient.shutdown();
        return transferReceipt.getTransactionHash();
    }

}
