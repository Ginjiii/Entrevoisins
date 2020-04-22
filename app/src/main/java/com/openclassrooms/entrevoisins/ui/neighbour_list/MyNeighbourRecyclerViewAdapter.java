package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {
    public enum ListType {
        NEIGHBOURS,
        FAVORITE

    }
    private final List<Neighbour> mNeighbours;
    private onItemListener mOnItemListener;
    private ListType mType;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, onItemListener onItemListener,ListType type) {
        mNeighbours = items;
        this.mOnItemListener = onItemListener;
        this.mType = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view, mOnItemListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
/**
 * delete neighbour from the list
 */
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mType){
                    case NEIGHBOURS:
                        EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                        EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(neighbour));
                        break;
                    case FAVORITE:
                        EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(neighbour));
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        onItemListener mOnItemListener;

        public ViewHolder(View view,onItemListener mOnItemListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.mOnItemListener = mOnItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onItemClick(getAdapterPosition());

        }
    }
    /**
     * create interface for detect the click
     */
    public interface onItemListener{
        void onItemClick (int position);

    }
}
