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
import space.samatov.mathmarathon.model.UserReference;
import space.samatov.mathmarathon.model.interfaces.OnUserRequestItemClickedListener;

/**
 * Created by iskenxan on 10/30/17.
 */

public class FriendRequestListAdapter extends RecyclerView.Adapter {
    private ArrayList<UserReference> mFriendRequests;
    private OnUserRequestItemClickedListener mItemClickedListener;


    public FriendRequestListAdapter (ArrayList<UserReference> friendRequests, OnUserRequestItemClickedListener listener){
        mFriendRequests = friendRequests;
        mItemClickedListener=listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_request_recycler_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return mFriendRequests.size();
    }




    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mProfileImageView;
        TextView mUsernameTextView;
        TextView mAcceptTextView;
        TextView mDeclineTextView;
        int mPosition;


        public ViewHolder(View itemView) {
            super(itemView);
            mProfileImageView=itemView.findViewById(R.id.FriendRequestRecyclerItemImageView);
            mUsernameTextView=itemView.findViewById(R.id.FriendRequestRecyclerItemUsernameTextView);
            mAcceptTextView=itemView.findViewById(R.id.FriendRequestRecyclerItemAcceptTextView);
            mDeclineTextView=itemView.findViewById(R.id.FriendRequestRecyclerItemDeclineTextView);
        }


        private void bind(int position){
            mPosition=position;
            UserReference request = mFriendRequests.get(position);
            Picasso.with(mProfileImageView.getContext()).load(request.getPhotoUrl()).placeholder(R.drawable.profile_placeholder).into(mProfileImageView);
            mUsernameTextView.setText(request.getUsername());
            mAcceptTextView.setOnClickListener(this);
            mDeclineTextView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(view.getId()==mAcceptTextView.getId())
                mItemClickedListener.onRequestItemClicked(true,mPosition);
            else
                mItemClickedListener.onRequestItemClicked(false,mPosition);
        }


    }

}
