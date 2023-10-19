package com.example.subirdelevel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import kotlin.jvm.functions.Function1;

public class SubirDeLevelViewModel extends AndroidViewModel {
    SubirDeLevel subirDeLevel;

    LiveData<Integer> ejercicioLiveData;
    LiveData<String> repeticionLiveData;

    public SubirDeLevelViewModel(@NonNull Application application) {
        super(application);

        subirDeLevel = new SubirDeLevel();

        ejercicioLiveData = Transformations.switchMap(subirDeLevel.ordenLiveData, new Function1<String, LiveData<Integer>>() {
            @Override
            public LiveData<Integer> invoke(String orden) {
                MutableLiveData<Integer> resultLiveData = new MutableLiveData<>();
                String transformacion = orden.split(":")[0];
                int imagen;

                switch (transformacion) {
                    case "TRANSFORMACION0":
                    default:
                        imagen = R.drawable.base;
                        break;
                    case "TRANSFORMACION1":
                        imagen = R.drawable.ssj1;
                        break;
                    case "TRANSFORMACION2":
                        imagen = R.drawable.ssj2;
                        break;
                    case "TRANSFORMACION3":
                        imagen = R.drawable.ssj3;
                        break;
                    case "TRANSFORMACION4":
                        imagen = R.drawable.ssj4;
                        break;
                    case "TRANSFORMACION5":
                        imagen = R.drawable.ssjg;
                        break;
                    case "TRANSFORMACION6":
                        imagen = R.drawable.ssjgssj;
                        break;
                    case "TRANSFORMACION7":
                        imagen = R.drawable.ui;
                        break;
                }
                resultLiveData.setValue(imagen);
                return resultLiveData;
            }
        });


        repeticionLiveData = Transformations.switchMap(subirDeLevel.ordenLiveData, new Function1<String, LiveData<String>>() {
            @Override
            public LiveData<String> invoke(String orden) {
                MutableLiveData<String> resultLiveData = new MutableLiveData<>();
                String repeticion = orden.split(":")[1];
                resultLiveData.setValue(repeticion);
                return resultLiveData;
            }
        });
    }

    LiveData<Integer> obtenerEjercicio(){
        return ejercicioLiveData;
    }

    LiveData<String> obtenerRepeticion(){
        return repeticionLiveData;
    }
}