package com.devinhouse.M02S10;

import com.devinhouse.M02S10.data.Role;
import com.devinhouse.M02S10.model.Usuario;
import com.devinhouse.M02S10.repository.UsuarioRepository;
import com.devinhouse.M02S10.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;

@SpringBootApplication
public class M02S10Application {

	public static void main(String[] args) {
		SpringApplication.run(M02S10Application.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		var mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true);
		return mapper;
	}

//	@Bean
//	public ModelMapper obterModelMapper() {
//		return new ModelMapper();
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(10); //reload messages every 10 seconds
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	CommandLineRunner run(UsuarioService usuarioService) {
		return args -> {

			usuarioService.inserir(new Usuario("Admin Geral","admin@geral.com",
					"admin123", Role.ADMIN));
		};
	}


}
