package com.example.recycler_view_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// step-8: ceating a custome adaper class  for recycler to hold the data.name as RecycleContactAdapter.java
// this class must have to extends the RecyclerView.Adapter<>
public class RecycleContactAdapter  extends RecyclerView.Adapter<RecycleContactAdapter.viewHolder> {

    //--------------------Step-9: write the code.  ---------------------------------
    // creating object for context to pass the construct of this class
    Context context;
    ArrayList<ContactModel> arrayList_model_adapter;

    // creating a constucre to get the context object because we are not in activity_class
    RecycleContactAdapter(Context context, ArrayList<ContactModel> arrayList_model_adapter) {
        this.context = context;
        this.arrayList_model_adapter = arrayList_model_adapter;
    }


    
    // creating a inner class which extends the RecyclerView.ViewHolder
    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView textname, textnumber;
        ImageView img;

        // by-default create to overrite it
        public viewHolder(View itemView) {
            super(itemView);
            // to find the id we are using the itemview.findveiwByid()
            // beacuse it is not activity class
            textname = itemView.findViewById(R.id.textview_id_contact);
            textnumber = itemView.findViewById(R.id.text_number_id);
            img = itemView.findViewById(R.id.imgContact);
        }
    }


    // overriding the methods..
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // to get a view we are creating a LayoutInflater.
        View v = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
    // create object of viewHolder to return it
        return new viewHolder(v);
        // this view will goes to viewHolder inner class.

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.img.setImageResource(arrayList_model_adapter.get(position).img);
        holder.textname.setText(arrayList_model_adapter.get(position).name);
        holder.textnumber.setText(arrayList_model_adapter.get(position).number);
    }

    @Override
    public int getItemCount() {
        return arrayList_model_adapter.size();
    }
}
