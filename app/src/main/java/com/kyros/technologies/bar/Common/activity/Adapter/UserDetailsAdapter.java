package com.kyros.technologies.bar.Common.activity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyros.technologies.bar.Common.activity.model.BarAccess;
import com.kyros.technologies.bar.R;
import com.kyros.technologies.bar.utils.UserDetail;

import java.util.ArrayList;

/**
 * Created by Rohin on 06-06-2017.
 */

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.MyViewHolderEleven> {
    OnClickInAdapter onClickInAdapter;
    private Context mContext;
    private ArrayList<UserDetail> userDetailArrayList;
    private ArrayList<BarAccess> barAccess;

    public class MyViewHolderEleven extends RecyclerView.ViewHolder{
        public TextView bar_name;
        public CheckBox bar_checkbox;

        public MyViewHolderEleven(View itemView) {
            super(itemView);
            bar_name=(TextView)itemView.findViewById(R.id.bar_name);
            bar_checkbox=(CheckBox)itemView.findViewById(R.id.bar_checkbox);
        }
    }
    public UserDetailsAdapter(Context mContext,ArrayList<UserDetail>userDetailArrayList,ArrayList<BarAccess> barAccess){
        this.mContext=mContext;
        this.userDetailArrayList=userDetailArrayList;
        this.barAccess=barAccess;


    }
    @Override
    public UserDetailsAdapter.MyViewHolderEleven onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user_details,parent,false);

        return new UserDetailsAdapter.MyViewHolderEleven(view);
    }

    @Override
    public void onBindViewHolder(UserDetailsAdapter.MyViewHolderEleven holder, final int position) {

        UserDetail userDetail=userDetailArrayList.get(position);
        final int id=userDetail.getId();
        final int userprofileid=userDetail.getUserprofileid();
        final String barname=userDetail.getBarname();
        final String createdon=userDetail.getCreatedon();
        final String modifiedon=userDetail.getModifiedon();
        if(barAccess!=null){
            for(int i=0;i<barAccess.size();i++){
                String barnames=barAccess.get(i).getBarName();
                if(barnames.equals(barname)){
                    holder.bar_checkbox.setChecked(true);
                }
            }

        }

        try {
            holder.bar_name.setText(barname);

        }catch (Exception e){
            e.printStackTrace();
        }

        holder.bar_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserDetail.getHolder().setId(id);
                UserDetail.getHolder().setBarname(barname);
                UserDetail.getHolder().setCreatedon(createdon);
                UserDetail.getHolder().setModifiedon(modifiedon);
                try {
                    onClickInAdapter = (OnClickInAdapter) mContext;
                } catch (ClassCastException e) {
                    throw new ClassCastException(mContext.toString()
                            + " must implement OnClickInAdapter");
                }
                onClickInAdapter.onClickInAdapter(id,barname);
            }
        });

        //   holder.bottle_pic.setBackgroundResource(Bottleimages[position]);
    }

    @Override
    public int getItemCount() {
        return userDetailArrayList.size();
    }
public interface OnClickInAdapter{
    public void onClickInAdapter(int BarId,String BarName);
}
}