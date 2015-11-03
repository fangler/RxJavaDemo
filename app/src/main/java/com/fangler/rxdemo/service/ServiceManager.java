package com.fangler.rxdemo.service;

import retrofit.RestAdapter;

/**
 * Created by fangler on 2015/10/22.
 */
public class ServiceManager {

  public static RestAdapter getRestAdapter(String endpoint) {
    return new RestAdapter.Builder().setEndpoint(endpoint).build();
  }
}
