package com.rsp.ndvd.view.importview;

import com.rsp.ndvd.view.baseview.BasePresenter;
import com.rsp.ndvd.view.baseview.BaseView;

import io.reactivex.disposables.CompositeDisposable;

public class ImportPresenter implements BasePresenter<ImportView> {

    private CompositeDisposable disposable;
    private ImportView view;

    @Override
    public void subscribe() {
        disposable = new CompositeDisposable();
    }

    @Override
    public void unsubscribe() {
        disposable.clear();
    }

    @Override
    public void attachView(ImportView view) {
        this.view = view;
    }


    public void checkScreenRotate(boolean screenRotated) {
        if(screenRotated) {
            view.reAttachFragment();
        }
    }
}
