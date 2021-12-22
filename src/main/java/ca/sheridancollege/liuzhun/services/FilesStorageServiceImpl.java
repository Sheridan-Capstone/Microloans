package ca.sheridancollege.liuzhun.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  @Override
  public void save(File directory, String path, MultipartFile file) {
    try {
    	byte[] bytes = file.getBytes();
    	if(!directory.exists()) {
        	directory.mkdir();
        	Path writepath = Paths.get(path + file.getOriginalFilename());
            Files.write(writepath,bytes);
        }
    	else {
    		Path writepath = Paths.get(path + file.getOriginalFilename());
            Files.write(writepath, bytes);
        }
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

}