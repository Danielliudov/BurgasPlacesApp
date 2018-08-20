package com.rachev.burgasplaces.views.dayandnight;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.views.base.BaseListFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayAndNightPlacesListFragment extends BaseListFragment implements AdapterView.OnItemClickListener
{
    private ListView mDayAndNightPlacesListView;
    
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
        setArrayAdapter(new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1));
        
        mDayAndNightPlacesListView.setAdapter(getArrayAdapter());
        mDayAndNightPlacesListView.setOnItemClickListener(this);
        mDayAndNightPlacesListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            showBlurredImageDialog(mDayAndNightPlacesListView, position);
            return true;
        });
        
        setPlacesList(new ArrayList<>());
        loadListData(Constants.ALL_DAY);
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        getNavigator().navigateWith(getPlacesList().get(position));
    }
}
