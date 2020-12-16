package com.example.proyecto;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Juego {
    @PrimaryKey(autoGenerate = true)
            int id;

    String titulo;
    String year;
    String desarrollador;
    String imagen;

    public Juego(String titulo, String year, String imagen) {
        this.titulo = titulo;
        this.year = year;
        this.imagen = imagen;
    }
}
