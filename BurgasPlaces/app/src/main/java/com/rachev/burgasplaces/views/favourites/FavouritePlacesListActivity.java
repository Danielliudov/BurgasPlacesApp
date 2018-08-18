package com.rachev.burgasplaces.views.favourites;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.*;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.models.Place;
import com.rachev.burgasplaces.uiutils.Navigator;
import com.rachev.burgasplaces.views.drawer.BaseDrawerActivity;
import com.rachev.burgasplaces.views.details.PlaceDetailsActivity;
import com.rachev.burgasplaces.views.details.PlaceDetailsFragment;

public class FavouritePlacesListActivity extends BaseDrawerActivity implements Navigator
{
    public static final int IDENTIFIER = 346;
    
    private FavouritePlacesListFragment mFavouritePlacesListFragment;
    private PlaceDetailsFragment mPlaceDetailsFragment;
    private boolean mDoubleBackToExitPressedOnce = false;
    private boolean mIsPhone;
    private Toolbar mToolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_places_list);
        setTitle(Constants.FAVOURITES_TITLE);
        
        mToolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mToolbar);
        
        mFavouritePlacesListFragment = FavouritePlacesListFragment.getInstance();
        mFavouritePlacesListFragment.setNavigator(this);
        
        mIsPhone = findViewById(R.id.content_favourite_details) == null;
        
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_favourite, mFavouritePlacesListFragment);
        
        if (!mIsPhone)
        {
            mPlaceDetailsFragment = PlaceDetailsFragment.getInstance();
            transaction.replace(R.id.content_favourite_details, mPlaceDetailsFragment);
        }
        
        transaction.commit();
    }
    
    @Override
    protected void onResume()
    {
        super.onResume();
        
        this.mDoubleBackToExitPressedOnce = false;
    }
    
    @Override
    public void onBackPressed()
    {
        if (mDoubleBackToExitPressedOnce)
        {
            super.onBackPressed();
            return;
        }
        
        mDoubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT)
                .show();
    }
    
    @Override
    public void navigateWith(Place place)
    {
        if (mIsPhone)
        {
            Intent intent = new Intent(this, PlaceDetailsActivity.class);
            
            intent.putExtra("obj", place);
            
            startActivity(intent);
        } else
            mPlaceDetailsFragment.setPlace(place);
    }
    
    @Override
    protected int getIdentifier()
    {
        return IDENTIFIER;
    }
    
    @Override
    protected Toolbar getDrawerToolbar()
    {
        return mToolbar;
    }
}
