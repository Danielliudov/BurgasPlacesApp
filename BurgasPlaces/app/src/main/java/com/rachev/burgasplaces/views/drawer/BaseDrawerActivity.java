package com.rachev.burgasplaces.views.drawer;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.views.barcafes.BarCafesListActivity;
import com.rachev.burgasplaces.views.dayandnight.DayAndNightPlacesListActivity;
import com.rachev.burgasplaces.views.fastfood.FastFoodPlacesListActivity;
import com.rachev.burgasplaces.views.favourites.FavouritePlacesListActivity;
import com.rachev.burgasplaces.views.nightclubs.NightclubsListActivity;
import com.rachev.burgasplaces.views.restaurants.RestaurantsListActivity;

public abstract class BaseDrawerActivity extends AppCompatActivity
{
    public BaseDrawerActivity()
    {
    }
    
    public void setupDrawer()
    {
        PrimaryDrawerItem listFavouritesItem = new PrimaryDrawerItem()
                .withIdentifier(FavouritePlacesListActivity.IDENTIFIER)
                .withIcon(R.drawable.ic_star)
                .withTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withSelectedTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withName(Constants.FAVOURITES_TITLE);
        
        PrimaryDrawerItem listRestaurantsItem = new PrimaryDrawerItem()
                .withIdentifier(RestaurantsListActivity.IDENTIFIER)
                .withIcon(R.drawable.ic_local_dining)
                .withTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withSelectedTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withName(Constants.RESTAURANTS_TITLE);
        
        PrimaryDrawerItem listFastFoodItem = new PrimaryDrawerItem()
                .withIdentifier(FastFoodPlacesListActivity.IDENTIFIER)
                .withIcon(R.drawable.ic_fastfood)
                .withTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withSelectedTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withName(Constants.FAST_FOOD_TITLE);
        
        PrimaryDrawerItem listCafesItem = new PrimaryDrawerItem()
                .withIdentifier(BarCafesListActivity.IDENTIFIER)
                .withIcon(R.drawable.ic_local_drink)
                .withTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withSelectedTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withName(Constants.BAR_CAFES_TITLE);
        
        PrimaryDrawerItem listNightclubsItem = new PrimaryDrawerItem()
                .withIdentifier(NightclubsListActivity.IDENTIFIER)
                .withIcon(R.drawable.ic_accessibility_new)
                .withTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withSelectedTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withName(Constants.NIGHTCLUBS_TITLE);
        
        PrimaryDrawerItem listDayAndNightItem = new PrimaryDrawerItem()
                .withIdentifier(DayAndNightPlacesListActivity.IDENTIFIER)
                .withIcon(R.drawable.ic_timer_off)
                .withTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withSelectedTextColor(ContextCompat.getColor(this, R.color.materialize_primary_light))
                .withName(Constants.DAY_AND_NIGHT_PLACES_TITLE);
        
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getDrawerToolbar())
                .addDrawerItems(
                        listFavouritesItem,
                        new DividerDrawerItem(),
                        listRestaurantsItem,
                        new DividerDrawerItem(),
                        listFastFoodItem,
                        new DividerDrawerItem(),
                        listCafesItem,
                        new DividerDrawerItem(),
                        listNightclubsItem,
                        new DividerDrawerItem(),
                        listDayAndNightItem)
                .withOnDrawerItemClickListener((view, position, drawerItem) ->
                {
                    int identifier = (int) drawerItem.getIdentifier();
                    
                    if (getIdentifier() == identifier)
                        return false;
                    
                    Intent intent = getNextIntent(identifier);
                    
                    if (intent == null)
                        return false;
                    
                    startActivity(intent);
                    return true;
                })
                .build();
    }
    
    private Intent getNextIntent(int identifier)
    {
        Intent intent = null;
        switch (identifier)
        {
            case FavouritePlacesListActivity.IDENTIFIER:
                intent = new Intent(this, FavouritePlacesListActivity.class);
                break;
            case RestaurantsListActivity.IDENTIFIER:
                intent = new Intent(this, RestaurantsListActivity.class);
                break;
            case FastFoodPlacesListActivity.IDENTIFIER:
                intent = new Intent(this, FastFoodPlacesListActivity.class);
                break;
            case BarCafesListActivity.IDENTIFIER:
                intent = new Intent(this, BarCafesListActivity.class);
                break;
            case NightclubsListActivity.IDENTIFIER:
                intent = new Intent(this, NightclubsListActivity.class);
                break;
            case DayAndNightPlacesListActivity.IDENTIFIER:
                intent = new Intent(this, DayAndNightPlacesListActivity.class);
                break;
            default:
                break;
        }
        
        return intent;
    }
    
    protected abstract int getIdentifier();
    
    protected abstract Toolbar getDrawerToolbar();
    
    @Override
    protected void onStart()
    {
        super.onStart();
        setupDrawer();
    }
}
