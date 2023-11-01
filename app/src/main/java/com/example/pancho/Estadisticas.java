package com.example.pancho;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class Estadisticas extends Fragment {
    private TextView textViewVictorias;
    private TextView textViewDerrotas;
    private TextView textViewEmpates;
    private TextView textViewPosicionLiga;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Estadisticas() {
        // Required empty public constructor
    }

    public static Estadisticas newInstance(String param1, String param2) {
        Estadisticas fragment = new Estadisticas();
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
        View rootView = inflater.inflate(R.layout.fragment_estadisticas, container, false);
            textViewVictorias = rootView.findViewById(R.id.textViewVictorias);
            textViewDerrotas = rootView.findViewById(R.id.textViewDerrotas);
            textViewEmpates = rootView.findViewById(R.id.textViewEmpates);
            textViewPosicionLiga = rootView.findViewById(R.id.textViewPosicionLiga);
            Bundle args = getArguments();
            if (args != null){
                String nombreEquipo = args.getString("equipoId");
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference estadisticasRef = db.collection("estadisticas");

                estadisticasRef.whereEqualTo("nombreEquipo", nombreEquipo)
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                String victorias = documentSnapshot.getString("victorias");
                                String derrotas = documentSnapshot.getString("derrotas");
                                String empates = documentSnapshot.getString("empate");
                                String posicion = documentSnapshot.getString("posicion");

                                // Usar los datos recuperados para mostrarlos en tus TextViews
                                textViewVictorias.setText("Victorias: " + victorias);
                                textViewDerrotas.setText("Derrotas: " + derrotas);
                                textViewEmpates.setText("Empates: " + empates);
                                textViewPosicionLiga.setText("Posición en la Liga: " + posicion);
                                Log.d("TAG", "DocumentSnapshot data: " + documentSnapshot.getData());
                            }
                        })
                        .addOnFailureListener(e -> {
                            //
                        });
            }
            return rootView;
    }
}