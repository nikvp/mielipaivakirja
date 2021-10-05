package com.example.mielipaivakirja;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KalenteriAdapter extends RecyclerView.Adapter<KalenteriViewHolder>{

    private final ArrayList<String> paivat;
    private final OnItemListener onItemListener;

    public KalenteriAdapter(ArrayList<String> paivat, OnItemListener onItemListener) {
        this.paivat = paivat;

        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public KalenteriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.kalenteri_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);

        return new KalenteriViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull KalenteriViewHolder holder, int position) {
        holder.dayofMonth.setText(paivat.get(position));

    }

    @Override
    public int getItemCount() {

        return paivat.size();
    }

    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }
}
