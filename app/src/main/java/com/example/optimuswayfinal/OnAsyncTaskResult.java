package com.example.optimuswayfinal;

public interface OnAsyncTaskResult<T> {
    public void onSuccess(T object);
    public void onFailure(Exception e);
}
