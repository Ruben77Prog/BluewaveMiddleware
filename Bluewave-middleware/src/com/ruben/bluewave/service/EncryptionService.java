package com.ruben.bluewave.service;

public interface EncryptionService {
	
	public String encrypt(String data);
	public boolean checkEncription(String clearData, String ecryptedData);
	public String decrypt(String data);
	
}
