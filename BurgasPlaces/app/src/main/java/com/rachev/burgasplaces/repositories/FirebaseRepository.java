package com.rachev.burgasplaces.repositories;

import com.google.firebase.firestore.FirebaseFirestore;
import com.rachev.burgasplaces.repositories.base.Repository;

import java.util.List;
import java.util.function.Consumer;

public class FirebaseRepository<T> implements Repository<T>
{
    private final FirebaseFirestore mDb;
    private final Class<T> mClazz;
    private final String mCollectionName;
    
    public FirebaseRepository(Class<T> clazz)
    {
        mDb = FirebaseFirestore.getInstance();
        mClazz = clazz;
        mCollectionName = clazz.getSimpleName().toLowerCase() + 's';
    }
    
    @Override
    public void getAll(Consumer<List<T>> action)
    {
        mDb.collection(mCollectionName)
                .get()
                .addOnCompleteListener(task ->
                {
                    List<T> items = task.getResult()
                            .toObjects(mClazz);
                    
                    action.accept(items);
                });
    }
    
    @Override
    public void add(T item, Consumer<T> action)
    {
        mDb.collection(mCollectionName)
                .add(item)
                .addOnCompleteListener(task -> action.accept(item));
    }
}

