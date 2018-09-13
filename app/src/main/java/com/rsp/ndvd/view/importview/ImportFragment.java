package com.rsp.ndvd.view.importview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsp.ndvd.R;
import com.rsp.ndvd.viewUtils.Constants;

import butterknife.ButterKnife;

public class ImportFragment extends Fragment implements ImportView {

    private ImportPresenter presenter;
    private ImportFragment importFragment;

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
