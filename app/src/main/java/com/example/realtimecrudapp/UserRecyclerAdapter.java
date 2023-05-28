package com.example.realtimecrudapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<userItems> usersItemArrayList;

    public UserRecyclerAdapter(Context context, ArrayList<userItems> usersItemArrayList) {
        this.context = context;
        this.usersItemArrayList = usersItemArrayList;
    }

    @NonNull
    @Override
    public UserRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.uszer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return usersItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textEmail;
        TextView textCountry;

        Button buttonDelete;
        Button buttonUpdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textEmail = itemView.findViewById(R.id.textEmail);
            textCountry = itemView.findViewById(R.id.textCountry);

            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
        }
    }
}
