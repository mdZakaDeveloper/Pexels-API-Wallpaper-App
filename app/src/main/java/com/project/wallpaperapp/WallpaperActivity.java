package com.project.wallpaperapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.wallpaperapp.Models.Photo;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class WallpaperActivity extends AppCompatActivity {

    Photo photo;
    ImageView wallpaper_image;
    FloatingActionButton download, set_wallpaper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        wallpaper_image = findViewById(R.id.wallpaper_image);
        download = findViewById(R.id.download);
        set_wallpaper = findViewById(R.id.set_wallpaper);

        photo = (Photo) getIntent().getSerializableExtra("photo");
        Picasso.get().load(photo.getSrc().getOriginal()).placeholder(R.drawable.placeholder_image).into(wallpaper_image);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager downloadManager = null;
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(photo.getSrc().getLarge());

                DownloadManager.Request request = new DownloadManager.Request(uri);


                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle("Wallpaper_" + photo.getPhotographer())
                        .setMimeType("image/jpeg")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "Wallpaper_" + photo.getPhotographer() + ".jpg");

                downloadManager.enqueue(request);
                Toast.makeText(WallpaperActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();

            }
        });

        set_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(WallpaperActivity.this);
                Bitmap bitmap = ((BitmapDrawable) wallpaper_image.getDrawable()).getBitmap();
                try{
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(WallpaperActivity.this, "Wallpaper Applied", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(WallpaperActivity.this, "Couldn't Add Wallpaper:( !", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




    }