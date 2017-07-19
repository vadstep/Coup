package com.example.vadym.coupbatterystatusapp.NetworkController;



public interface ResultListener<T> {
    void onSuccess(T result);
    void onError(Throwable throwable);
}
