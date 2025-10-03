package com.example.lab6_20212093_gtics.services;


import com.example.lab6_20212093_gtics.repositorys.HeroeNavalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroeNavalService {

    @Autowired
    private HeroeNavalRepository heroeNavalRepository;
}
