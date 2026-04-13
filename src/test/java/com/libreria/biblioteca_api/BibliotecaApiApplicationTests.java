package com.libreria.biblioteca_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class BibliotecaApiApplicationTests {
	@Value("${spring.datasource.url}")
    private String dbUrl;
	@Test
	void contextLoads() {
		
		System.out.println("--------------------------------------");
        System.out.println("TEST EJECUTÁNDOSE CON LA BASE DE DATOS:");
        System.out.println(dbUrl);
        System.out.println("--------------------------------------");
        
        // Verificamos que la URL contenga "libreria_test" y no "libreria"
        assert dbUrl.contains("libreria_test");
	}

}
