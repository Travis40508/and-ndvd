package com.rsp.ndvd.view.galleryview;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsp.ndvd.R;
import com.rsp.ndvd.model.Member;
import com.rsp.ndvd.viewModel.MembersAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryFragment extends Fragment implements GalleryView {

    @BindView(R.id.recycler_view_gallery)
    protected RecyclerView recyclerView;
    private GalleryPresenter presenter;
    private MembersAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);
        presenter = new GalleryPresenter();
        presenter.attachView(this);

        return view;
    }

    public static GalleryFragment newInstance() {

        Bundle args = new Bundle();

        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void showMembersGallery(List<Member> members) {
        adapter = new MembersAdapter(members, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));
        adapter.notifyDataSetChanged();
    }
}
