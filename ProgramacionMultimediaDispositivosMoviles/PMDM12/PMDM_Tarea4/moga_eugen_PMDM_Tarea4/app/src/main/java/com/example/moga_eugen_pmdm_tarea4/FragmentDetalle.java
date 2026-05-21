package com.example.moga_eugen_pmdm_tarea4;

import static android.content.Context.SENSOR_SERVICE;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetalle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetalle extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button botonVolver;

    // Declaracion de variables para manejar los sensores
    private SensorManager mSensorManager;
    private Sensor sensorProximidad;
    private SensorEventListener escuchadorSensorProximidad;


    public FragmentDetalle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDetalle.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetalle newInstance(String param1, String param2) {
        FragmentDetalle fragment = new FragmentDetalle();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Se buscan los elementos del layout
        ImageView imagen = view.findViewById(R.id.imagen);
        TextView nombre = view.findViewById(R.id.nombre);
        TextView descripcion = view.findViewById(R.id.descripcion);

        // Se recupera el dato del valor seleccionado en la imagen FragmentInicial
        int pincho = getArguments().getInt("pincho", 1);

        // Se cambia segun la imagen escogida
        if(pincho == 1){
            imagen.setImageResource(R.drawable.anchoas);
            nombre.setText(R.string.la_olla);
            descripcion.setText(R.string.descripcion_anchoas);
        } else if (pincho == 2) {
            imagen.setImageResource(R.drawable.chipirones);
            nombre.setText(R.string.bar_gaucho);
            descripcion.setText(R.string.descripcion_chipirones);
        } else {
            imagen.setImageResource(R.drawable.solomillo);
            nombre.setText(R.string.iruñazarra);
            descripcion.setText(R.string.descripcion_solomillo);
        }

        // Se configura el boton para volver
        botonVolver = view.findViewById(R.id.volver);
        botonVolver.setOnClickListener(v -> getParentFragmentManager().beginTransaction().replace(R.id.contenedor, new FragmentInicial()).commit());

        // Inicializo las variables para el sensor de proximidad
        mSensorManager = (SensorManager)requireContext().getSystemService(SENSOR_SERVICE);

        // Se obtiene el sensor de proximidad
        sensorProximidad = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Si no hay sensor de proximidad de proximidad se muestra el aviso
        if (sensorProximidad == null){
            Toast.makeText(requireContext(), "Sensor de proximidad no disponible", Toast.LENGTH_SHORT).show();
        }

        // Se inicializa el escuchador
        escuchadorSensorProximidad = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                // Logica al detectar el cambio del sensor
                if (event.values[0] < 1){
                    view.setBackgroundColor(Color.GREEN);
                } else {
                    view.setBackgroundColor(Color.RED);
                }
            }
        };

    }


    // Configurar que el escuchard funcione solo cuando el fragment este activo
    @Override
    public void onResume (){
        super.onResume();
        if (sensorProximidad != null){
            mSensorManager.registerListener(escuchadorSensorProximidad, sensorProximidad, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    // Indico que el sensor deje de escuchar si el fragment no esta visible
    @Override
    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(escuchadorSensorProximidad);
    }

}