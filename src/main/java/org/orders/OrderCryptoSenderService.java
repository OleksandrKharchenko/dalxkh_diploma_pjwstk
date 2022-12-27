package org.orders;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class OrderCryptoSenderService {
    private boolean isTransactionCompleted = false;
    final String myWallet = "0x329DfA6ca4F1210231B66e6d78361D36aBF178EA";
    final Web3j client = Web3j.build(
            new HttpService("https://goerli.infura.io/v3/376e17af3c6a4c8ea17b7d33f7ae42b6"));

    public void getWalletInfo() throws ExecutionException, InterruptedException, TimeoutException {
        BigInteger bigIntegerUnscaled = client.ethGetBalance(myWallet, DefaultBlockParameter.valueOf("latest")).sendAsync().get(10, TimeUnit.SECONDS).getBalance();
        System.out.println(bigIntegerUnscaled.toString());
        BigDecimal bigIntegerScaled = new BigDecimal(bigIntegerUnscaled).divide(new BigDecimal ( 1000000000000000000L),2, RoundingMode. HALF_UP);
        System.out.println(bigIntegerScaled + " ETH ");
        client.shutdown();
    }



}
