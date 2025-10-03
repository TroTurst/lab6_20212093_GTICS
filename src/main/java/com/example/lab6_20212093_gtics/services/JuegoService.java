package com.example.lab6_20212093_gtics.services;

import org.springframework.stereotype.Service;

@Service
public class JuegoService {


    public String compararCancion(String tituloObjetivo, String intento) {
        String target = tituloObjetivo.toUpperCase().replaceAll("\\s", "");
        String guess = intento.toUpperCase().replaceAll("\\s", "");
        StringBuilder result = new StringBuilder();

        if (guess.length() > target.length()) {
            guess = guess.substring(0, target.length());
        }

        for (int i = 0; i < target.length(); i++) {
            if (i >= guess.length()) {
                result.append("-");
            } else if (guess.charAt(i) == target.charAt(i)) {
                result.append("A");
            } else if (target.contains(String.valueOf(guess.charAt(i)))) {
                result.append("C");
            } else {
                result.append("X");
            }
        }
        return result.toString();
    }
}