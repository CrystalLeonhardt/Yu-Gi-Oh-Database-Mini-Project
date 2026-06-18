package com.crystal.bd_ygo.config;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminBootstrap {

    @Bean
    public CommandLineRunner seedAdmin(DataSource dataSource, PasswordEncoder encoder) {
        return args -> {
            JdbcTemplate jdbc = new JdbcTemplate(dataSource);

            // só cria se não existir
            Integer existing = jdbc.queryForObject(
                "SELECT COUNT(*) FROM usuario WHERE nome = ?", Integer.class, "admin");

            if (existing != null && existing == 0) {
                jdbc.update(
                    "INSERT INTO usuario(nome, password) VALUES (?, ?)",
                    "admin", encoder.encode("admin123")
                );
            }

            // garante que o perfil admin existe
            jdbc.update(
                "INSERT INTO perfil_usuario(usuarioid, cargo) " +
                "SELECT usuarioid, 'admin' FROM usuario WHERE nome = 'admin' " +
                "ON CONFLICT (usuarioid) DO UPDATE SET cargo = 'admin'"
            );
        };
    }
}
