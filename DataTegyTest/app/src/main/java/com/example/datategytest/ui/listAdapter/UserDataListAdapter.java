package com.example.datategytest.ui.listAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datategytest.R;
import com.example.datategytest.data.models.UserData;
import com.example.datategytest.ui.UserDetailActivity;
import com.example.datategytest.utils.Constants;

import java.util.List;

public class UserDataListAdapter extends RecyclerView.Adapter<UserDataListAdapter.Holder> {

    private List<UserData> userDataList;
    private Context context;

    public UserDataListAdapter(@NonNull Context context) {
        this.context = context;
    }

    public void updateData( List<UserData> userDataList){
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_user_list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.firstName.setText(userDataList.get(position).getFirstName());
        holder.lastName.setText(userDataList.get(position).getLastName());
        Glide.with(context)
                .load(userDataList.get(position).getPicture())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.dpImage);
        holder.container.setOnClickListener(view -> {
            Intent intent = new Intent(context, UserDetailActivity.class);
            intent.putExtra(Constants.USER_ID,userDataList.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView firstName, lastName;
        private ImageView dpImage;
        private ConstraintLayout container;

        public Holder(@NonNull View itemView) {
            super(itemView);
            dpImage = itemView.findViewById(R.id.image);
            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
            container=itemView.findViewById(R.id.container);
        }
    }
}
