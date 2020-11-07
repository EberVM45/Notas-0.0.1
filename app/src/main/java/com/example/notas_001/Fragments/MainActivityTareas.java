package com.example.notas_001.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.notas_001.R;
import com.example.notas_001.activityAgregarTarea;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainActivityTareas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainActivityTareas extends Fragment {
    Button BotonActivityTareas;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainActivityTareas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainActivityTareas.
     */
    // TODO: Rename and change types and number of parameters
    public static MainActivityTareas newInstance(String param1, String param2) {
        MainActivityTareas fragment = new MainActivityTareas();
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
        View view=inflater.inflate(R.layout.fragment_main_activity_tareas,container,false);
        BotonActivityTareas=(Button) view.findViewById(R.id.btnAgregarTarea);
        BotonActivityTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),activityAgregarTarea.class);
                intent.putExtra("msj","Ventana Tareas");
                startActivity(intent);
            }
        });
        return view;
    }
}