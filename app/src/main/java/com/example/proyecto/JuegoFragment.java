package com.example.proyecto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyecto.databinding.FragmentJuegoBinding;

import java.util.List;


public class JuegoFragment extends Fragment {

    private FragmentJuegoBinding binding;
    private NavController navController;
    private JuegosViewModel juegosViewModel;

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
            binding.descripcionjuego.setText(juego.titulo);
            binding.aboutgame.setText(juego.plataforma);
            Glide.with(requireView()).load(juego.imagen).into(binding.portadajuego);

        });
    }

}