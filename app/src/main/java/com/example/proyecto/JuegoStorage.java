package com.example.proyecto;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JuegoStorage {

    private final AppBaseDeDatos.AppDao appDao;
    Executor executor = Executors.newSingleThreadExecutor();

    JuegoStorage(Application application){
        appDao = AppBaseDeDatos.getInstance(application).obtenerJuegosDao();
    }

    void insertar(String titulo, String anyo, String imagen){
        executor.execute(()->{
            appDao.insertarJuego(new Juego(titulo, anyo, imagen));


        });

    }
}
