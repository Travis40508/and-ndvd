package com.rsp.ndvd.view.importview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rsp.ndvd.R;
import com.rsp.ndvd.viewUtils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImportFragment extends Fragment implements ImportView {

    @BindView(R.id.input_first_name)
    protected TextInputEditText firstNameInput;

    @BindView(R.id.input_last_name)
    protected TextInputEditText lastNameInput;

    @BindView(R.id.input_email)
    protected TextInputEditText emailInput;

    @BindView(R.id.input_mobile_phone)
    protected TextInputEditText mobilePhoneInput;

    @BindView(R.id.input_home_phone)
    protected TextInputEditText homePhoneInput;

    @BindView(R.id.image_taken)
    protected ImageView imageTaken;

    private ImportPresenter presenter;
    private ImportFragment importFragment;

    @OnClick(R.id.button_save_form)
    protected void onSaveClicked(View view) {

    }

    @OnClick(R.id.button_take_picture)
    protected void onTakePictureClicked(View view) {
        presenter.takePictureClicked();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_import, container, false);
        ButterKnife.bind(this, view);
        presenter = new ImportPresenter();
        presenter.attachView(this);
        presenter.checkScreenRotate(savedInstanceState != null && importFragment != null);

        return view;
    }

    public static ImportFragment newInstance() {

        Bundle args = new Bundle();

        ImportFragment fragment = new ImportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void reAttachFragment() {
        importFragment = (ImportFragment) getActivity().getSupportFragmentManager().findFragmentByTag(Constants.IMPORT_FRAGMENT_TAG);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, importFragment, Constants.IMPORT_FRAGMENT_TAG).commit();
    }

    @Override
    public void launchCamera(int requestImageCapture) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, requestImageCapture);
    }

    @Override
    public void displayImagePreview(Bitmap imageBitmap) {
        imageTaken.setImageBitmap(imageBitmap);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.retrieveImage(requestCode, resultCode, data);
    }
}
