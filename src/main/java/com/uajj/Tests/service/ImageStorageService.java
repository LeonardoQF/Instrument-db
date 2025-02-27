package com.uajj.Tests.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.Tests.config.StorageProperties;
import com.uajj.Tests.model.entities.Instrument;
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
	 * Creates the storage directory and its subdirectories. If the directory
	 * already exists, creation is skipped and nothing gets created. <br>
	 * The amount and name of the created subdirectories are based on the
	 * InstrumentType enum. For each value present in it, a subdirectory bearing its
	 * same name will be created.
	 */
	@Override
	public void init() {
		try {
			if (Files.exists(rootFolderLocation))
				throw new StorageAlreadyExistsException("Storage '" + rootFolderLocation.toAbsolutePath().toString()
						+ "' already exists. Skipping creation");

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
	public void storeFile(MultipartFile file, Instrument instrument) {
		try {

			if (file.isEmpty())
				throw new StorageException("File cannot be empty");

			Path destinationPath = createFolder(instrument).resolve(file.getName());

			System.out.println("Destination path for testing: " + destinationPath);

			file.transferTo(destinationPath);

		} catch (IOException e) {
			throw new RuntimeException("Error creating file: " + e);
		} catch (StorageAlreadyExistsException e) {
			System.out.println(e);
		}

	}

	@Override
	public Resource getAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a folder whose name will be the same as the instrument's id.
	 * 
	 * @param instrument - the instrument to get the id from.
	 * @return The path in which the folder was created.
	 */

	@Override
	public Path createFolder(Instrument instrument) {
		try {
			// Root directory
			Path rootPath = rootFolderLocation;
			File rootFolder = rootPath.toAbsolutePath().toFile();

			// Name for the created folder, which should be named after the instrument's ID
			String folderToBeCreatedName = instrument.getId().toString();

			for (File folder : rootFolder.listFiles()) {
				if (folder.getName().equals(instrument.getType().toString())) {
					Path equivalentInstrumentTypePath = folder.toPath();

					Path createdInstrumentImageFolderPath = equivalentInstrumentTypePath.resolve(folderToBeCreatedName);

					// Checks if the folder has already been created and returns the existent path if it does
					if (Files.exists(createdInstrumentImageFolderPath)) {
						System.out.println("Folder for the provided instrument already exists. Skipping");
						return createdInstrumentImageFolderPath;
					}
					Files.createDirectories(createdInstrumentImageFolderPath);
					return createdInstrumentImageFolderPath;
				}
			}

			throw new IllegalArgumentException("No such folder for InstrumentType " + instrument.getType().toString());

		} catch (IOException e) {
			throw new RuntimeException("Error while creating folder: " + e);

		}
	}

}
