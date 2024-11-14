package com.iserveu.fin.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponse {
    private long Txnid;
    private String walletStatus ;
    private  String walletStatusDesc;
    private Integer walletStatusCode;
    private Long walletTransactionID;
    private Long apiwalletTransactionID;

    public WalletResponse(String walletStatus, Future<String> walletStatusDesc) throws ExecutionException, InterruptedException {
        this.walletStatus =walletStatus;
        this.walletStatusDesc=walletStatusDesc.get();
    }

    public WalletResponse(String walletStatus, String walletStatusDesc, Integer walletStatusCode) {
        this.walletStatus = walletStatus;
        this.walletStatusDesc = walletStatusDesc;
        this.walletStatusCode = walletStatusCode;
    }

    public WalletResponse(String walletStatus, String walletStatusDesc) {
        this.walletStatus =walletStatus;
        this.walletStatusDesc=walletStatusDesc;
    }

    public WalletResponse(String walletStatus, String walletStatusDesc, Integer walletStatusCode, Long Txnid ) {
        this.walletStatus = walletStatus;
        this.walletStatusDesc = walletStatusDesc;
        this.walletStatusCode = walletStatusCode;
        this.Txnid = Txnid;

    }
}
