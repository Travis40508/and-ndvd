package com.rsp.ndvd.viewModel;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsp.ndvd.R;
import com.rsp.ndvd.model.Member;
import com.rsp.ndvd.viewUtils.Utils;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {
    private Context context;
    private List<Member> members;
    private int[] cardColors;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView memberFirstName;
        TextView memberLastName;
        TextView memberMobile;
        TextView memberPhone;
        TextView memberEmail;
        ImageView memberPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            memberFirstName = (TextView)itemView.findViewById(R.id.member_firstName);
            memberLastName = (TextView)itemView.findViewById(R.id.member_lastName);
            memberPhoto = (ImageView)itemView.findViewById(R.id.member_photo);
            memberPhone = (TextView) itemView.findViewById(R.id.member_phone);
            memberMobile = (TextView) itemView.findViewById(R.id.member_mobile);
            memberEmail = (TextView) itemView.findViewById(R.id.member_email);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MembersAdapter(List<Member> members, Context context) {
        this.members = members;
        this.context = context;
        this.cardColors = Utils.getCardColors(context);
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
        Member member = members.get(position);
        holder.memberFirstName.setText(member.getFirstName());
        holder.memberLastName.setText(member.getLastName());
        holder.memberPhoto.setImageResource((member.getPhotoId()<=0)? R.mipmap.member_default : member.getPhotoId());
        holder.memberPhone.setText(String.format("%s", member.getPhoneNumber()));
        holder.memberMobile.setText(String.format("%s", member.getMobileNumber()));
        holder.memberEmail.setText(member.getEmail());
        holder.cv.setBackgroundColor(cardColors[position % cardColors.length]);
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