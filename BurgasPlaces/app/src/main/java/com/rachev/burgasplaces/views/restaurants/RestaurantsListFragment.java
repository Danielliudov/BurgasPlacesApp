package com.rachev.burgasplaces.views.restaurants;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.rachev.burgasplaces.BurgasPlacesApp;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.views.base.BaseListFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsListFragment extends BaseListFragment implements AdapterView.OnItemClickListener
{
    private ListView mRestaurantsListView;
    
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
        setArrayAdapter(new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1));
        
        mRestaurantsListView.setAdapter(getArrayAdapter());
        mRestaurantsListView.setOnItemClickListener(this);
        mRestaurantsListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            showBlurredImageDialog(mRestaurantsListView, position);
            return true;
        });
        
        setPlacesList(new ArrayList<>());
        loadListData(Constants.RESTAURANT, BurgasPlacesApp.getSuperheroRepository());
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        getNavigator().navigateWith(getPlacesList().get(position));
    }
}