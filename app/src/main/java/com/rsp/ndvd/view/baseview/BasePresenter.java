package com.rsp.ndvd.view.baseview;

public interface BasePresenter<T extends BaseView> {

    void subscribe();
    void unsubscribe();
    void attachView(T view);
}
