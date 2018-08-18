package com.rachev.burgasplaces.views.details;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.StorageReference;
import com.rachev.burgasplaces.AndroidApplication;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.models.Place;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceDetailsFragment extends Fragment
{
    private Place mPlace;
    private TextView mPlaceNameTextView;
    private TextView mPlaceAddressTextView;
    private TextView mPlaceOpenHoursTextView;
    private TextView mPlaceShortInfoTextView;
    private ImageView mPlaceImageView;
    private FloatingActionButton mDialFloatingActionButton;
    private View mBackgroundImage;
    private MaterialFavoriteButton materialFavoriteButton;
    
    public PlaceDetailsFragment()
    {
        // Required empty public constructor
    }
    
    public static PlaceDetailsFragment getInstance()
    {
        return new PlaceDetailsFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_details, container, false);
        
        mBackgroundImage = view.findViewById(R.id.background);
        mBackgroundImage.getBackground().setAlpha(Constants.OPACITY_80);
        
        mPlaceNameTextView = view.findViewById(R.id.tv_place_name);
        mPlaceNameTextView.setText(mPlace.name);
        
        mPlaceAddressTextView = view.findViewById(R.id.tv_place_address);
        mPlaceAddressTextView.setText(String.format("Адрес: %s", mPlace.address));
        
        mPlaceOpenHoursTextView = view.findViewById(R.id.tv_place_open_hours);
        mPlaceOpenHoursTextView.setText(String.format("Работно време: %s", mPlace.openHours));
        
        mPlaceShortInfoTextView = view.findViewById(R.id.tv_place_short_info);
        mPlaceShortInfoTextView.setText(mPlace.shortInfo);
        
        materialFavoriteButton = view.findViewById(R.id.fav_button);
        materialFavoriteButton.setOnFavoriteChangeListener((buttonView, favorite) ->
        {
            if (favorite)
            {
                mPlace.isFavourite = true;
                
                FirebaseFirestore.getInstance()
                        .collection("places")
                        .document(mPlace.documentId)
                        .set(mPlace, SetOptions.merge())
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(getContext(), "Заведението добавено към любими", Toast.LENGTH_SHORT)
                                        .show())
                        .addOnFailureListener(e ->
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT)
                                        .show());
            } else
            {
                mPlace.isFavourite = false;
                
                FirebaseFirestore.getInstance()
                        .collection("places")
                        .document(mPlace.documentId)
                        .set(mPlace, SetOptions.merge())
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(getContext(), "Заведението премахнато от любими", Toast.LENGTH_SHORT)
                                        .show())
                        .addOnFailureListener(e ->
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT)
                                        .show());
            }
        });
        
        mDialFloatingActionButton = view.findViewById(R.id.fab);
        mDialFloatingActionButton.setOnClickListener(v ->
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mPlace.contactPhone));
            
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, Constants.DIALER_REQUEST_CODE);
            else
            {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        
        mPlaceImageView = view.findViewById(R.id.iv_place);
        loadPlaceImage();
        
        return view;
    }
    
    private void loadPlaceImage()
    {
        StorageReference load = AndroidApplication.getStorageReference()
                .child("/places/" + mPlace.name.toLowerCase() + ".jpg");
        
        load.getDownloadUrl().addOnSuccessListener(uri ->
                Picasso.get()
                        .load(uri.toString())
                        .into(mPlaceImageView));
    }
    
    public void setPlace(Place place)
    {
        if (place == null)
            return;
        
        mPlace = place;
    }
}
