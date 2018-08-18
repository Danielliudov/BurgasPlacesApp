package com.rachev.burgasplaces.uiutils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.firebase.storage.StorageReference;
import com.rachev.burgasplaces.AndroidApplication;
import com.rachev.burgasplaces.R;
import com.squareup.picasso.Picasso;

public class ImagePreviewer
{
    @SuppressLint("ClickableViewAccessibility")
    public static void show(Context context, ListView source, int position)
    {
        BitmapDrawable background = ImagePreviewerUtils.getBlurredScreenDrawable(
                context, source.getRootView());
        
        @SuppressLint("InflateParams")
        View dialogView = LayoutInflater.from(context)
                .inflate(R.layout.view_image_previewer, null);
        
        ImageView imageView = dialogView.findViewById(R.id.previewer_image);
        
        String imgName = source.getItemAtPosition(position).toString().toLowerCase();
        
        StorageReference load = AndroidApplication.getStorageReference()
                .child("/places/" + imgName + ".jpg");
        
        load.getDownloadUrl().addOnSuccessListener(uri ->
                Picasso.get()
                        .load(uri.toString())
                        .into(imageView));
        
        final Dialog dialog = new Dialog(context, R.style.ImagePreviewerTheme);
        dialog.getWindow().setBackgroundDrawable(background);
        dialog.setContentView(dialogView);
        dialog.show();
        
        source.setOnTouchListener((view, event) ->
        {
            if (dialog.isShowing())
            {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                
                int action = event.getActionMasked();
                if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL)
                {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                    dialog.dismiss();
                    
                    return true;
                }
            }
            return false;
        });
    }
}
