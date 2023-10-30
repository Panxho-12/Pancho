package com.example.pancho;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Equipos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Equipos extends Fragment {

    private List<List_Equipos> equipos;
    private ListAdapterEquipos listAdapter;
    private RecyclerView recyclerView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Equipos() {
        // Required empty public constructor
    }

    public static Equipos newInstance(String param1, String param2) {
        Equipos fragment = new Equipos();
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
        View rootView = inflater.inflate(R.layout.fragment_equipos, container, false);
        recyclerView = rootView.findViewById(R.id.listRecycleView);

        // Configura el RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ObtenerEquipos();
        return rootView;
    }

    public void ObtenerEquipos(){
        equipos = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference equiposRef = db.collection("equipos");

        equiposRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                        List_Equipos equipo = documentSnapshot.toObject(List_Equipos.class);
                        equipos.add(equipo);
                    }
                    listAdapter = new ListAdapterEquipos(equipos,getContext());

                    recyclerView.setAdapter(listAdapter);
                    listAdapter.setItems(equipos);

                }else{
                    //No se encontraron datos
                }
            }
        });
    }
}