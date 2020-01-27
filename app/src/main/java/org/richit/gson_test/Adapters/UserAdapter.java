package org.richit.gson_test.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.richit.gson_test.Models.User;
import org.richit.gson_test.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    ArrayList<User> users = new ArrayList<>();

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( context ).inflate( R.layout.user_item, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get( position);

        holder.textViewName.setText( user.getName() );
        holder.textViewEmail.setText( user.getEmail() );
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewEmail;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            textViewName = itemView.findViewById( R.id.nameTv );
            textViewEmail = itemView.findViewById( R.id.emailTv );
        }
    }
}
