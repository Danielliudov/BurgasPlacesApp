package com.rachev.burgasplaces.views.restaurants;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.rachev.burgasplaces.AndroidApplication;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.models.Place;
import com.rachev.burgasplaces.uiutils.ImagePreviewer;
import com.rachev.burgasplaces.uiutils.Navigator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsListFragment extends Fragment implements AdapterView.OnItemClickListener
{
    private Navigator mNavigator;
    private ListView mRestaurantsListView;
    private ArrayAdapter<String> mRestaurantsAdapter;
    private List<Place> mRestaurants;
    
    public RestaurantsListFragment()
    {
        // Required empty public constructor
    }
    
    public static RestaurantsListFragment getInstance()
    {
        return new RestaurantsListFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurants_list, container, false);
        
        mRestaurantsListView = view.findViewById(R.id.lv_restaurants);
        mRestaurantsAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1);
        
        mRestaurantsListView.setAdapter(mRestaurantsAdapter);
        mRestaurantsListView.setOnItemClickListener(this);
        
        mRestaurants = new ArrayList<>();
        AndroidApplication.getSuperheroRepository().getAll(places ->
                places.stream()
                        .filter(place -> place.getType().equals(Constants.RESTAURANT))
                        .forEach(place ->
                        {
                            mRestaurantsAdapter.add(place.getName());
                            mRestaurants.add(place);
                        }));
        
        mRestaurantsListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            ImagePreviewer.show(getContext(), mRestaurantsListView, position);
            return true;
        });
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        mNavigator.navigateWith(mRestaurants.get(position));
    }
    
    public void setNavigator(Navigator navigator)
    {
        mNavigator = navigator;
    }
}