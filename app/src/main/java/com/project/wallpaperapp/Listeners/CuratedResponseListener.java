package com.project.wallpaperapp.Listeners;

import com.project.wallpaperapp.Models.CuratedApiResponse;

public interface CuratedResponseListener {
     public void onFetch(CuratedApiResponse response, String message);
     public void onError(String message);
}
