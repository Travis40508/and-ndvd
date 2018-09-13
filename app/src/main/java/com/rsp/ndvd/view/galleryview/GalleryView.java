package com.rsp.ndvd.view.galleryview;

import com.rsp.ndvd.model.Member;
import com.rsp.ndvd.view.baseview.BaseView;

import java.util.List;

public interface GalleryView extends BaseView {
    void showMembersGallery(List<Member> members);
}
