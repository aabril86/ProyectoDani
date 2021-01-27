package com.example.proyecto;

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
import com.example.proyecto.databinding.FragmentJuegoReviewBinding;


public class JuegoReviewFragment extends Fragment {

    private FragmentJuegoReviewBinding binding;
    private NavController navController;
    private JuegosViewModel juegosViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentJuegoReviewBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        juegosViewModel = new ViewModelProvider(requireActivity()).get(JuegosViewModel.class);

        juegosViewModel.seleccionado().observe(getViewLifecycleOwner(), juego -> {
            Glide.with(requireView()).load(juego.banner).into(binding.bannerjuego);
        });

        binding.general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_juegoReviewFragment_to_juegoFragment);
            }
        });

        binding.arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_juegoReviewFragment_to_listaJuegosFragment);
            }
        });


    }
}