package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class TransactionProfileRequest {
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
	private String urn;
	private String subWalletId;
	private String enablementType;
	private String transactionProfileId;
	private String transactionType;
	private String status;
	
	private TransactionProfile tps[];
	
	public JSONObject jsonObject = new JSONObject();
    public Faker faker = new Faker();
	
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "3070";
		}
		if(!messageCode.toLowerCase().equals("false")) {
			jsonObject.put("messageCode", messageCode.equals("null") ? JSONObject.NULL : this.messageCode);
		}
	}
	public void setClientTxnId(String clientTxnId) {
		this.clientTxnId = clientTxnId;
		if(clientTxnId.contentEquals("Auto")) {
			this.clientTxnId = faker.number().randomNumber(12, true)+"";
		}
		if(!clientTxnId.toLowerCase().equals("false")) {
			jsonObject.put("clientTxnId", clientTxnId.equals("null") ? JSONObject.NULL : this.clientTxnId);	
		}
	}
	
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
		if(requestDateTime.contentEquals("Auto")) {
			this.requestDateTime = GenericMethods.getCurrentDateTime("yyyyMMddHHmmss");
		}		
		if(!requestDateTime.toLowerCase().equals("false")) {
			jsonObject.put("requestDateTime", requestDateTime.equals("null") ? JSONObject.NULL : this.requestDateTime);	
		}
	}
	
	public void setUrn(String urn) {
		this.urn = urn;
		if(urn.contentEquals("Auto")) {
			this.urn = faker.number().randomNumber(12, true)+"";
		}		
		if(!urn.toLowerCase().equals("false")) {
			jsonObject.put("urn", urn.equals("null") ? JSONObject.NULL : this.urn);	
		}
	}
	
	public void setSubWalletId(String subWalletId) {
		this.subWalletId = subWalletId;
		if(subWalletId.contentEquals("Auto")) {
			this.subWalletId = faker.number().randomNumber(12, true)+"";
		}		
		if(!subWalletId.toLowerCase().equals("false")) {
			jsonObject.put("subWalletId", subWalletId.equals("null") ? JSONObject.NULL : this.subWalletId);	
		}
	}
	public void setEnablementType(String enablementType) {
		this.enablementType = enablementType;
		if(enablementType.contentEquals("Auto")) {
			this.enablementType = "1";
		}		
		if(!enablementType.toLowerCase().equals("false")) {
			jsonObject.put("enablementType", enablementType.equals("null") ? JSONObject.NULL : this.enablementType);	
		}
	}
	public void setTransactionProfileId(String transactionProfileId) {
		this.transactionProfileId = transactionProfileId;		
		String[] tpids =  transactionProfileId.split(",");		
		tps = new TransactionProfile[tpids.length];
		for(int i=0;i<tpids.length;i++) {
			TransactionProfile tp = new TransactionProfile();
			tp.setTransactionProfileId(tpids[i]);
			tps[i] = tp;
		}
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		String[] tt =  transactionType.split(",");		
		for(int i=0;i<tt.length;i++) {			
			tps[i].setTransactionType(tt[i]);
		}
	}
	public void setStatus(String status) {
		this.status = status;
		String[] st =  status.split(",");		
		for(int i=0;i<st.length;i++) {			
			tps[i].setStatus(st[i]);
		}
	}
	
	
	public void setTransactionProfileList() {
		if ((transactionProfileId.contentEquals("false") && transactionType.contentEquals("false")
				&& status.contentEquals("false"))
				|| (transactionProfileId.contentEquals("") && transactionType.contentEquals("")
						&& status.contentEquals(""))) {
			System.out.println("Remove transaction profiles list");
		} else if (this.tps.length > 0) {
			JSONObject obj[] = new JSONObject[tps.length];
			for (int i = 0; i < tps.length; i++) {
				obj[i] = tps[i].getJsonobject();
			}
			jsonObject.put("transactionProfile", obj);
		}
	}
	
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String transactionProfileRequestBody(String token) {
		String jsonbody = "{"
				+ "    \"token\": \""+token+"\""
				+ "}";
		return jsonbody;
	}
	
	public String decryptRequestBody(String token) {
		String jsonbody = "{"
				+ "    \"token\": \""+token+"\""
				+ "}";
		return jsonbody;
	}
	

}
