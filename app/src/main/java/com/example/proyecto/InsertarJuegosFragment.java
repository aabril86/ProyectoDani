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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentInsertarJuegosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        juegosViewModel = new ViewModelProvider(requireActivity()).get(JuegosViewModel.class);

        binding.seleccionarPortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(requireContext(), READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED) {
                    abrirGaleria();
                } else {
                    lanzadorPermisos.launch(READ_EXTERNAL_STORAGE);
                }
            }
        });

        juegosViewModel.imagenSeleccionada.observe(getViewLifecycleOwner(), uri -> {
            Glide.with(requireView()).load(uri).into(binding.previsualizarPortada);
        });
    }

    private final ActivityResultLauncher<String> lanzadorPermisos =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    abrirGaleria();
                }
            });

    private final ActivityResultLauncher<String> lanzadorGaleria =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {

                juegosViewModel.establecerImagenSeleccionada(uri);
            });

    private void abrirGaleria(){
        lanzadorGaleria.launch("image/*");
    }
}