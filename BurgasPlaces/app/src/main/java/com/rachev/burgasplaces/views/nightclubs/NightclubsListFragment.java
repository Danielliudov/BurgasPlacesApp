package com.rachev.burgasplaces.views.nightclubs;

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
public class NightclubsListFragment extends Fragment implements AdapterView.OnItemClickListener
{
    private Navigator mNavigator;
    private ListView mNightclubsListView;
    private ArrayAdapter<String> mNightclubsAdapter;
    private List<Place> mNightclubs;
    
    public NightclubsListFragment()
    {
        // Required empty public constructor
    }
    
    public static NightclubsListFragment getInstance()
    {
        return new NightclubsListFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nightclubs_list, container, false);
        
        mNightclubsListView = view.findViewById(R.id.lv_nightclubs);
        mNightclubsAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1);
        
        mNightclubsListView.setAdapter(mNightclubsAdapter);
        mNightclubsListView.setOnItemClickListener(this);
        
        mNightclubs = new ArrayList<>();
        AndroidApplication.getSuperheroRepository().getAll(places ->
                places.stream()
                        .filter(place -> place.getType().equals(Constants.NIGHTCLUB))
                        .forEach(place ->
                        {
                            mNightclubsAdapter.add(place.getName());
                            mNightclubs.add(place);
                        }));
        
        mNightclubsListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            ImagePreviewer.show(getContext(), mNightclubsListView, position);
            return true;
        });
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        mNavigator.navigateWith(mNightclubs.get(position));
    }
    
    public void setNavigator(Navigator navigator)
    {
        mNavigator = navigator;
    }
}
