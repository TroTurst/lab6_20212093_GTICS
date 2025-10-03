package com.example.lab6_20212093_gtics.entitys;

import com.example.lab6_20212093_gtics.entitys.*;
import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private TodosLosRoles nombre;

    public Rol() {}

    public Rol(TodosLosRoles nombre) {
        this.nombre = nombre;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TodosLosRoles getNombre(){
        return nombre;
    }

    public void setNombre(TodosLosRoles nombre){
        this.nombre = nombre;
    }
}



