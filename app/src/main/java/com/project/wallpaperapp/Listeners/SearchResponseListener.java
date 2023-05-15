package com.project.wallpaperapp.Listeners;

import com.project.wallpaperapp.Models.SearchApiResponse;

public interface SearchResponseListener {
    void onFetch(SearchApiResponse response, String message);
    void onError(String message);
}
