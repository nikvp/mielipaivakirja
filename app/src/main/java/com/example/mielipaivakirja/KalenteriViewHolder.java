package com.example.mielipaivakirja;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KalenteriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayofMonth;
    private final KalenteriAdapter.OnItemListener onItemListener;

    public KalenteriViewHolder(@NonNull View itemView, KalenteriAdapter.OnItemListener onItemListener) {

        super(itemView);
        this.dayofMonth = itemView.findViewById(R.id.cellDaytxt);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(),  (String) dayofMonth.getText());
    }
}
