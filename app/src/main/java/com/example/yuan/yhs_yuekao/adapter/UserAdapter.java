package com.example.yuan.yhs_yuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuan.yhs_yuekao.R;
import com.example.yuan.yhs_yuekao.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<UserBean.DataBean> mDatas;
    private Context context;
    private boolean isshow;

    public UserAdapter(Context context, boolean isshow) {
        this.context = context;
        this.isshow = isshow;
        mDatas=new ArrayList<>();
    }

    public void setmDatas(List<UserBean.DataBean> datas) {
       mDatas.clear();
       mDatas.addAll(datas);
       notifyDataSetChanged();
    }
    public void addmDatas(List<UserBean.DataBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        if (isshow)
        {
            View view=View.inflate(context,R.layout.linear_item,null);
            holder=new ViewHolder(view);

        }
        else
        {
            View view=View.inflate(context,R.layout.grid_item,null);
            holder=new ViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder holder= (ViewHolder) viewHolder;
        String[] split = mDatas.get(i).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.linear_image);
        holder.linear_price.setText(mDatas.get(i).getPrice()+ "");
        holder.linear_title.setText(mDatas.get(i).getTitle());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongLinstener!=null)
                {
                    onLongLinstener.osSuccess(mDatas.get(i).getImages());
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView linear_image;
        private final TextView linear_title;
        private final TextView linear_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linear_image = itemView.findViewById(R.id.linear_image);
            linear_title = itemView.findViewById(R.id.linear_title);
            linear_price = itemView.findViewById(R.id.linear_price);
        }
    }
    OnLongLinstener onLongLinstener;

    public void setOnLongLinstener(OnLongLinstener onLongLinstener) {
        this.onLongLinstener = onLongLinstener;
    }

    public interface OnLongLinstener{
        void osSuccess(String images);
    }

}
