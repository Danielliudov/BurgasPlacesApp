package com.rachev.burgasplaces.uiutils;

import java.util.function.Predicate;

public interface ListLoader
{
    void loadListData(Object filter);
    
    Predicate<Object> getPredicate(Object filter);
}
