package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    @BindView(R.id.imageButtonBack)
    ImageButton mButtonBack;

    @BindView(R.id.floatingActionButtonFav)
    FloatingActionButton mImageButtonFav;

    @BindView(R.id.image_avatar)
    ImageView mImageViewAvatar;

    @BindView(R.id.nameProfil)
    TextView mTextViewNameProfil;

    @BindView(R.id.activity_name_text)
    TextView mTextViewNameText;

    @BindView(R.id.activity_phone_text)
    TextView mTextViewPhoneText;

    @BindView(R.id.activity_mail_text)
    TextView mTextViewMailText;

    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind( this);
        mApiService = DI.getNeighbourApiService();

        displayDetail();
        actionOnFavoriteButton();
        actionOnButtonback();

    }

    /**
     * get extra and show detail of neighbour
     */
    private void displayDetail() {
        if (getIntent().hasExtra("Neighbour")) {
            Neighbour neighbour = getIntent().getParcelableExtra("Neighbour");

            Glide.with(this)
                    .load(neighbour.getAvatarUrl())
                    .into(mImageViewAvatar);
            mTextViewNameText.setText(neighbour.getName());
            mTextViewNameProfil.setText(neighbour.getName());
            mTextViewMailText.setText("www.facebook.fr/" + neighbour.getName());
        }
    }

    /**
     * display favorite  image button full when neighbour is already favorite.
     * get favorite when favorite button is clicked
     * if neighbour is not favorite image button is empty
     */

    private void actionOnFavoriteButton() {
        if (getIntent().hasExtra("Neighbour")) {
            Neighbour neighbour = getIntent().getParcelableExtra("Neighbour");

            if (mApiService.getFavorite().contains(neighbour)) {
                mImageButtonFav.setImageResource(R.drawable.ic_star_white_24dp);
            }

            mImageButtonFav.setOnClickListener(v -> {
                if (!mApiService.getFavorite().contains(neighbour)) {
                    mApiService.addFavorite(neighbour);

                    mImageButtonFav.setImageResource(R.drawable.ic_star_white_24dp);
                    Toast.makeText(this, "add to favorites", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, " already favorite ", Toast.LENGTH_SHORT).show();
                }

            });

        }
    }

    /**
     * back to previously page
     */
    private void actionOnButtonback() {

        mButtonBack.setOnClickListener(v -> {
            finish();
        });
    }

}