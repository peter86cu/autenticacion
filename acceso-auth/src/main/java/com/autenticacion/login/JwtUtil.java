package com.autenticacion.login;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;

import javax.crypto.SecretKey;

public class JwtUtil {

	private static final String SECRET = "6o84T5FgHhLsaeFC5J7/V7MAjUG81YOXH5hDGi8RMBVKoWyOY0MkVc0vHf7pWZsL"; // Genera una clave más segura
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));


    public static Key getSecretKey() {
        return SECRET_KEY;
    }
}
