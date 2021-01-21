package com.example.proyecto;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyecto.databinding.FragmentInsertarJuegosBinding;
import com.example.proyecto.databinding.FragmentListaJuegosBinding;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static androidx.core.content.ContextCompat.checkSelfPermission;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;


public class InsertarJuegosFragment extends Fragment {

   FragmentInsertarJuegosBinding binding;
   private JuegosViewModel juegosViewModel;
   Uri imagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentInsertarJuegosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        juegosViewModel = new ViewModelProvider(requireActivity()).get(JuegosViewModel.class);

        binding.seleccionarPortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzadorGaleria.launch("image/*");
            }
        });

        juegosViewModel.imagenSeleccionada.observe(getViewLifecycleOwner(), uri -> {

            if(uri != null){
                imagen = uri;
                Glide.with(requireView()).load(uri).into(binding.previsualizarPortada);
            }
        });

        binding.insertar.setOnClickListener(v -> {

            String titulo = binding.titulo.getText().toString();
            String anyo = binding.anyo.getText().toString();
            String plataforma = binding.plataforma.getText().toString();
            juegosViewModel.insertar(titulo, anyo, plataforma ,imagen.toString());

            navController.popBackStack();
            juegosViewModel.establecerImagenSeleccionada(null);
        });
    }

    private final ActivityResultLauncher<String> lanzadorGaleria =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                juegosViewModel.establecerImagenSeleccionada(uri);
            });

}