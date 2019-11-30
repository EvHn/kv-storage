package khannanov.kvstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication()
@EntityScan("khannanov.kvstorage.model")
public class KvStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(KvStorageApplication.class, args);
	}

}
