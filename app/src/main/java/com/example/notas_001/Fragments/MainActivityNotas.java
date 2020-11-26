package com.example.notas_001.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.notas_001.R;
import com.example.notas_001.activityAgregarNota;
import com.example.notas_001.datos.daoNota;
import com.example.notas_001.verNotaSeleccionada;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainActivityNotas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainActivityNotas extends Fragment {
    Button BotonActivityNotas;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;

    public MainActivityNotas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainActivityF.
     */
    // TODO: Rename and change types and number of parameters
    public static MainActivityNotas newInstance(String param1, String param2) {
        MainActivityNotas fragment = new MainActivityNotas();
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
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        BotonActivityNotas = (Button) view.findViewById(R.id.btnAgregarNota);
        recycleView = (RecyclerView) view.findViewById(R.id.recycleNotas);
        layoutManager = new GridLayoutManager(getContext(), 1);
        recycleView.setLayoutManager(layoutManager);
        adaptadorNotas adaptador = new adaptadorNotas(Objects.requireNonNull(getContext()),
                Objects.requireNonNull(new daoNota(getContext()).getAll()));
        adaptador.setOnClickListener((item) -> {
            int a = (recycleView.getChildAdapterPosition(item)) + 1;
            Intent intent = new Intent(getActivity(),verNotaSeleccionada.class);
            intent.putExtra("idNota",a);
            startActivity(intent);
        });
        BotonActivityNotas.setOnClickListener(v -> {
            Intent in = new Intent(getActivity(), activityAgregarNota.class);
            in.putExtra("cosas", "Ventana Notas");
            startActivity(in);
        });
        recycleView.setAdapter(adaptador);
        return view;
    }
}