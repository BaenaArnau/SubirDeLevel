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
                String personaje = orden.split(":")[2];
                int imagen;

                switch (personaje) {
                    case "PERSONAJE1":
                    default:
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
                        break;
                    case "PERSONAJE2":
                        switch (transformacion) {
                            case "TRANSFORMACION0":
                            default:
                                imagen = R.drawable.vegetaangry;
                                break;
                            case "TRANSFORMACION1":
                                imagen = R.drawable.vegetasupersaiyan;
                                break;
                            case "TRANSFORMACION2":
                                imagen = R.drawable.vegetassj2;
                                break;
                            case "TRANSFORMACION3":
                                imagen = R.drawable.vegetassj3;
                                break;
                            case "TRANSFORMACION4":
                                imagen = R.drawable.vegetassj4;
                                break;
                            case "TRANSFORMACION5":
                                imagen = R.drawable.vegetagod;
                                break;
                            case "TRANSFORMACION6":
                                imagen = R.drawable.vegetablue;
                                break;
                            case "TRANSFORMACION7":
                                imagen = R.drawable.vegetablueevolution;
                                break;
                        }break;
                    case "PERSONAJE3":
                        switch (transformacion) {
                            case "TRANSFORMACION0":
                            default:
                                imagen = R.drawable.vegito;
                                break;
                            case "TRANSFORMACION1":
                                imagen = R.drawable.vegitossj;
                                break;
                            case "TRANSFORMACION2":
                                imagen = R.drawable.vegettossj2;
                                break;
                            case "TRANSFORMACION3":
                                imagen = R.drawable.vegetassj3;
                                break;
                            case "TRANSFORMACION4":
                                imagen = R.drawable.vegettossj4;
                                break;
                            case "TRANSFORMACION5":
                                imagen = R.drawable.vegitogod;
                                break;
                            case "TRANSFORMACION6":
                                imagen = R.drawable.vegitoblue;
                                break;
                            case "TRANSFORMACION7":
                                imagen = R.drawable.vegitoui;
                                break;
                        }
                        break;
                    case "PERSONAJE4":
                        switch (transformacion) {
                            case "TRANSFORMACION0":
                            default:
                                imagen = R.drawable.gogeta;
                                break;
                            case "TRANSFORMACION1":
                                imagen = R.drawable.gogetassj;
                                break;
                            case "TRANSFORMACION2":
                                imagen = R.drawable.gogetassj2;
                                break;
                            case "TRANSFORMACION3":
                                imagen = R.drawable.gogetassj3;
                                break;
                            case "TRANSFORMACION4":
                                imagen = R.drawable.gogetassj4;
                                break;
                            case "TRANSFORMACION5":
                                imagen = R.drawable.gogetagod;
                                break;
                            case "TRANSFORMACION6":
                                imagen = R.drawable.gogetablue;
                                break;
                            case "TRANSFORMACION7":
                                imagen = R.drawable.gogetaui;
                                break;
                        }
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