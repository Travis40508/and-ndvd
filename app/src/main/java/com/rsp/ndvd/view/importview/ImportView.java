package com.rsp.ndvd.view.importview;

import android.graphics.Bitmap;

import com.rsp.ndvd.view.baseview.BaseView;

public interface ImportView extends BaseView {
    void reAttachFragment();

    void launchCamera(int requestImageCapture);

    void displayImagePreview(Bitmap imageBitmap);

    void toastError(String s);

    void removeFragment();
}
