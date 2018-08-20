package com.rachev.burgasplaces.views.nightclubs;

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
public class NightclubsListFragment extends BaseListFragment implements AdapterView.OnItemClickListener
{
    private ListView mNightclubsListView;
    
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
        setArrayAdapter(new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1));
        
        mNightclubsListView.setAdapter(getArrayAdapter());
        mNightclubsListView.setOnItemClickListener(this);
        
        mNightclubsListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            showBlurredImageDialog(mNightclubsListView, position);
            return true;
        });
        
        setPlacesList(new ArrayList<>());
        loadListData(Constants.NIGHTCLUB);
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        getNavigator().navigateWith(getPlacesList().get(position));
    }
}
