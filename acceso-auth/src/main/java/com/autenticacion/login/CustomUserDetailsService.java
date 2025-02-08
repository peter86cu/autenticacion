package com.autenticacion.login;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DataSource dataSource;

    public CustomUserDetailsService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            String query;
            String userIdentifier;
            
            // Determinar la consulta según el prefijo
            if (username.startsWith("shop:")) {
                query = "SELECT email, password, state FROM shopping_usuarios WHERE email = ?";
                userIdentifier = username.replace("shop:", "");
            } else if (username.startsWith("admin:")) {
                query = "SELECT usuario, password, estado FROM usuarios WHERE fechabaja IS NULL AND usuario = ?";
                userIdentifier = username.replace("admin:", "");
            } else {
                throw new UsernameNotFoundException("Formato de usuario no reconocido.");
            }

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, userIdentifier);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userEmail = userIdentifier;
                String password = rs.getString("password");
                int enabled = rs.getInt(3); // Estado del usuario

                return User.builder()
                        .username(userEmail)
                        .password(password)
                        .disabled(enabled!=1)
                        .roles(username.startsWith("shop:") ? "SHOP_USER" : "ADMIN") // Roles distintos
                        .build();
            } else {
                throw new UsernameNotFoundException("User not found with username: " + userIdentifier);
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Database error", e);
        }
    }
}
