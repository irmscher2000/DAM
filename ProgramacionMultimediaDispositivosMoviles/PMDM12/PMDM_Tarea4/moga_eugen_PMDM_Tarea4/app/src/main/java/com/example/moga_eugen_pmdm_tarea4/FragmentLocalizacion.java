package com.example.moga_eugen_pmdm_tarea4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLocalizacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLocalizacion extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Se crea la variable para el mapa
    private GoogleMap mMap;

    public FragmentLocalizacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLocalizacion.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLocalizacion newInstance(String param1, String param2) {
        FragmentLocalizacion fragment = new FragmentLocalizacion();
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
        return inflater.inflate(R.layout.fragment_localizacion, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Se llama al boton volver
        Button btnVolver = view.findViewById(R.id.volver);
        btnVolver.setOnClickListener(v ->{
            getParentFragmentManager().beginTransaction().replace(R.id.contenedor, new FragmentInicial()).commit();
        });

        // Se incluye el mapa en el SupportMapFragment
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.mapa);

        // Se virifica si el frament existe para evitar errores y lo llamo de forma asincrona
        if(mapFragment != null){
            mapFragment.getMapAsync(this);
        }
    }

    // Metodo que se ejecuta automaticamente cuando el mapa esta listo para usarse
    @Override
    public void onMapReady(GoogleMap googleMap){ // Recibe el objeto GoogleMap
        // Se inicializa el mapa asignando el objeto a la variable
        mMap = googleMap;

        // Se centra el mapa en Pamplona
        LatLng pamplona = new LatLng(42.8169, -1.6445);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pamplona, 15f));

        // Se añaden los marcadores
        mMap.addMarker(new MarkerOptions().position(new LatLng(42.81546,-1.64072)).title("La Olla Restaurante"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(42.81645,-1.64179)).title("Bar Gaucho"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(42.81854,-1.64332)).title("Iruñazarra"));

    }

}