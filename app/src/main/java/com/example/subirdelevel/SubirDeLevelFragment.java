package com.example.subirdelevel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.example.subirdelevel.databinding.FragmentSubirDeLevelBinding;

public class SubirDeLevelFragment extends Fragment {

    private FragmentSubirDeLevelBinding binding;
    private SubirDeLevelViewModel subirDeLevelViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSubirDeLevelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subirDeLevelViewModel = new ViewModelProvider(this).get(SubirDeLevelViewModel.class);

        subirDeLevelViewModel.obtenerEjercicio().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer ejercicio) {
                Glide.with(requireContext()).load(ejercicio).into(binding.nivel);
            }
        });

        subirDeLevelViewModel.obtenerRepeticion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String repeticion) {
                if(repeticion.equals("CAMBIO")){
                    binding.SubirElNivel.setVisibility(View.VISIBLE);
                } else {
                    binding.SubirElNivel.setVisibility(View.GONE);
                }
                binding.sayaying.setText(repeticion);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

