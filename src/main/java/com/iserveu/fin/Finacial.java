package com.iserveu.fin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iserveu.fin.request.wallet2DebitRequestPojo;
import com.iserveu.fin.response.WalletResponse;

public class Finacial {

	@Autowired
	private static RestTemplate client = new RestTemplate();

	private static String uri="https://cbsgateway-stage.iserveu.online/common/wallet2/doDebit";

	public static int debit(long walletid, double amount, long apiwalletid, String txnid, boolean is_sl,
			String transactionType, String userid, String apiuserid, String callBackUrl, String contraWalletId,
			String contraApiWalletId) {
		WalletResponse walletRes = new WalletResponse();
		int statuscode = 0;
		try {

			wallet2DebitRequestPojo transcationRequest = new wallet2DebitRequestPojo();
			transcationRequest.setTxnid(txnid);
			transcationRequest.setAmount(amount);
			transcationRequest.setWalletid(walletid);
			transcationRequest.setUserid(userid);
			transcationRequest.setApiwalletid(apiwalletid);
			transcationRequest.setApiuserid(apiuserid);
			transcationRequest.setIs_sl(is_sl);
			transcationRequest.setTransactionType(transactionType);
			transcationRequest.setCallbackRequired(false);
			if(callBackUrl!=null) {
				transcationRequest.setCallbackRequired(true);
			}
			ResponseEntity<WalletResponse> retailerWalletResponse = null;
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<wallet2DebitRequestPojo> retailerWalletReq = new HttpEntity(transcationRequest, httpHeaders);
			
			try {
				retailerWalletResponse = client.postForEntity(uri, retailerWalletReq, WalletResponse.class);
				
				walletRes = retailerWalletResponse.getBody();
				
				statuscode=1;
				
			} catch (HttpServerErrorException ex) {
				HttpStatus statusCode = ex.getStatusCode();
			
				String responseBody = ex.getResponseBodyAsString();
				ObjectMapper objectMapper = new ObjectMapper();
				if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
					try {
						JsonNode jsonNode = objectMapper.readTree(responseBody);
						String walletStatusDesc = jsonNode.get("walletStatusDesc").asText();
						statuscode=11;
						
					} catch (JsonProcessingException e) {
					
						statuscode=2;
					
					}
				} else {
					
					statuscode=3;
				}
			} catch (HttpClientErrorException e) {
				HttpStatus code = e.getStatusCode();
				String responseBody = e.getResponseBodyAsString();
				ObjectMapper objectMapper = new ObjectMapper();
				if (code == HttpStatus.BAD_REQUEST) {
					try {
						JsonNode jsonNode = objectMapper.readTree(responseBody);
						String walletStatusDesc = jsonNode.get("walletStatusDesc").asText();
					
						statuscode=4;
						
					} catch (JsonProcessingException ex) {
				
						statuscode=5;
					}
				} else {
				
					statuscode=6;
				}
			} catch (Exception e) {
				e.printStackTrace();
				statuscode=7;
			}
			
			return statuscode;
		} catch (Exception e) {
			statuscode=8;
			return statuscode;
		}
		// finally {
//			
//			 return statuscode;
//		}

	}
	
	
	public static int credit(long walletid, double amount, long apiwalletid, String txnid, boolean is_sl,
			String transactionType, String userid, String apiuserid, String callBackUrl, String contraWalletId,
			String contraApiWalletId) {
		WalletResponse walletRes = new WalletResponse();
		int statuscode = 0;
		try {

			wallet2DebitRequestPojo transcationRequest = new wallet2DebitRequestPojo();
			transcationRequest.setTxnid(txnid);
			transcationRequest.setAmount(amount);
			transcationRequest.setWalletid(walletid);
			transcationRequest.setUserid(userid);
			transcationRequest.setApiwalletid(apiwalletid);
			transcationRequest.setApiuserid(apiuserid);
			transcationRequest.setIs_sl(is_sl);
			transcationRequest.setTransactionType(transactionType);
			transcationRequest.setCallbackRequired(false);
			if(callBackUrl!=null) {
				transcationRequest.setCallbackRequired(true);
			}
			ResponseEntity<WalletResponse> retailerWalletResponse = null;
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<wallet2DebitRequestPojo> retailerWalletReq = new HttpEntity(transcationRequest, httpHeaders);
			
			try {
				retailerWalletResponse = client.postForEntity(uri, retailerWalletReq, WalletResponse.class);
				
				walletRes = retailerWalletResponse.getBody();
				
				statuscode=1;
				
			} catch (HttpServerErrorException ex) {
				HttpStatus statusCode = ex.getStatusCode();
			
				String responseBody = ex.getResponseBodyAsString();
				ObjectMapper objectMapper = new ObjectMapper();
				if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
					try {
						JsonNode jsonNode = objectMapper.readTree(responseBody);
						String walletStatusDesc = jsonNode.get("walletStatusDesc").asText();
						statuscode=11;
						
					} catch (JsonProcessingException e) {
					
						statuscode=2;
					
					}
				} else {
					
					statuscode=3;
				}
			} catch (HttpClientErrorException e) {
				HttpStatus code = e.getStatusCode();
				String responseBody = e.getResponseBodyAsString();
				ObjectMapper objectMapper = new ObjectMapper();
				if (code == HttpStatus.BAD_REQUEST) {
					try {
						JsonNode jsonNode = objectMapper.readTree(responseBody);
						String walletStatusDesc = jsonNode.get("walletStatusDesc").asText();
					
						statuscode=4;
						
					} catch (JsonProcessingException ex) {
				
						statuscode=5;
					}
				} else {
				
					statuscode=6;
				}
			} catch (Exception e) {
				e.printStackTrace();
				statuscode=7;
			}
			
			return statuscode;
		} catch (Exception e) {
			statuscode=8;
			return statuscode;
		}
		// finally {
//			
//			 return statuscode;
//		}

	}
}
