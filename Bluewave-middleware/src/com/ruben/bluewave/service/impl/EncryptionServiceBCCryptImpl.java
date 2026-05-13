package com.ruben.bluewave.service.impl;

import org.mindrot.jbcrypt.BCrypt;

import com.ruben.bluewave.service.EncryptionService;

public class EncryptionServiceBCCryptImpl implements EncryptionService{

	
	public EncryptionServiceBCCryptImpl() {
		
	}
	
	@Override
	public String encrypt(String data) {
		
		return BCrypt.hashpw(data, BCrypt.gensalt());
	}

	@Override
	public boolean checkEncription(String clearData, String encryptedData) {
	
		return BCrypt.checkpw(clearData /*PUFF*/, encryptedData);
	}


	@Override
	public String decrypt(String data) {
		
		return null;
	}

	
	
}
