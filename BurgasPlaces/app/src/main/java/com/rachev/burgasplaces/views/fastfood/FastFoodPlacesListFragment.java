package com.rachev.burgasplaces.views.fastfood;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
public class FastFoodPlacesListFragment extends Fragment implements AdapterView.OnItemClickListener
{
    private Navigator mNavigator;
    private ListView mFastFoodPlacesListView;
    private ArrayAdapter<String> mFastFoodPlacesAdapter;
    private List<Place> mFastFoodPlaces;
    
    public FastFoodPlacesListFragment()
    {
        // Required empty public constructor
    }
    
    public static FastFoodPlacesListFragment getInstance()
    {
        return new FastFoodPlacesListFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fast_food_places_list, container, false);
        
        mFastFoodPlacesListView = view.findViewById(R.id.lv_fast_food_places);
        mFastFoodPlacesAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1);
        
        mFastFoodPlacesListView.setAdapter(mFastFoodPlacesAdapter);
        mFastFoodPlacesListView.setOnItemClickListener(this);
        
        mFastFoodPlaces = new ArrayList<>();
        AndroidApplication.getSuperheroRepository().getAll(places ->
                places.stream()
                        .filter(place -> place.type.equals(Constants.FASTFOOD))
                        .forEach(place ->
                        {
                            mFastFoodPlacesAdapter.add(place.name);
                            mFastFoodPlaces.add(place);
                        }));
        
        mFastFoodPlacesListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            ImagePreviewer.show(getContext(), mFastFoodPlacesListView, position);
            return true;
        });
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        mNavigator.navigateWith(mFastFoodPlaces.get(position));
    }
    
    public void setNavigator(Navigator navigator)
    {
        mNavigator = navigator;
    }
}
