package com.fbayhan.onepiececrawler;

import com.fbayhan.onepiececrawler.business.FileOperations;
import com.fbayhan.onepiececrawler.business.FileOperationsImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class OnepiececrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnepiececrawlerApplication.class, args);
        FileOperationsImp fileOperationsImp = new FileOperationsImp();

        fileOperationsImp.createFolder(666, 416);

    }

}
