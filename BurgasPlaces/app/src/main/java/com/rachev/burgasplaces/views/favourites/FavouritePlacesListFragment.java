package com.rachev.burgasplaces.views.favourites;

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
import com.rachev.burgasplaces.models.Place;
import com.rachev.burgasplaces.uiutils.ImagePreviewer;
import com.rachev.burgasplaces.uiutils.Navigator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritePlacesListFragment extends Fragment implements AdapterView.OnItemClickListener
{
    private Navigator mNavigator;
    private ListView mFavouritePlacesListView;
    private ArrayAdapter<String> mFavouritePlacesAdapter;
    private List<Place> mFavouritePlaces;
    
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
        mFavouritePlacesAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1);
        
        mFavouritePlacesListView.setAdapter(mFavouritePlacesAdapter);
        mFavouritePlacesListView.setOnItemClickListener(this);
        
        mFavouritePlaces = new ArrayList<>();
        AndroidApplication.getSuperheroRepository().getAll(places ->
                places.stream()
                        .filter(Place::isFavourite)
                        .forEach(place ->
                        {
                            mFavouritePlacesAdapter.add(place.getName());
                            mFavouritePlaces.add(place);
                        }));
        
        mFavouritePlacesListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            ImagePreviewer.show(getContext(), mFavouritePlacesListView, position);
            return true;
        });
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        mNavigator.navigateWith(mFavouritePlaces.get(position));
    }
    
    public void setNavigator(Navigator navigator)
    {
        mNavigator = navigator;
    }
}
