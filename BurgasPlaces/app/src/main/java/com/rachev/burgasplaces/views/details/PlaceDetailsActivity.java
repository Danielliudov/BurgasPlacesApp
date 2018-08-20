package com.rachev.burgasplaces.views.details;

import android.app.Activity;
import android.os.Bundle;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.models.Place;

public class PlaceDetailsActivity extends Activity
{
    private PlaceDetailsFragment mPlaceDetailsFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
    
        Place place = (Place) getIntent().getSerializableExtra("obj");
        
        mPlaceDetailsFragment = PlaceDetailsFragment.getInstance();
        mPlaceDetailsFragment.setPlace(place);
        
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_details, mPlaceDetailsFragment)
                .commit();
    }
}
