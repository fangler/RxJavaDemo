package com.fangler.rxdemo.service;

import com.fangler.rxdemo.model.ByImage;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by fangler on 2015/10/22.
 */
public interface ByApi {

  @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
  Observable<ByImage> getImage();
}
