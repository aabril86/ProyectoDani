package com.example.proyecto;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
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

    public LiveData<List<Juego>> obtenerJuegosNuevos() {
        return appDao.obtenerJuegosNuevos();
    }
    public LiveData<List<Juego>> obtenerJuegosPopulares() {
        return appDao.obtenerJuegosPopulares();
    }
}
