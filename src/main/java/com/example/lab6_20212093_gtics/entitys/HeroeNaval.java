package com.example.lab6_20212093_gtics.entitys;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "heroes_navales")
public class HeroeNaval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String rango;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String reseña;

    private String pais;

    public HeroeNaval(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getRango(){
        return rango;
    }

    public void setRango(String rango){
        this.rango = rango;
    }

    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getReseña(){
        return reseña;
    }

    public void setReseña(String reseña){
        this.reseña = reseña;
    }

    public String getPais(){
        return pais;
    }

    public void setPais(String pais){
        this.pais = pais;
    }
}
