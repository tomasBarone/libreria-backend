package com.libreria.config;

import java.util.Set;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.libreria.model.*;
import com.libreria.repository.*;

import jakarta.transaction.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // NUEVOS REPOSITORIOS
    @Autowired
    private CorrienteRepository corrienteRepo;
    @Autowired
    private GeneroRepository generoRepo;
    @Autowired
    private SubgeneroRepository subgeneroRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        // --- 1. SEGURIDAD (Tu código original) ---
        RoleEntity adminRole = roleRepository.findByRoleName(RoleEnum.ADMIN)
                .orElseGet(() -> roleRepository.save(new RoleEntity(RoleEnum.ADMIN)));

        RoleEntity userRole = roleRepository.findByRoleName(RoleEnum.USER)
                .orElseGet(() -> roleRepository.save(new RoleEntity(RoleEnum.USER)));

        if (userRepository.findByUsername("admin").isEmpty()) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setRoles(Set.of(adminRole));
            userRepository.save(adminUser);
            System.out.println(">>> [DataInitializer] Usuario 'admin' creado.");
        }

        // --- 2. ONTOLOGÍA LITERARIA (Nueva lógica) ---
        if (corrienteRepo.count() == 0) {
            inicializarDatosLiterarios();
            System.out.println(">>> [DataInitializer] Corrientes, Géneros y Subgéneros cargados.");
        }
    }
    
    
    
    
    

    private void inicializarDatosLiterarios() {
        // Carga de Corrientes
        corrienteRepo.saveAll(List.of(
        		 new CorrienteLiteraria("Clasicismo", "V a.C - V d.C","Es la base de la cultura occidental (Grecia y Roma)", "Uso de la mitologia, respeto por las unidades de tiempo y espacio y un fin didactico o moral","La busqueda de la perfeccion, la armonia y el equilibrio"),
        	     new CorrienteLiteraria("Literatura Medieval", "V- XV", "Dominada por la cosmovision teocentrica (Dios como centro)", "Gran parte es anonima y oral(juglares). Aparecen los cantares de gesta y los cuentos morales","La transmision de valores religiosos y caballerescos."),
                 new CorrienteLiteraria("Renacimiento" , "XV-XVI", "El paso del teocentrismo al antropecentrismo (el hombre como centro)","Lenguaje sencillo y claro, optimismo y el uso del soneto", "El retorno a los clasicos grecorromanos y la valoracion de la naturaleza y el amor idealizado"),
        		 new CorrienteLiteraria("Barroco" , "XVII" , "Una epoca de crisis, pesimismo y  desengaño", "La ornamentacion excesiva y la complejidad intelectual","Uso de las figuras retoricas complejas (metaforas, hiperbaton). Se duvude en Culteranismo (forma) y Conceptismo (contenido/ideas)" ),
        		 new CorrienteLiteraria("Neoclasicismo", "XVIII", "El Siglo de las Luces, La razon se impone sobre los sentimientos", "La utilidad pedagogica. La literatura debe enseñar no solo entretener","Rigidez en las normas, rechazo a lo fantastico y predominio del ensayo y la fabula"),
        		 new CorrienteLiteraria("Romanticismo" , "XIX - Primera Mitad" , "El gran grito de libertad y rebeldia contra la razon", "El predominio del Yp, los sentimientos desbordados y la evasion de mundos lejanos o pasados" , " Culto al individuo,fascinacion por lo oscuro y lo sobrenatural, y la naturaleza como reflejo del estado de animo del autor"),
        		 new CorrienteLiteraria("Realismo y Naturalismo", "XIX - Segunda Mitad" , "Como reaccion al excesp sentimental del Romanticismo, surge el deseo de retratar la realidad tal cual es. ", "La observacion casi cientifica de la sociedad" , "Descripciones minuciosas, lenguaje cotidiano y enofque en problemas sociales(pobreza,burguesia, injusticia. El NATURALISMO es un realismo extrem que cree que el hombre esta determiando por su herencia y su entorno"),
        		 new CorrienteLiteraria("Modernismo" , "Finales del XIX - Principios del XX" , "Surge en Hispanoamerica con Ruben Dario" , "La busqueda de la belleza absoluta y el refinamiento estetico" , "Uso de simbolos (como el cisne), exotismo(palacios, princesas, Oriente) y una musicalidad muy marcada en el verso"),
        		 new CorrienteLiteraria("Vanguardismo" , "Principios del XX" , "Conjunto de movimientos(Ismos: Surrealismo, Futurismo, Dadaismo) tras la primera guerra mundial" , "La ruptura total con el pasado y la experimentacion", "Alteracion del orden logico, versolibrismo, caligramas y exploracion del subconciente"),
        		 new CorrienteLiteraria("Posmodernidad" , "Mitad del XX - Actualidad","etc" , "El juego con la estructura narrativa y el Realismo Magico","Fragmentacion del tiempo, multiplicidad de narradores y la mezcla de lo fantastico con lo cotidiano de forma natural")
        ));

        // Carga de Géneros y sus Subgéneros
        // NARRATIVO
        GeneroLiterario narrativo = generoRepo.save(new GeneroLiterario("Narrativo", "Relato de hechos reales o imaginarios"));
        subgeneroRepo.save(new Subgenero("Novela", "Relato extenso y complejo", narrativo));
        subgeneroRepo.save(new Subgenero("Cuento", "Narración breve", narrativo));
        subgeneroRepo.save(new Subgenero("Fábula", "Relato con moraleja", narrativo));

        // LÍRICO
        GeneroLiterario lirico = generoRepo.save(new GeneroLiterario("Lírico", "Expresión de sentimientos subjetivos generalmente a traves del verso"));
        subgeneroRepo.save(new Subgenero("Soneto", "Composición de 14 versos", lirico));
        subgeneroRepo.save(new Subgenero("Oda", "Poema de tono elevado", lirico));

        // DRAMÁTICO
        GeneroLiterario dramatico = generoRepo.save(new GeneroLiterario("Dramático", "Obras creadas para ser representadas ante un publico mediante el dialogo de los personajes"));
        subgeneroRepo.save(new Subgenero("Tragedia", "Conflictos fatales", dramatico));
        subgeneroRepo.save(new Subgenero("Comedia", "Tono alegre y final feliz", dramatico));
        
        //DIDACTICO
        GeneroLiterario didactico = generoRepo.save(new GeneroLiterario("Didactico","Su finalidad principal es la enseñanza o la divulgacion de ideas"));
        subgeneroRepo.save(new Subgenero("Ensayo","Escrito en prosa donde el autor exponse su punto de vista sobre un tema",didactico));
        subgeneroRepo.save(new Subgenero("Ensayo","Escrito en prosa donde el autor exponse su punto de vista sobre un tema",didactico));
        subgeneroRepo.save(new Subgenero("Ensayo","Escrito en prosa donde el autor exponse su punto de vista sobre un tema",didactico));
    }
}