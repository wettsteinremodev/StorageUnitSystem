package ch.sus.storageunitsytemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StorageUnitSystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageUnitSystemServiceApplication.class, args);
    }

}
