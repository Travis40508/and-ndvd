package com.rsp.ndvd;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rsp.ndvd.Model.Member;
import com.rsp.ndvd.ViewUtils.DividerItemDecoration;
import com.rsp.ndvd.ViewUtils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private static final String TAG = "NDVD.MainActivity";
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
    }

    private void initializeData() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReferenceFromUrl("https://ndvd-1464b.firebaseio.com");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value.toString());
                if (members == null) {
                    members = new ArrayList<>();
                }
                for (DataSnapshot parent : dataSnapshot.getChildren()) {
                    for (DataSnapshot child : parent.getChildren()) {
                        Member member = child.getValue(Member.class);
                        if (member != null) {
                            members.add(member);
                        }
                    }
                    if (members != null) {
                        // specify an adapter (see also next example)
                        mAdapter = new MembersAdapter(MainActivity.this, members);
                        recyclerView.setAdapter(mAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
