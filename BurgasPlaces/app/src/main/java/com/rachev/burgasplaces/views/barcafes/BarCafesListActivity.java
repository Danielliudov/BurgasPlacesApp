package com.rachev.burgasplaces.views.barcafes;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.models.Place;
import com.rachev.burgasplaces.uiutils.Navigator;
import com.rachev.burgasplaces.views.base.BaseDrawerActivity;
import com.rachev.burgasplaces.views.details.PlaceDetailsActivity;
import com.rachev.burgasplaces.views.details.PlaceDetailsFragment;

public class BarCafesListActivity extends BaseDrawerActivity implements Navigator
{
    public static final int IDENTIFIER = 657;
    
    private BarCafesListFragment mBarCafesListFragment;
    private PlaceDetailsFragment mPlaceDetailsFragment;
    private boolean mIsPhone;
    private Toolbar mToolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcafes_list);
        setTitle(Constants.BAR_CAFES_TITLE);
        
        mToolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mToolbar);
        
        mBarCafesListFragment = BarCafesListFragment.getInstance();
        mBarCafesListFragment.setNavigator(this);
        
        mIsPhone = findViewById(R.id.content_barcafes_details) == null;
        
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_barcafes, mBarCafesListFragment);
        
        if (!mIsPhone)
        {
            mPlaceDetailsFragment = PlaceDetailsFragment.getInstance();
            transaction.replace(R.id.content_barcafes_details, mPlaceDetailsFragment);
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
