package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavoriteNeighbourFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.onItemListener {

    private NeighbourApiService mApiService;
    private List<Neighbour> mFavoriteNeighbour;
    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter mAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FavoriteNeighbourFragment.
     */
    public static FavoriteNeighbourFragment newInstance() {

        FavoriteNeighbourFragment fragment = new FavoriteNeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_favorite_neighbour, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;
    }

    /**
     * Init the List of FavoriteNeighbour
     */

    private void initList() {
        mFavoriteNeighbour = mApiService.getFavorite();
        mAdapter = new MyNeighbourRecyclerViewAdapter(this.mFavoriteNeighbour,this,MyNeighbourRecyclerViewAdapter.ListType.FAVORITE);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register( this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister( this);

    }

    @Override
    public  void onResume() {
        super.onResume();
        initList();
    }

    @Subscribe
    public  void onDeleteFavoriteNeighbour(DeleteFavoriteNeighbourEvent event) {
        mApiService.deleteFavorite(event.neighbour);
        initList();
    }

    /**
     * get position when the item is clicked
     * start activity NeighbourDetailActivity
     * @param position
     */

    @Override
    public void onItemClick(int position) {
        Context context = getActivity();
        Intent intent = new Intent(context, NeighbourDetailActivity.class);
        intent.putParcelableArrayListExtra("Neighbour", mFavoriteNeighbour.get(position));
        startActivity(intent);
    }

}