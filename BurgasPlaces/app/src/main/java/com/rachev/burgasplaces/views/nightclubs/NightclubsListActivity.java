package com.rachev.burgasplaces.views.nightclubs;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.models.Place;
import com.rachev.burgasplaces.uiutils.Navigator;
import com.rachev.burgasplaces.views.drawer.BaseDrawerActivity;
import com.rachev.burgasplaces.views.details.PlaceDetailsActivity;
import com.rachev.burgasplaces.views.details.PlaceDetailsFragment;

public class NightclubsListActivity extends BaseDrawerActivity implements Navigator
{
    public static final int IDENTIFIER = 409;
    
    private NightclubsListFragment mNightclubsListFragment;
    private PlaceDetailsFragment mPlaceDetailsFragment;
    private boolean mIsPhone;
    private Toolbar mToolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nightclubs_list);
        setTitle(Constants.NIGHTCLUBS_TITLE);
        
        mToolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mToolbar);
        
        mNightclubsListFragment = NightclubsListFragment.getInstance();
        mNightclubsListFragment.setNavigator(this);
        
        mIsPhone = findViewById(R.id.content_nightclubs_details) == null;
        
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_nightclubs, mNightclubsListFragment);
        
        if (!mIsPhone)
        {
            mPlaceDetailsFragment = PlaceDetailsFragment.getInstance();
            transaction.replace(R.id.content_nightclubs_details, mPlaceDetailsFragment);
        }
        
        transaction.commit();
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
