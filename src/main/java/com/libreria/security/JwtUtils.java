package com.libreria.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	
	private String SECRET_KEY = "esta_es_una_clave_secreta_muy_larga_y_segura_para_el_proyecto";
    private long JWT_EXPIRATION = 86400000;
    
    
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    
    public String generateToken(String username, Collection<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    
    
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder() // 
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    
    public boolean validateToken(String token) {
        try {
            // Imprimimos la longitud para verificar si no tiene caracteres de más
            System.out.println("[DEBUG JWT] Longitud del token recibido en validateToken: " + token.length());
            
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            
            System.out.println("[DEBUG JWT] ¡Token verificado con éxito en el Parser!");
            return true;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            System.out.println("❌ ERROR JWT: La firma no coincide. El string del token fue alterado o mal codificado. Mensaje: " + e.getMessage());
            return false;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("❌ ERROR JWT: El token está vencido. Mensaje: " + e.getMessage());
            return false;
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            System.out.println("❌ ERROR JWT: El token es de estructura inválida (Malformed). Chequear comillas o caracteres extra. Mensaje: " + e.getMessage());
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("❌ ERROR JWT GENERAL: " + e.getMessage());
            return false;
        }
    }
	
}
