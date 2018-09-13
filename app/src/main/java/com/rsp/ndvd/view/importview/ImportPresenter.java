package com.rsp.ndvd.view.importview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rsp.ndvd.model.Member;
import com.rsp.ndvd.view.baseview.BasePresenter;
import com.rsp.ndvd.view.baseview.BaseView;
import com.rsp.ndvd.viewUtils.Constants;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import io.reactivex.disposables.CompositeDisposable;

import static android.app.Activity.RESULT_OK;
import static com.rsp.ndvd.viewUtils.Constants.REQUEST_IMAGE_CAPTURE;

public class ImportPresenter implements BasePresenter<ImportView> {

    private CompositeDisposable disposable;
    private ImportView view;
    private Bitmap imageBitmap;
    private DatabaseReference mDatabase;

    public ImportPresenter() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

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
            Member member = new Member(firstName, lastName, email, mobilePhone, homePhone, convertBitmapToString(imageBitmap));
            mDatabase.child("Member").push().setValue(member);
            view.removeFragment();
        } else {
            view.toastError("Please fill out all fields and take a picture before saving.");
        }
    }

    private String convertBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        bitmap.recycle();
        byte[] byteArray = stream.toByteArray();
        String imageString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return imageString;
    }
}
