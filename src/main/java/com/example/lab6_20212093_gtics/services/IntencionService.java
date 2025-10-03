package com.example.lab6_20212093_gtics.services;

import com.example.lab6_20212093_gtics.entitys.Intencion;
import com.example.lab6_20212093_gtics.entitys.Usuario;
import com.example.lab6_20212093_gtics.repositorys.IntencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class IntencionService {

    @Autowired
    private IntencionRepository intencionRepository;
    @Autowired
    private UsuarioService usuarioService;

    // Palabras que estan prohibidas, ya que subio en la tarea previa del lab anterior el regex creo que es requisito en este
    private static final String PALABRAS_PROHIBIDAS_REGEX = "(?i).*\\b(odio|pelea|violencia|matar|malo|muerte)\\b.*";
    private static final Pattern PROHIBIDAS_PATTERN = Pattern.compile(PALABRAS_PROHIBIDAS_REGEX);

    // Validacion de longitd de 16 a 256 caracteres
    private static final String CONTENT_AND_LENGTH_REGEX = "^[\\s\\S]{15,255}$";

    public boolean yaExistePeticion(Long usuarioId) {
        return intencionRepository.existsByUsuarioId(usuarioId);
    }

    public Intencion guardarPeticion(String descripcion, String correoUsuario) {
        descripcion = descripcion.trim();

        if (!descripcion.matches(CONTENT_AND_LENGTH_REGEX)) {
            if (descripcion.length() < 15) {
                throw new IllegalArgumentException("La petición debe contener al menos 15 caracteres.");
            }
            if (descripcion.length() > 255) {
                throw new IllegalArgumentException("La petición excede el límite de 255 caracteres.");
            }
        }

        Matcher matcher = PROHIBIDAS_PATTERN.matcher(descripcion);
        if (matcher.matches()) {
            throw new IllegalArgumentException("La petición contiene palabras prohibidas o inapropiadas.");
        }

        Usuario usuario = usuarioService.getUsuarioAutenticado(correoUsuario);

        if (yaExistePeticion(usuario.getId())) {
            throw new IllegalStateException("Ya has registrado una petición.");
        }

        Intencion intencion = new Intencion();
        intencion.setUsuario(usuario);
        intencion.setDescripcion(descripcion);
        return intencionRepository.save(intencion);
    }

    public List<Intencion> findAll() {
        return intencionRepository.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
    }
}