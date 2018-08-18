package com.rachev.burgasplaces.views.barcafes;

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
public class BarCafesListFragment extends Fragment implements AdapterView.OnItemClickListener
{
    private Navigator mNavigator;
    private ListView mBarCafesListView;
    private ArrayAdapter<String> mBarCafesAdapter;
    private List<Place> mBarCafes;
    
    public BarCafesListFragment()
    {
        // Required empty public constructor
    }
    
    public static BarCafesListFragment getInstance()
    {
        return new BarCafesListFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barcafes_list, container, false);
        
        mBarCafesListView = view.findViewById(R.id.lv_barcafes);
        mBarCafesAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1);
        
        mBarCafesListView.setAdapter(mBarCafesAdapter);
        mBarCafesListView.setOnItemClickListener(this);
        
        mBarCafes = new ArrayList<>();
        AndroidApplication.getSuperheroRepository().getAll(places ->
                places.stream()
                        .filter(place -> place.type.equals(Constants.BAR_CAFE))
                        .forEach(place ->
                        {
                            mBarCafesAdapter.add(place.name);
                            mBarCafes.add(place);
                        }));
        
        mBarCafesListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            ImagePreviewer.show(getContext(), mBarCafesListView, position);
            return true;
        });
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        mNavigator.navigateWith(mBarCafes.get(position));
    }
    
    public void setNavigator(Navigator navigator)
    {
        mNavigator = navigator;
    }
}
