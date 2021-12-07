package ca.sheridancollege.liuzhun.services;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	
  public void save(File directory, String path, MultipartFile file);
}