package com.rachev.burgasplaces.views.dayandnight;


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
public class DayAndNightPlacesListFragment extends Fragment implements AdapterView.OnItemClickListener
{
    private Navigator mNavigator;
    private ListView mDayAndNightPlacesListView;
    private ArrayAdapter<String> mDayAndNightPlacesAdapter;
    private List<Place> mDayAndNightPlaces;
    
    public DayAndNightPlacesListFragment()
    {
        // Required empty public constructor
    }
    
    public static DayAndNightPlacesListFragment getInstance()
    {
        return new DayAndNightPlacesListFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day_and_night_places_list, container, false);
        
        mDayAndNightPlacesListView = view.findViewById(R.id.lv_day_and_night_places);
        mDayAndNightPlacesAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1);
        
        mDayAndNightPlacesListView.setAdapter(mDayAndNightPlacesAdapter);
        mDayAndNightPlacesListView.setOnItemClickListener(this);
        
        mDayAndNightPlaces = new ArrayList<>();
        AndroidApplication.getSuperheroRepository().getAll(places ->
                places.stream()
                        .filter(place -> place.openHours.equals(Constants.ALL_DAY))
                        .forEach(place ->
                        {
                            mDayAndNightPlacesAdapter.add(place.name);
                            mDayAndNightPlaces.add(place);
                        }));
        
        mDayAndNightPlacesListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            ImagePreviewer.show(getContext(), mDayAndNightPlacesListView, position);
            return true;
        });
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        mNavigator.navigateWith(mDayAndNightPlaces.get(position));
    }
    
    public void setNavigator(Navigator navigator)
    {
        mNavigator = navigator;
    }
}
