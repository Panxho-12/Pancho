package com.example.pancho;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pancho.Estadisticas;
import com.example.pancho.List_Equipos;
import com.example.pancho.R;

import java.util.List;

 public class ListAdapterEquipos extends RecyclerView.Adapter<ListAdapterEquipos.ViewHolder> {
    private List<List_Equipos> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    public ListAdapterEquipos(List<List_Equipos> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_equipos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
        holder.btnEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el ID
                String equipoId = mData.get(position).getName(); //
                // Iniciar el fragmento de estadísticas y pasar datos
                Estadisticas estadisticasFragment = new Estadisticas();
                Bundle bundle = new Bundle();
                bundle.putString("equipoId", equipoId);
                estadisticasFragment.setArguments(bundle);

                FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedor, estadisticasFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        View btnEstadisticas;
        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            btnEstadisticas = itemView.findViewById(R.id.EstadisticasBtn);
        }
        void bindData(final List_Equipos item){
            name.setText(item.getName());
        }
    }
     public void setItems(List<List_Equipos> items) {
         mData = items;
         notifyDataSetChanged();
     }
}