package com.enes.notlar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class adaptır  extends RecyclerView.Adapter<adaptır.cardviewtutucu> {
    private Context mcontex ;
    private List<defterler> defterList;
    private veri vt;

    public adaptır(Context mcontex, List<defterler> defterList, veri vt) {
        this.mcontex = mcontex;
        this.defterList = defterList;
        this.vt = vt;
    }

    @NonNull
    @Override
    public adaptır.cardviewtutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
            return new  cardviewtutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptır.cardviewtutucu holder, int position) {
        final defterler defterlo = defterList.get(position);

        holder.textyazii.setText(defterlo.getBaslik());
        holder.texttarihh.setText(defterlo.getTarih());
        holder.textViewsaat.setText(defterlo.getSaat());




        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcontex, detaylariGoster.class);
                intent.putExtra("nesne",defterlo);
                mcontex.startActivity(intent);
            }
        });



        holder.btnsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new defterdao().sil(vt,defterlo.getDefter_id());
                defterList= new defterdao().tumdefterler(vt);
                Snackbar.make(v,"silindi",Snackbar.LENGTH_LONG).show();
                notifyDataSetChanged();
            }
        });




    }

    @Override
    public int getItemCount() {
        return defterList.size();
    }

    public class cardviewtutucu extends RecyclerView.ViewHolder{
        private TextView textyazii;
        private TextView texttarihh;
        private TextView textViewsaat;
        private CardView cardView;
        private Button btnsil;




        public cardviewtutucu(@NonNull View itemView) {
            super(itemView);

            textyazii = itemView.findViewById(R.id.textyazi);
            texttarihh = itemView.findViewById(R.id.texttarih);
            cardView = itemView.findViewById(R.id.cardview);
            btnsil = itemView.findViewById(R.id.btnsil);
            textViewsaat = itemView.findViewById(R.id.textsaat);




        }
    }
}
