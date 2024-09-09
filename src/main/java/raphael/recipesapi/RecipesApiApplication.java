package raphael.recipesapi;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@Slf4j
public class RecipesApiApplication  {
//test
	public static void main(String[] args) {

		SpringApplication.run(RecipesApiApplication.class, args);
		makeDirectory();
	}
	private static void makeDirectory() {
        File parDirectory = new File(System.getProperty("user.dir"));
		String path = "pictures";
	 	File newDirectory = new File(parDirectory, path);
		if (!newDirectory.exists()) {
			newDirectory.mkdir();
		}
    }

}
