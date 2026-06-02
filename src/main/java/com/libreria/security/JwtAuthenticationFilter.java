package com.libreria.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
    	System.out.println("====== NUEVA PETICION EN EL FILTRO ======");
        System.out.println("Metodo HTTP: " + request.getMethod());
        System.out.println("URL solicitada: " + request.getRequestURI());

        String authHeader = request.getHeader("Authorization");
        System.out.println("Header Authorization recibido: " + authHeader);

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                System.out.println("Token extraido en el Back: " + token);

                // Intentamos validar
                boolean esValido = jwtUtils.validateToken(token);
                System.out.println("¿jwtUtils.validateToken() es valido?: " + esValido);

                if (esValido) {
                    String username = jwtUtils.getUsernameFromToken(token);
                    System.out.println("Usuario extraido del token: " + username);

                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        System.out.println("Buscando UserDetails en la Base de Datos para: " + username);
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        System.out.println("UserDetails cargado. Roles/Authorities: " + userDetails.getAuthorities());

                        UsernamePasswordAuthenticationToken authToken = 
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        System.out.println("¡CONTECTO DE SEGURIDAD CONFIGURADO CON ÉXITO para " + username + "!");
                    } else {
                        System.out.println("AVISO: El usuario ya estaba autenticado o el username vino null.");
                    }
                } else {
                    System.out.println("❌ RECHAZADO: jwtUtils.validateToken(token) devolvio FALSE.");
                }
            } else {
                System.out.println("AVISO: No se proceso token porque el header es null o no empieza con 'Bearer '");
            }
        } catch (Exception e) {
             // Si salta un error de token vencido, firma invalida o usuario inexistente, lo vemos acá:
            System.out.println("❌ EXCEPCIÓN EN EL FILTRO JWT: " + e.getMessage());
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
}

}

