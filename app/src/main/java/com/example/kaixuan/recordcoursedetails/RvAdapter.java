package com.example.kaixuan.recordcoursedetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 数据源通过构造方法传递
    private ItemTypeA itemTypeA;
    private List<ItemTypeD> mList;
    private Context context;
    public RvAdapter(Context context,ItemTypeA itemTypeA,List<ItemTypeD> mList){
        this.context = context;
        this.itemTypeA = itemTypeA;
        this.mList = mList;
    }

    public enum Item_Type{
        RECYCLERVIEW_ITEM_TYPE_A, //课程名字，授课时间和时长
        RECYCLERVIEW_ITEM_TYPE_B, //查看和分享回放（含图标）
        RECYCLERVIEW_ITEM_TYPE_C, //人数统计显示
        RECYCLERVIEW_ITEM_TYPE_D  //学生时间详情
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 从0开始计
        if (viewType == Item_Type.RECYCLERVIEW_ITEM_TYPE_A.ordinal()) {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_a,parent,false);
            RecyclerView.ViewHolder viewHolder = new ViewHolderA(view);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLERVIEW_ITEM_TYPE_B.ordinal()) {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_b,parent,false);
            RecyclerView.ViewHolder viewHolder = new ViewHolderB(view);
            return viewHolder;
        } else if (viewType == Item_Type.RECYCLERVIEW_ITEM_TYPE_C.ordinal()) {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_c,parent,false);
            RecyclerView.ViewHolder viewHolder = new ViewHolderC(view);
            return viewHolder;
        }else if(viewType == Item_Type.RECYCLERVIEW_ITEM_TYPE_D.ordinal()){
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_d,parent,false);
            RecyclerView.ViewHolder viewHolder = new ViewHolderD(view);
            return viewHolder;
        }

        return null;
    }

    //数据绑定
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  ViewHolderA){
            ((ViewHolderA) holder).tvCourseName.setText(itemTypeA.getCourseName());
            ((ViewHolderA) holder).tvTeachingTime.setText(itemTypeA.getTeachingTime());
            ((ViewHolderA) holder).tvTeachingDuration.setText(itemTypeA.getTeachingDuration());
        }else if (holder instanceof  ViewHolderB){

        }else  if(holder instanceof  ViewHolderC){

        }else if(holder instanceof ViewHolderD){

        }
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0){
            return Item_Type.RECYCLERVIEW_ITEM_TYPE_A.ordinal();
        }else if(position == 1){
            return Item_Type.RECYCLERVIEW_ITEM_TYPE_B.ordinal();
        }else if(position == 2){
            return Item_Type.RECYCLERVIEW_ITEM_TYPE_C.ordinal();
        }else if(position >= 3){
            return Item_Type.RECYCLERVIEW_ITEM_TYPE_D.ordinal();
        }
        return -1;
    }

    //返回列表长度
    @Override
    public int getItemCount() {
        return mList == null ? 0:mList.size();
    }


    class ViewHolderA extends RecyclerView.ViewHolder {
        TextView tvCourseName;
        TextView tvTeachingTime;
        TextView tvTeachingDuration;

        public ViewHolderA(@NonNull View itemView) {
            super(itemView);
            tvCourseName = (TextView) itemView.findViewById(R.id.tv_record_course_name);
            tvTeachingTime = (TextView) itemView.findViewById(R.id.tv_teaching_time);
            tvTeachingDuration = (TextView) itemView.findViewById(R.id.tv_teaching_duration);
        }
    }

    class ViewHolderB extends RecyclerView.ViewHolder {
        ImageView ivWatchPlayback;
        TextView tvWatchPlayback;
        ImageView ivWatchShare;
        TextView tvWatchShare;
        public ViewHolderB(@NonNull View itemView) {
            super(itemView);
            ivWatchPlayback = (ImageView) itemView.findViewById(R.id.iv_watch_playback);
            tvWatchPlayback = (TextView) itemView.findViewById(R.id.tv_watch_playback);
            ivWatchShare = (ImageView) itemView.findViewById(R.id.iv_watch_share);
            tvWatchShare = (TextView)itemView.findViewById(R.id.tv_watch_share);
            tvWatchPlayback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"查看回放被点击",Toast.LENGTH_SHORT);
                }
            });
            tvWatchShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"分享回放被点击",Toast.LENGTH_SHORT);
                }
            });
        }
    }

    class ViewHolderC extends RecyclerView.ViewHolder {
        TextView tvSMR;
        public ViewHolderC(@NonNull View itemView) {
            super(itemView);
            tvSMR = (TextView) itemView.findViewById(R.id.tv_student_message_record);
        }
    }
    class ViewHolderD extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        TextView tvWatchLiveTime;
        TextView tvWatchRecordTime;
        public ViewHolderD(@NonNull View itemView) {
            super(itemView);
            tvStudentName = (TextView) itemView.findViewById(R.id.tv_student_name);
            tvWatchLiveTime =(TextView) itemView.findViewById(R.id.tv_watch_live_time);
            tvWatchRecordTime =(TextView) itemView.findViewById(R.id.tv_watch_record_time);
        }
    }
}
