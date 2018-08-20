package com.rachev.burgasplaces;

import android.app.Application;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rachev.burgasplaces.models.Place;
import com.rachev.burgasplaces.repositories.FirebaseRepository;
import com.rachev.burgasplaces.repositories.base.Repository;

public class BurgasPlacesApp extends Application
{
    private static Repository<Place> superheroRepository;
    private static StorageReference storageReference;
    
    public static Repository<Place> getSuperheroRepository()
    {
        if (superheroRepository == null)
            superheroRepository = new FirebaseRepository<>(Place.class);
        
        return superheroRepository;
    }
    
    public static StorageReference getStorageReference()
    {
        if (storageReference == null)
            storageReference = FirebaseStorage.getInstance().getReference();
        
        return storageReference;
    }
}
