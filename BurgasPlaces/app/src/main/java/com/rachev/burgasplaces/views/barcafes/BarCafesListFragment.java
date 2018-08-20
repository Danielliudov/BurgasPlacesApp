package com.rachev.burgasplaces.views.barcafes;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rachev.burgasplaces.BurgasPlacesApp;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.views.base.BaseListFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarCafesListFragment extends BaseListFragment implements AdapterView.OnItemClickListener
{
    private ListView mBarCafesListView;
    
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
        setArrayAdapter(new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1));
        
        mBarCafesListView.setAdapter(getArrayAdapter());
        mBarCafesListView.setOnItemClickListener(this);
        mBarCafesListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            showBlurredImageDialog(mBarCafesListView, position);
            return true;
        });
    
        setPlacesList(new ArrayList<>());
        loadListData(Constants.BAR_CAFE, BurgasPlacesApp.getSuperheroRepository());
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        getNavigator().navigateWith(getPlacesList().get(position));
    }
}
