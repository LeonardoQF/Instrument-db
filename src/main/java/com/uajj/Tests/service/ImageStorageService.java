package com.uajj.Tests.service;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.Tests.config.StorageProperties;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.service.exceptions.StorageAlreadyExistsException;
import com.uajj.Tests.service.exceptions.StorageException;
import com.uajj.Tests.service.interfaces.StorageService;

@Service
public class ImageStorageService implements StorageService {

	/**
	 * The directory in which files will be saved. The value of the folder is passed
	 * in the constructor, inside the Paths.get() method.
	 */
	private final Path rootFolderLocation;

	public ImageStorageService(StorageProperties properties) {
		if (properties.getPathName().trim().length() == 0) {
			throw new RuntimeException(
					"File upload location is empty."); /*
														 * TODO Temporary RuntimeException, a more // specialized one
														 * will be created in the // future.
														 */
		}

		this.rootFolderLocation = Paths.get(properties.getPathName());

	}

	/**
	 * Checks if the storage directory exists, creating it along with folders for
	 * each InstrumentType within it, if not.
	 */
	@Override
	public void init() {
		try {
			if(Files.exists(rootFolderLocation)) throw new StorageAlreadyExistsException("Storage '" + rootFolderLocation.toAbsolutePath().toString() + "' already exists. Skipping creation");
			
			Files.createDirectories(rootFolderLocation);

			for (InstrumentType type : InstrumentType.values()) {
				Path instrumentTypePath = rootFolderLocation.resolve(type.name());
				Files.createDirectory(instrumentTypePath);
			}

			System.out.println(
					"Storage folder initialized successfully in " + rootFolderLocation.toAbsolutePath().toString());

		} catch (StorageAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Storage could not be created.");
		}
	}

	@Override
	public void storeFile(MultipartFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public Resource getAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

}
