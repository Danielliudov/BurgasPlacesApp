package com.rachev.burgasplaces.uiutils;

import com.rachev.burgasplaces.models.Place;
import com.rachev.burgasplaces.repositories.base.Repository;

import java.util.function.Predicate;

public interface ListLoader
{
    void loadListData(Object filter, Repository<Place> repository);
    
    Predicate<Object> getPredicate(Object filter);
}
