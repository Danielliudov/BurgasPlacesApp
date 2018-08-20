package com.rachev.burgasplaces.views.favourites;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.views.base.BaseListFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritePlacesListFragment extends BaseListFragment implements AdapterView.OnItemClickListener
{
    private ListView mFavouritePlacesListView;
    
    public FavouritePlacesListFragment()
    {
        // Required empty public constructor
    }
    
    public static FavouritePlacesListFragment getInstance()
    {
        return new FavouritePlacesListFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite_places_list, container, false);
        
        mFavouritePlacesListView = view.findViewById(R.id.lv_favourite_places);
        setArrayAdapter(new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1));
        
        mFavouritePlacesListView.setAdapter(getArrayAdapter());
        mFavouritePlacesListView.setOnItemClickListener(this);
        mFavouritePlacesListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            showBlurredImageDialog(mFavouritePlacesListView, position);
            return true;
        });
        
        setPlacesList(new ArrayList<>());
        loadListData(true);
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        getNavigator().navigateWith(getPlacesList().get(position));
    }
}
