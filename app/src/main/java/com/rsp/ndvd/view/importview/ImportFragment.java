package com.rsp.ndvd.view.importview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private ImportPresenter presenter;
    private ImportFragment importFragment;

    @OnClick(R.id.button_save_form)
    protected void onSaveClicked(View view) {

    }

    @OnClick(R.id.button_take_picture)
    protected void onTakePictureClicked(View view) {
        
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
}
