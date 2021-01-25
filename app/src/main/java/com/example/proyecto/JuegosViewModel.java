package com.example.proyecto;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class JuegosViewModel extends AndroidViewModel {

    private final JuegoStorage juegoStorage;

    //imagen del insert
    MutableLiveData<Uri> imagenSeleccionada = new MutableLiveData<>();

    //del splash
    MutableLiveData<Boolean> finishedLoading = new MutableLiveData<>();

    //Para el juegoFragment
    MutableLiveData<Juego> juegoSeleccionado = new MutableLiveData<>();

    public JuegosViewModel(@NonNull Application application) {
        super(application);

        juegoStorage = new JuegoStorage(application);
    }

    void establecerImagenSeleccionada(Uri uri) {
        imagenSeleccionada.setValue(uri);
    }

    void insertar(String titulo, String anyo, String plataforma, String imagen){
        juegoStorage.insertar(titulo, anyo, plataforma, imagen);
    }
    void seleccionar (Juego juego){
        juegoSeleccionado.setValue(juego);
    }

    LiveData<List<Juego>> obtenerJuegosNuevos(){
        return juegoStorage.obtenerJuegosNuevos();
    }
    LiveData<List<Juego>> obtenerJuegosPopulares(){
        return juegoStorage.obtenerJuegosPopulares();
    }

    MutableLiveData<Juego> seleccionado() { return juegoSeleccionado; }
}
