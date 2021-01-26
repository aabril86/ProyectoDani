package com.example.proyecto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto.databinding.FragmentListaJuegosBinding;
import com.example.proyecto.databinding.ViewholdernewJuegoBinding;
import com.example.proyecto.databinding.ViewholderpopularJuegoBinding;

import java.util.List;


public class ListaJuegosFragment extends Fragment {

    private FragmentListaJuegosBinding binding;
    private JuegosViewModel juegosViewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return (binding = FragmentListaJuegosBinding.inflate(inflater, container, false)).getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //AQUI VA EL CODIGO
        navController = Navigation.findNavController(view);

        juegosViewModel = new ViewModelProvider(requireActivity()).get(JuegosViewModel.class);

        binding.irAInsertarJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });


        JuegosNewAdapter juegosNewAdapter = new JuegosNewAdapter();
        binding.recyclerViewNew.setAdapter(juegosNewAdapter);

        juegosViewModel.obtenerJuegosNuevos().observe(getViewLifecycleOwner(), albums -> {
            juegosNewAdapter.setAlbumList(albums);
        });

        JuegosPopularAdapter juegosPopularesAdapter = new JuegosPopularAdapter();
        binding.recyclerViewPopular.setAdapter(juegosPopularesAdapter);

        juegosViewModel.obtenerJuegosPopulares().observe(getViewLifecycleOwner(), albums -> {
            juegosPopularesAdapter.setAlbumList(albums);
        });
    }


    class JuegosNewAdapter extends RecyclerView.Adapter<JuegoNewViewHolder> {

        List<Juego> albumList;


        @NonNull
        @Override
        public JuegoNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JuegoNewViewHolder(ViewholdernewJuegoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull JuegoNewViewHolder holder, int position) {
            Juego album = albumList.get(position);

            Glide.with(holder.itemView).load(album.imagen).into(holder.binding.portada);

            holder.itemView.setOnClickListener(v -> {
                juegosViewModel.seleccionar(album);
                navController.navigate(R.id.juegoFragment);
            });
        }

        @Override
        public int getItemCount() {
            return albumList == null ? 0 : albumList.size();
        }

        public void setAlbumList(List<Juego> albumList) {
            this.albumList = albumList;
            notifyDataSetChanged();
        }
    }

    class JuegosPopularAdapter extends RecyclerView.Adapter<JuegoPopularViewHolder> {

        List<Juego> albumList;


        @NonNull
        @Override
        public JuegoPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JuegoPopularViewHolder(ViewholderpopularJuegoBinding.inflate(getLayoutInflater(), parent, false));
        }


        @Override
        public void onBindViewHolder(@NonNull JuegoPopularViewHolder holder, int position) {
            Juego album = albumList.get(position);

            holder.binding.gametitle.setText(album.titulo);
            holder.binding.plataforma.setText(album.plataforma);
            Glide.with(holder.itemView).load(album.imagen).into(holder.binding.gameicon);

            holder.itemView.setOnClickListener(v -> {
                juegosViewModel.seleccionar(album);
                navController.navigate(R.id.juegoFragment);
            });
        }

        @Override
        public int getItemCount() {
            return albumList == null ? 0 : albumList.size();
        }

        public void setAlbumList(List<Juego> albumList) {
            this.albumList = albumList;
            notifyDataSetChanged();
        }
    }


    static class JuegoNewViewHolder extends RecyclerView.ViewHolder {
        ViewholdernewJuegoBinding binding;

        public JuegoNewViewHolder(@NonNull ViewholdernewJuegoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    static class JuegoPopularViewHolder extends RecyclerView.ViewHolder {
        ViewholderpopularJuegoBinding binding;

        public JuegoPopularViewHolder(@NonNull ViewholderpopularJuegoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}