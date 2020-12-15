package com.example.proyecto;

import androidx.room.Entity;

@Entity
public class Juego {

    String titulo;
    String year;
    String desarrollador;
    String imagen;

    public Juego(String titulo, String year, String desarrollador, String imagen) {
        this.titulo = titulo;
        this.year = year;
        this.desarrollador = desarrollador;
        this.imagen = imagen;
    }
}
