package com.rsp.ndvd.view.importview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.rsp.ndvd.view.baseview.BasePresenter;
import com.rsp.ndvd.view.baseview.BaseView;
import com.rsp.ndvd.viewUtils.Constants;

import io.reactivex.disposables.CompositeDisposable;

import static android.app.Activity.RESULT_OK;
import static com.rsp.ndvd.viewUtils.Constants.REQUEST_IMAGE_CAPTURE;

public class ImportPresenter implements BasePresenter<ImportView> {

    private CompositeDisposable disposable;
    private ImportView view;
    private Bitmap imageBitmap;

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

    public void takePictureClicked() {
        view.launchCamera(REQUEST_IMAGE_CAPTURE);
    }

    public void retrieveImage(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            this.imageBitmap = imageBitmap;
            view.displayImagePreview(imageBitmap);
        }
    }

    public void saveClicked(String firstName, String lastName, String email, String mobilePhone, String homePhone) {
        if(!(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || mobilePhone.isEmpty() || homePhone.isEmpty() || imageBitmap == null)) {
            //Save object to Firebase - Model will need to be Base64 String or Byte[] so we can decode into a bitmap image and display.
        } else {
            view.toastError("Please fill out all fields and take a picture before saving.");
        }
    }
}
