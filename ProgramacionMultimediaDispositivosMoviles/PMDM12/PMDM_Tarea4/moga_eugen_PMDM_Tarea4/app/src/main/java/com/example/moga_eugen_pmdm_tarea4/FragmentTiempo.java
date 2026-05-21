package com.example.moga_eugen_pmdm_tarea4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTiempo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTiempo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Se declaran las variables para mostrar los datos obtenidos
    private TextView Temperatura;
    private TextView Descripcion;

    private Button botonVolver;


    public FragmentTiempo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTiempo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTiempo newInstance(String param1, String param2) {
        FragmentTiempo fragment = new FragmentTiempo();
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
        return inflater.inflate(R.layout.fragment_tiempo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        botonVolver = view.findViewById(R.id.volver);
        botonVolver.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.contenedor, new FragmentInicial()).commit();
        });

        // Se inicializan las variables
        Temperatura = view.findViewById(R.id.id_temperatura);
        Descripcion = view.findViewById(R.id.id_descripcion);

        // Se llama a la funcion para obtener el tiempo
        obtenerTiempo();
    }

    // Metodo para obtener tiempo que establece la conexion con el servidor
    private void obtenerTiempo(){
        // Se crea un nuevo hilo
        new Thread(()->{
            try {
                // Se crea el objeto URL
                URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=42.8125&lon=-1.6458&appid=283f266796b4156fda3b4e1d5250a6e7&units=metric&lang=es");
                // Se abre conexion HTTP
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Se lee el flujo de entrada
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // Se escriben los datos recibidos
                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null){
                    respuesta.append(linea);
                }
                reader.close();

                procesarJSON(respuesta.toString());

            }catch (Exception e){
                requireActivity().runOnUiThread(()->
                    Toast.makeText(requireContext(), "Ha ocurrido un error de conexión", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    // Metodo para procesar datos JSON
    private void procesarJSON(String json){
        try {
            // Se convierte el archivo JSON en un objeto
            JSONObject obj = new JSONObject(json);

            // Extraer temperatura
            double temperatura = obj.getJSONObject("main").getDouble("temp");

            // Extraer descripcion
            String descripcion = obj.getJSONArray("weather").getJSONObject(0).getString("description");

            requireActivity().runOnUiThread(()->{
                Temperatura.setText("Temperatura: " + temperatura + " ºC");
                Descripcion.setText("Descripción: " +  descripcion);
            });

        } catch (Exception e){
            requireActivity().runOnUiThread(()->
                    Toast.makeText(requireContext(), "Ha ocurrido un error al procesar los datos", Toast.LENGTH_SHORT).show());
        }
    }


}