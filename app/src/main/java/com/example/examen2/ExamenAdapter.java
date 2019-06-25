package com.example.examen2;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class ExamenAdapter extends RecyclerView.Adapter<ExamenAdapter.ViewHolder> {

    private List<ExamenModel> examenModelList;

    public ExamenAdapter(List<ExamenModel> examenModelList) {
        this.examenModelList = examenModelList;
    }

    //Crea la celda e instancia el aspecto de la celda y devuelve el ViewHolder con el layout seteado
    //TODO el holder simpre recibe el xml de la celda.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.examen_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public void onItemDismiss(int position){
        examenModelList.remove(position);
        notifyDataSetChanged();
    }

    public void onItemMove(int position_dragged, int position_target){
        Collections.swap(examenModelList,position_dragged,position_target);
        notifyItemMoved(position_dragged,position_target);

    }

    public void removeItem(int position) {
        examenModelList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(ExamenModel item, int position){
        if(examenModelList.isEmpty()) {
            examenModelList.add(0,item);
            notifyItemInserted(0);
        }
        else{
            examenModelList.add(position,item);
            notifyItemInserted(position);
        }
        notifyDataSetChanged();
    }


    // Metodo que se encargua de establecer los objetos en el ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = examenModelList.get(position).getTitle();
        String subtitle = examenModelList.get(position).getSubtitle();

        holder.title.setText(title);
        holder.subtitle.setText(subtitle);
    }

    //Cuantas celdas va a tener nuestra vista
    @Override
    public int getItemCount() {
        return examenModelList.size();
    }

    //Generamos el text view que se a devolver el ViewHolder de la funcion onCreateViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView subtitle;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.textViewCellTitle);
            subtitle = v.findViewById(R.id.textViewCellSubtitle);
        }

    }

}



