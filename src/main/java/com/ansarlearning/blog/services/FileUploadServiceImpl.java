package com.ansarlearning.blog.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// get the originalFileName
		String fileName = file.getOriginalFilename();

		// generate random name
		String randomNumber = UUID.randomUUID().toString();

		String genratedFileName = randomNumber.concat(fileName.substring(fileName.lastIndexOf(".")));
		// get Full Path
		String fullPath = path + File.separator + genratedFileName;

		

		File f = new File(path);

		if (!f.exists()) {
			f.mkdir();

		}
		
		Files.copy(file.getInputStream(), Paths.get(fullPath));

		return genratedFileName;
	}

	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;

		InputStream inputStream = new FileInputStream(fullPath);

		return inputStream;
	}

}
