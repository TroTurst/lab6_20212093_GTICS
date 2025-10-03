package com.example.lab6_20212093_gtics.services;


import com.example.lab6_20212093_gtics.entitys.HeroeNaval;
import com.example.lab6_20212093_gtics.repositorys.HeroeNavalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class HeroeNavalService {

    @Autowired
    private HeroeNavalRepository heroeNavalRepository;

    private static final String TEXTO_LIMPIO_REGEX = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
    private static final Pattern TEXTO_LIMPIO_PATTERN = Pattern.compile(TEXTO_LIMPIO_REGEX);

    public List<HeroeNaval> findAllHeroes() {
        return heroeNavalRepository.findAll();
    }

    public HeroeNaval saveHeroe(HeroeNaval heroe) {
        if (!TEXTO_LIMPIO_PATTERN.matcher(heroe.getNombre()).matches()) {
            throw new IllegalArgumentException("El nombre del héroe solo debe contener letras y espacios.");
        }

        if (heroe.getRango() != null && !heroe.getRango().trim().isEmpty()) {
            if (!TEXTO_LIMPIO_PATTERN.matcher(heroe.getRango()).matches()) {
                throw new IllegalArgumentException("El rango solo debe contener letras y espacios.");
            }
        }

        if (!TEXTO_LIMPIO_PATTERN.matcher(heroe.getPais()).matches()) {
            throw new IllegalArgumentException("El país solo debe contener letras y espacios.");
        }

        return heroeNavalRepository.save(heroe);
    }

    public Optional<HeroeNaval> findHeroeById(Long id) {
        return heroeNavalRepository.findById(id);
    }

    public void deleteHeroe(Long id) {
        heroeNavalRepository.deleteById(id);
    }
}