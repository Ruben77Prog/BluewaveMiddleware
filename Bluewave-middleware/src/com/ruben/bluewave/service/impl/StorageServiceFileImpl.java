package com.ruben.bluewave.service.impl;

import java.io.File;

import com.ruben.bluewave.model.DataException;
import com.ruben.bluewave.service.StorageData;
import com.ruben.bluewave.service.StorageMetadata;
import com.ruben.bluewave.service.StorageService;

public class StorageServiceFileImpl implements StorageService {
	
	private static final String STORAGE_FOLDER = "c:\\setlisto-storage";
	
	public StorageServiceFileImpl() {
		
	}
	
	
	// c:\\setlisto-storage\\musical-event\\51341\\51341-main-image.jpg
	/**
	 * Implementa la convencion de nombrado para la carpeta 
	 * en la que se guarda los datos para un id.
	 * @param id
	 * @param metadata
	 * @return
	 */
	private String getFolderFor(String id, StorageMetadata metadata) {
		StringBuilder folderName = new StringBuilder(STORAGE_FOLDER);
		folderName.append(File.separator);
		folderName.append(metadata.getEntityType());
		folderName.append(File.separator);
		folderName.append(id);
		folderName.append(File.separator);
		return folderName.toString();
	}
	
	/**
	 * Implementa la convenci�n de nombrado para el fichero de ciertos datos 
	 * asociados a un id.
	 * @param id
	 * @param metadata
	 * @return
	 */
	private String getFileNameFor(String id, StorageMetadata metadata) {		
		StringBuilder fileName = new StringBuilder(id)
									.append("_").append(metadata.getResourceType())
									.append(".").append(metadata.getFormat());
		return fileName.toString();
	}


	@Override
	public void save(String id, StorageMetadata metadata, byte[] data) throws DataException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean exists(String id, StorageMetadata metadata) throws DataException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public StorageData load(String id, StorageMetadata metadata) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public StorageData[] load(String id) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void remove(String id, StorageMetadata metadata) throws DataException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void remove(String id) throws DataException {
		// TODO Auto-generated method stub
		
	}

}