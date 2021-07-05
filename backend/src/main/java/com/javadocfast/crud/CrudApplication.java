package com.javadocfast.crud;

import com.javadocfast.crud.seed.SeedRolesTable;
import com.javadocfast.crud.seed.SeedUsersTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.text.ParseException;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    // import org.springframework.boot.CommandLineRunner;
    // implements CommandLineRunner
    // @Override
    // public void run(String... args) throws Exception {
    //     seedRolesTable();
    // }

    @EventListener
    public void seed(ContextRefreshedEvent event) throws ParseException {

        SeedRolesTable.insertData();
        SeedUsersTable.insertData();

    }

}
