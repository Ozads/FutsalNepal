package com.ozads.futsalnepal.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;


public class FileUtility {
	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "image";

	public static File writeFile(String name, MultipartFile file) {
		File directory = new File(UPLOADED_FOLDER);
		File filePath = null;
		if (!directory.exists()) {
			directory.mkdir();
		}
		try {
			byte[] bytes = file.getBytes();
			//System.out.println("/n/n/n "+bytes);
			filePath = new File(directory.getPath().concat(File.separator).concat(name)
					.concat(file.getOriginalFilename()));
			Path path = Paths
					.get(UPLOADED_FOLDER + "//" + name + file.getOriginalFilename());
			System.out.println("Path:" + path);
			Files.write(path, bytes);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n"+filePath);
		return filePath;
	}
}


