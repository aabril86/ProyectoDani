package com.example.proyecto;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class JuegosViewModel extends AndroidViewModel {

    private final JuegoStorage juegoStorage;
    MutableLiveData<Uri> imagenSeleccionada = new MutableLiveData<>();

    public JuegosViewModel(@NonNull Application application) {
        super(application);

        juegoStorage = new JuegoStorage(application);
    }

    void establecerImagenSeleccionada(Uri uri) {
        imagenSeleccionada.setValue(uri);
    }

    void insertar(String titulo, String anyo, String imagen){
        juegoStorage.insertar(titulo, anyo, imagen);
    }
}
