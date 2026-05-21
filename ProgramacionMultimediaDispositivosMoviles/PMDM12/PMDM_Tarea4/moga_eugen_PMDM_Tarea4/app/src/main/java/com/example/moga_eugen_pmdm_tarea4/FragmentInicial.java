package com.example.moga_eugen_pmdm_tarea4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInicial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInicial extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentInicial() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInicial.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInicial newInstance(String param1, String param2) {
        FragmentInicial fragment = new FragmentInicial();
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
        return inflater.inflate(R.layout.fragment_inicial, container, false);
    }

    @Override
    public void  onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Se buscan las imagenes del archivo xml
        ImageView imagen1 = view.findViewById(R.id.img_anchoas);
        ImageView imagen2 = view.findViewById(R.id.img_chipirones);
        ImageView imagen3 = view.findViewById(R.id.img_solomillo);

        // Se establece que al hacer clic abra el fragmento detalle
        imagen1.setOnClickListener(v -> abrirDetalle(1));
        imagen2.setOnClickListener(v -> abrirDetalle(2));
        imagen3.setOnClickListener(v -> abrirDetalle(3));
    }

    // Metodo para abrir fragmento detalle
    private void abrirDetalle(int id){
        FragmentDetalle detalle = new FragmentDetalle();

        // Se crea el Bundle para guardar el numero de imagen pulsado
        Bundle args = new Bundle();
        args.putInt("pincho", id);
        detalle.setArguments(args);

        // Se cambia el fragment que se visualiza
        getParentFragmentManager().beginTransaction().replace(R.id.contenedor, detalle).commit();
    }
}