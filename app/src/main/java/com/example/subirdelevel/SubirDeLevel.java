package com.example.subirdelevel;

import static java.util.concurrent.TimeUnit.SECONDS;

import androidx.lifecycle.LiveData;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class SubirDeLevel {

    interface SubirListener {
        void cuandoDeLaOrden(String orden);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> subida;

    void iniciarSubida(SubirListener subirListener) {
        if (subida == null || subida.isCancelled()) {
            subida = scheduler.scheduleAtFixedRate(new Runnable() {
                int nivel = -1;
                int repeticiones = -1;

                int aleatorio = 1;

                @Override
                public void run() {
                    if (repeticiones < 0) {
                        repeticiones = 3;
                        nivel++;
                        if (nivel == 3){
                            nivel = 0;
                            aleatorio = (int)(Math.random() * 3.0) +1;
                        }
                    }

                    subirListener.cuandoDeLaOrden("TRANSFORMACION" + nivel + ":" + (repeticiones == 0 ? "Trasfromacion" : repeticiones) + ":PERSONAJE" + aleatorio);
                    repeticiones--;
                }
            }, 0, 1, SECONDS);
        }
    }

    void pararSubir() {
        if (subida != null) {
            subida.cancel(true);
        }
    }

    LiveData<String> ordenLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            iniciarSubida(new SubirListener() {
                @Override
                public void cuandoDeLaOrden(String orden) {
                    postValue(orden);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            pararSubir();
        }
    };
}

