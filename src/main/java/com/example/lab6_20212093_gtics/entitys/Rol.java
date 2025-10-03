package com.example.lab6_20212093_gtics.entitys;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Rol {

    public enum todosRoles{
        ADMIN,
        USUARIO,
        VISITANTE,

    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private todosRoles nombre;

    public Rol() {}

    public Rol(todosRoles nombre) {
        this.nombre = nombre;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public todosRoles getNombre(){
        return nombre;
    }

    public void setNombre(todosRoles nombre){
        this.nombre = nombre;
    }
}



