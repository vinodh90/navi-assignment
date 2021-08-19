package com.dweepdroid.github.model;

public interface IResponseListener<T> {

    void onSuccess(T value);

    void onError(ErrorResponse value);
}