package com.example.proyecto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyecto.databinding.FragmentJuegoBinding;

import java.util.concurrent.atomic.AtomicReference;


public class JuegoFragment extends Fragment {

    private FragmentJuegoBinding binding;
    private NavController navController;
    private JuegosViewModel juegosViewModel;
    private String url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentJuegoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        juegosViewModel = new ViewModelProvider(requireActivity()).get(JuegosViewModel.class);
        juegosViewModel.seleccionado().observe(getViewLifecycleOwner(), juego -> {
            binding.descripcionjuego.setText(juego.descripcion);
            binding.platform.setText(juego.plataforma);
            binding.fecha.setText(juego.year);
            binding.developer.setText(juego.desarrollador);
            Glide.with(requireView()).load(juego.imagen).into(binding.portadajuego);
            Glide.with(requireView()).load(juego.banner).into(binding.bannerjuego);
            url = juego.url;
        });

        binding.goToUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }

}