package com.iserveu.fin.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class wallet2DebitRequestPojo {
	

	private String userid;

	private String apiuserid;

	private Long walletid;

	private Long apiwalletid;

	private Double amount;
	
	private String txnid;
	
	private Boolean is_sl;
	
	private String transactionType;
	
	private boolean isCallbackRequired;
}
