package com.rachev.burgasplaces.views.base;

import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.models.Place;
import com.rachev.burgasplaces.repositories.base.Repository;
import com.rachev.burgasplaces.uiutils.ImagePreviewer;
import com.rachev.burgasplaces.uiutils.ListLoader;
import com.rachev.burgasplaces.uiutils.Navigator;

import java.util.List;
import java.util.function.Predicate;

public abstract class BaseListFragment extends Fragment implements ListLoader
{
    private ArrayAdapter<String> mArrayAdapter;
    private List<Place> mPlacesList;
    private Navigator mNavigator;
    
    public BaseListFragment()
    {
    }
    
    protected List<Place> getPlacesList()
    {
        return mPlacesList;
    }
    
    protected void setPlacesList(List<Place> placesList)
    {
        mPlacesList = placesList;
    }
    
    protected ArrayAdapter<String> getArrayAdapter()
    {
        return mArrayAdapter;
    }
    
    protected void setArrayAdapter(ArrayAdapter<String> arrayAdapter)
    {
        mArrayAdapter = arrayAdapter;
    }
    
    protected Navigator getNavigator()
    {
        return mNavigator;
    }
    
    public void setNavigator(Navigator navigator)
    {
        mNavigator = navigator;
    }
    
    protected void showBlurredImageDialog(ListView listView, int position)
    {
        ImagePreviewer.show(getContext(), listView, position);
    }
    
    @Override
    public Predicate<Object> getPredicate(Object filter)
    {
        if (!(filter instanceof String))
            return place -> ((Place) place).isFavourite();
        else
        {
            String filterStr = (String) filter;
            switch (filterStr)
            {
                case Constants.RESTAURANT:
                    return place -> ((Place) place).getType().equals(Constants.RESTAURANT);
                case Constants.FASTFOOD:
                    return place -> ((Place) place).getType().equals(Constants.FASTFOOD);
                case Constants.ALL_DAY:
                    return place -> ((Place) place).getOpenHours().equals(Constants.ALL_DAY);
                case Constants.BAR_CAFE:
                    return place -> ((Place) place).getType().equals(Constants.BAR_CAFE);
                case Constants.NIGHTCLUB:
                    return place -> ((Place) place).getType().equals(Constants.NIGHTCLUB);
                
            }
        }
        
        return null;
    }
    
    @Override
    public void loadListData(Object filter, Repository<Place> repository)
    {
        repository.getAll(places ->
                places.stream()
                        .filter(getPredicate(filter))
                        .forEach(place ->
                        {
                            mArrayAdapter.add(place.getName());
                            mPlacesList.add(place);
                        }));
    }
}
