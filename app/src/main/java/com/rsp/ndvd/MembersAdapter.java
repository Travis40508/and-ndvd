package com.rsp.ndvd;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsp.ndvd.Model.Member;

import org.w3c.dom.Text;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {
    private List<Member> members;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView memberName;
        TextView memberAge;
        TextView memberPhone;
        ImageView memberPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            memberName = (TextView)itemView.findViewById(R.id.member_name);
            memberAge = (TextView)itemView.findViewById(R.id.member_age);
            memberPhoto = (ImageView)itemView.findViewById(R.id.member_photo);
            memberPhone = (TextView) itemView.findViewById(R.id.member_phone);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MembersAdapter(Context context, List<Member> members) {
        this.members = members;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MembersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.member_card,
                                                                    viewGroup,
                                                                    false);
        ViewHolder memberViewHolder = new ViewHolder(v);
        return memberViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.memberName.setText(members.get(position).getName());
        holder.memberAge.setText(members.get(position).getAge());
        holder.memberPhoto.setImageResource(members.get(position).getPhotoId());
        holder.memberPhone.setText("9876543210");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return members.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}