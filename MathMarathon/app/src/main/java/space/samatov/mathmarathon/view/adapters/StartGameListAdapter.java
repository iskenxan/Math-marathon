package space.samatov.mathmarathon.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import space.samatov.mathmarathon.R;
import space.samatov.mathmarathon.model.FirebaseManager;
import space.samatov.mathmarathon.model.User;
import space.samatov.mathmarathon.model.interfaces.OnExtracUserListener;
import space.samatov.mathmarathon.model.interfaces.OnStartGameItemClickedListener;

/**
 * Created by iskenxan on 11/6/17.
 */

public class StartGameListAdapter extends RecyclerView.Adapter {

    OnStartGameItemClickedListener mListener;
    ArrayList<String> mFriendList;

    public StartGameListAdapter(ArrayList<String> friendList, OnStartGameItemClickedListener listener){
        mListener=listener;
        mFriendList=friendList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.start_game_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }



    private class ViewHolder extends RecyclerView.ViewHolder implements OnExtracUserListener, View.OnClickListener {
        ImageView mProfileImageView;
        TextView mUsernameTextView;
        TextView mPlayTextView;
        int mPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            mProfileImageView=itemView.findViewById(R.id.StartGameItemProfileImageView);
            mUsernameTextView=itemView.findViewById(R.id.StartGameItemUsername);
            mPlayTextView=itemView.findViewById(R.id.StartGamePlayTextView);
        }

        private void bind(int position){
            mPosition=position;
            String username=mFriendList.get(position);
            FirebaseManager.extractUserData(this,username);
            mUsernameTextView.setText(username);
            mPlayTextView.setOnClickListener(this);
        }

        @Override
        public void onUserDataExtracted(User user) {
            Picasso.with(mProfileImageView.getContext()).load(user.getPhotoUrl()).placeholder(R.drawable.profile_placeholder).into(mProfileImageView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
