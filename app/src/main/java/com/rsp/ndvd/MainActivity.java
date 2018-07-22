package com.rsp.ndvd;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rsp.ndvd.Model.Member;
import com.rsp.ndvd.ViewUtils.DividerItemDecoration;
import com.rsp.ndvd.ViewUtils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final int VERTICAL_ITEM_SPACE = 10;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Member> members;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.members_rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        initializeData();
        // specify an adapter (see also next example)
        mAdapter = new MembersAdapter(this, members);
        recyclerView.setAdapter(mAdapter);
    }



    // This method creates an ArrayList that has three Member objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        members = new ArrayList<>();
        members.add(new Member("Emma Wilson", "23 years old", R.mipmap.member_default));
        members.add(new Member("Lavery Maiss", "25 years old", R.mipmap.member_default));
        members.add(new Member("Lillie Watts", "35 years old", R.mipmap.member_default));
        members.add(new Member("Emma Wilson", "23 years old", R.mipmap.member_default));
        members.add(new Member("Lavery Maiss", "25 years old", R.mipmap.member_default));
        members.add(new Member("Lillie Watts", "35 years old", R.mipmap.member_default));
        members.add(new Member("Emma Wilson", "23 years old", R.mipmap.member_default));
        members.add(new Member("Lavery Maiss", "25 years old", R.mipmap.member_default));
        members.add(new Member("Lillie Watts", "35 years old", R.mipmap.member_default));
        members.add(new Member("Emma Wilson", "23 years old", R.mipmap.member_default));
        members.add(new Member("Lavery Maiss", "25 years old", R.mipmap.member_default));
        members.add(new Member("Lillie Watts", "35 years old", R.mipmap.member_default));
        members.add(new Member("Emma Wilson", "23 years old", R.mipmap.member_default));
        members.add(new Member("Lavery Maiss", "25 years old", R.mipmap.member_default));
        members.add(new Member("Lillie Watts", "35 years old", R.mipmap.member_default));

    }
}
