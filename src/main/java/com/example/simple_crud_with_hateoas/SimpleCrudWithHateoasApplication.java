package com.example.simple_crud_with_hateoas;

import com.example.simple_crud_with_hateoas.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;

@SpringBootApplication
public class SimpleCrudWithHateoasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleCrudWithHateoasApplication.class, args);
    }

   /* @Bean(name = "pagedResourcesAssembler")
    public PagedResourcesAssembler<User> pagedResourcesAssembler() {
        return new PagedResourcesAssembler<>(new HateoasPageableHandlerMethodArgumentResolver(), null);
    }*/

}
