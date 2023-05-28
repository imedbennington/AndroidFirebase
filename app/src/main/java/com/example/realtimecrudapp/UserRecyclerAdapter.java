package com.example.realtimecrudapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<userItems> usersItemArrayList;
    DatabaseReference databaseReference;
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
        userItems users = usersItemArrayList.get(position);
        holder.textName.setText("Name : " + users.getUserName());
        holder.textEmail.setText("Email : " + users.getUserEmail());
        holder.textCountry.setText("Country : " + users.getUserCountry());
        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context, users.getUserID(), users.getUserName(), users.getUserEmail(), users.getUserCountry());
            }
        });
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
    public class ViewDialogUpdate {
        public void showDialog(Context context, String id, String name, String email, String country) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_user);

            EditText textName = dialog.findViewById(R.id.textName);
            EditText textEmail = dialog.findViewById(R.id.textEmail);
            EditText textCountry = dialog.findViewById(R.id.textCountry);

            textName.setText(name);
            textEmail.setText(email);
            textCountry.setText(country);


            Button buttonUpdate = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonUpdate.setText("UPDATE");

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newName = textName.getText().toString();
                    String newEmail = textEmail.getText().toString();
                    String newCountry = textCountry.getText().toString();

                    if (name.isEmpty() || email.isEmpty() || country.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newName.equals(name) && newEmail.equals(email) && newCountry.equals(country)) {
                            Toast.makeText(context, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("USERS").child(id).setValue(new userItems(id, newName, newEmail, newCountry));
                            Toast.makeText(context, "User Updated successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }

}
