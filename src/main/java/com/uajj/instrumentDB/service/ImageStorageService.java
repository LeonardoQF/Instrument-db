package com.uajj.instrumentDB.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.instrumentDB.config.StorageProperties;
import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.service.exceptions.InstrumentTypeMismatchException;
import com.uajj.instrumentDB.service.exceptions.StorageAlreadyExistsException;
import com.uajj.instrumentDB.service.exceptions.StorageException;
import com.uajj.instrumentDB.service.interfaces.StorageService;

@Service
public class ImageStorageService implements StorageService {

	/**
	 * The directory in which files will be saved. The value of the folder is passed
	 * in the constructor, inside the Paths.get() method.
	 */
	private final Path rootFolderLocation;

	private final StorageProperties properties;

    private InstrumentService service;


	public ImageStorageService(StorageProperties properties, InstrumentService service) {
		if (properties.getPath().trim().isEmpty()) {
			throw new StorageException("File upload path is empty");
		}

		this.service = service;
		this.properties = properties;

		this.rootFolderLocation = Paths.get(properties.getPath());

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
			System.out.println("Storage could not be created: " + e);
		}
	}

	@Override
	public void storeFile(MultipartFile file, Instrument instrument) {
		try {
			sanitizeImage(file);

			Path folderPath = createFolder(instrument);
			Path destinationPath = folderPath.resolve(generateStandardizedImageName(file, folderPath));

			validateImageCreation(file, folderPath);

			System.out.println("Received image save path: " + destinationPath);

			file.transferTo(destinationPath);

		} catch (IOException e) {
			throw new RuntimeException("Error creating file: " + e);
		} catch (StorageAlreadyExistsException e) {
			System.out.println(e);
		}

	}

	@Override
	public Resource getAsResource(Instrument instrument, String filename) {
		Path path = rootFolderLocation.resolve(instrument.getType().toString())
				.resolve(instrument.getId().toString()).resolve(filename);

		File image = path.toFile();

		return new FileSystemResource(image);
	}

	public List<String> getInstrumentImagesAsUrls(Instrument instrument) {
		String baseUrl = "http://localhost:8080/image_upload";
		Path instrumentFolderPath = rootFolderLocation.resolve(instrument.getType().toString())
				.resolve(instrument.getId().toString());

		File[] files = instrumentFolderPath.toFile().listFiles();

		List<String> urls = new ArrayList<>();

		for (File file : files) {
			if (file.isFile()) {
				String filename = file.getName();
				String fullUrl = baseUrl.concat("/image").concat("?id=").concat(instrument.getId().toString()).concat("&instrumentType=")
						.concat(instrument.getType().toString()).concat("&filename=").concat(filename);
			urls.add(fullUrl);
			}
		}
		
		return urls;

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
			validateFolderCreation(instrument.getId(), instrument.getType());
			// Root directory
            File rootFolder = rootFolderLocation.toAbsolutePath().toFile();

			// Name for the created folder, which should be named after the instrument's ID
			String folderToBeCreatedName = instrument.getId().toString();

			for (File folder : rootFolder.listFiles()) {
				if (folder.getName().equals(instrument.getType().toString())) {
					Path equivalentInstrumentTypePath = folder.toPath();

					Path createdInstrumentImageFolderPath = equivalentInstrumentTypePath.resolve(folderToBeCreatedName);

					/*
					 * Checks if the folder has already been created and returns the existent path
					 * if it does
					 */
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

	/**
	 * Validates and sanitizes the image to be stored in the storage. It does so by
	 * checking whether the file type is supported, empty, and if its size is too
	 * big.
	 * 
	 * @param image - The image to be sanitized
	 */
	public void sanitizeImage(MultipartFile image) {
		if (image.isEmpty())
			throw new StorageException("Image cannot be empty");

		String imageFileFormat = getDotFileExtension(image);

		if (!properties.getSupportedImageTypes().stream().anyMatch(x -> x.equals(imageFileFormat))) // Checks whether
																									// the file's format
																									// is supported, as
																									// per the
																									// supportedImageTypes
																									// on
																									// application.yaml
			throw new StorageException("File format not supported: " + imageFileFormat);

		if (image.getSize() > properties.getMaxImageSizeBytes())
			throw new StorageException("Images cannot exceed 5 MB");
	}

	public void validateImageCreation(MultipartFile image, Path instrumentFolderPath) {
		File instrumentFolder = instrumentFolderPath.toFile();

		if (instrumentFolder.listFiles().length >= properties.getMaxImagesPerFolder())
			throw new StorageException("Only 5 images can be saved for each instrument");
	}

	public void validateFolderCreation(UUID id, InstrumentType instrumentType) {
		Instrument foundInstrument = service.findById(id);

		if (!foundInstrument.getType().equals(instrumentType))
			throw new InstrumentTypeMismatchException("Instrument type mismatch: Provided Instrument is of type "
					+ foundInstrument.getType() + ", not the provided " + instrumentType + " type");
	}

	public String generateStandardizedImageName(MultipartFile image, Path imageFolderPath) {
		File imageFolder = imageFolderPath.toFile();
		File[] existingImagesInFolder = imageFolder.listFiles();
		int imageNumberId = existingImagesInFolder.length + 1;

		return "image" + imageNumberId + getDotFileExtension(image);
	}

	public String getDotFileExtension(MultipartFile file) {
		return "." + file.getContentType().split("/")[1];
		/*
		 * Splits the image's name in two: The part before and after // the /, and
		 * retrieves the second part which is of array // index 1.
		 */
	}

}
