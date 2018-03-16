package com.scleroid.financematic;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by scleroid on 5/3/18.
 */

public class Adapter_passbook extends RecyclerView.Adapter<Adapter_passbook.MyViewHolder>  {

    private List<Passbook> passbookList;

    public class MyViewHolder extends RecyclerView.ViewHolder  {
/* for selecte row and chnage color        implements View.OnClickListener*/
        public TextView passbook_taken_money,passbook_name, passbook_date,passbook_received_money;
        private SparseBooleanArray selectedItems = new SparseBooleanArray();
        public MyViewHolder(View view)  {
            super(view);
           /* view.setOnClickListener(this);*/
            passbook_date= (TextView) view.findViewById(R.id.passbook_date);
            passbook_name = (TextView) view.findViewById(R.id.passbook_name);
            passbook_taken_money = (TextView) view.findViewById(R.id.passbook_taken_money);
            passbook_received_money = (TextView) view.findViewById(R.id.passbook_received_money);

        }



      /*  @Override
        public void onClick(View view) {
            if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                view.setSelected(false);
            }
            else {
                selectedItems.put(getAdapterPosition(), true);
                view.setSelected(true);
            }
        }*/


    }


    public Adapter_passbook(List<Passbook> passbookList) {
        this.passbookList = passbookList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_passbook_row, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Passbook passbook = passbookList.get(position);


        holder.passbook_name.setText(passbook .getPassbook_name());
        holder.passbook_date.setText(passbook .getPassbook_date());
        holder.passbook_taken_money.setText(passbook.getPassbook_taken_money());
        holder.passbook_received_money.setText(passbook.getPassbook_received_money());
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#d5e5f0"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
    }

    @Override
    public int getItemCount() {
        return passbookList.size();
    }
}
