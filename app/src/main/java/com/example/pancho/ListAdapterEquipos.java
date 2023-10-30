package com.example.pancho;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;




public class ListAdapterEquipos extends RecyclerView.Adapter<ListAdapterEquipos.ViewHolder> {

    private List<List_Equipos> mData;
    private LayoutInflater mImflater;
    private Context context;

    public ListAdapterEquipos(List<List_Equipos> itemList, Context context){
        this.mImflater = LayoutInflater .from(context);
        this.context = context;
        this.mData = itemList;
    }
    @Override
    public int getItemCount() {return mData.size();}

    @Override
    public ListAdapterEquipos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mImflater.inflate(R.layout.list_equipos, null);
        return new ListAdapterEquipos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterEquipos.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<List_Equipos> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name;
        ViewHolder(View itemView){
           super(itemView);
           name = itemView.findViewById(R.id.nameTextView);
        }
        void bindData(final List_Equipos item){
            name.setText(item.getName());
        }
    }

    //dfbsjtdyjdtyjdtykdukduduk
}
