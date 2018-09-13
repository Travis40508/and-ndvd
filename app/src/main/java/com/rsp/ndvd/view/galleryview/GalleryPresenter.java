package com.rsp.ndvd.view.galleryview;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rsp.ndvd.model.Member;
import com.rsp.ndvd.view.baseview.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class GalleryPresenter implements BasePresenter<GalleryView> {

    private CompositeDisposable disposable;
    private GalleryView view;
    private DatabaseReference myRef;
    private ValueEventListener valueEventListener;
    private List<Member> members;

    public GalleryPresenter() {

    }

    @Override
    public void subscribe() {
        disposable = new CompositeDisposable();

        initGalleryData();
    }

    private void initGalleryData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://ndvd-1464b.firebaseio.com/");

        members = new ArrayList<>();
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot parent : dataSnapshot.getChildren()) {
                    for (DataSnapshot child : parent.getChildren()) {
                        Member member = child.getValue(Member.class);
                        if (member != null) {
                            members.add(member);
                        }
                    }
                }
                view.showMembersGallery(members);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myRef.addValueEventListener(valueEventListener);
    }

    @Override
    public void unsubscribe() {
        disposable.clear();
    }

    @Override
    public void attachView(GalleryView view) {
        this.view = view;
    }
}
