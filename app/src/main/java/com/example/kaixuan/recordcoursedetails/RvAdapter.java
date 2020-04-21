package com.example.kaixuan.recordcoursedetails;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    // 数据源通过构造方法传递
    private ItemTypeA itemTypeA;
    private List<ItemTypeD> mList;
    private Context context;
    private static final String TAG = "RvAdapter";
    private String[] numberViewers;


    public RvAdapter(Context context, ItemTypeA itemTypeA, String[] numberViewers, List<ItemTypeD> mList) {
        this.context = context;
        this.itemTypeA = itemTypeA;
        this.mList = mList;
        this.numberViewers = numberViewers;
    }


    public enum Item_Type {
        RECYCLERVIEW_ITEM_TYPE_A, //课程名字，授课时间和时长
        RECYCLERVIEW_ITEM_TYPE_B, //查看和分享回放（含图标）
        RECYCLERVIEW_ITEM_TYPE_C, //人数统计显示
        RECYCLERVIEW_ITEM_TYPE_D  //学生时间详情
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 从0开始计 .ordinal()
        if (viewType == Item_Type.RECYCLERVIEW_ITEM_TYPE_A.ordinal()) {
            return new ViewHolderA(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_a, parent, false));
        } else if (viewType == Item_Type.RECYCLERVIEW_ITEM_TYPE_B.ordinal()) {
            return new ViewHolderB(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_b, parent, false));
        } else if (viewType == Item_Type.RECYCLERVIEW_ITEM_TYPE_C.ordinal()) {
            return new ViewHolderC(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_c, parent, false));
        } else if (viewType == Item_Type.RECYCLERVIEW_ITEM_TYPE_D.ordinal()) {
            return new ViewHolderD(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_d, parent, false));
        }
        return null;
    }

    //数据绑定
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderA) {
            ((ViewHolderA) holder).tvCourseName.setText(itemTypeA.getCourseName());
            ((ViewHolderA) holder).tvTeachingTime.setText(itemTypeA.getTeachingTime());
            ((ViewHolderA) holder).tvTeachingDuration.setText(itemTypeA.getTeachingDuration());
        } else if (holder instanceof ViewHolderB) {
            ((ViewHolderB) holder).ivWatchPlayback.setTag(position);
            ((ViewHolderB) holder).tvWatchPlayback.setTag(position);
            ((ViewHolderB) holder).ivSharePlayback.setTag(position);
            ((ViewHolderB) holder).tvSharePlayback.setTag(position);

        } else if (holder instanceof ViewHolderC) {
            SpannableString spannableString1 = new SpannableString("本次观看直播 " + numberViewers[0] + " 人");
            ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(Color.parseColor("#2196F3"));
            spannableString1.setSpan(foregroundColorSpan1, 7, 7 + numberViewers[0].length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            ((ViewHolderC) holder).tvNumberOfLiveViewers.setText(spannableString1);

            SpannableString spannableString2 = new SpannableString(",查看回放 " + numberViewers[1] + " 人");
            ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(Color.parseColor("#2196F3"));
            spannableString2.setSpan(foregroundColorSpan2, 6, 6 + numberViewers[1].length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            ((ViewHolderC) holder).tvNumberOfPlaybackViewers.setText(spannableString2);

        } else if (holder instanceof ViewHolderD) {
            if (position == 3) {
                ((ViewHolderD) holder).tvStudentName.setTextColor(Color.parseColor("#7c8288"));
                ((ViewHolderD) holder).tvStudentName.setText(mList.get(position - 3).getStudentName());
                ((ViewHolderD) holder).tvWatchLiveTime.setTextColor(Color.parseColor("#7c8288"));
                ((ViewHolderD) holder).tvWatchLiveTime.setText(mList.get(position - 3).getWatchLiveTime());
                ((ViewHolderD) holder).tvWatchRecordTime.setTextColor(Color.parseColor("#7c8288"));
                ((ViewHolderD) holder).tvWatchRecordTime.setText(mList.get(position - 3).getWatchRecordTime());
            } else {
                ((ViewHolderD) holder).tvStudentName.setText(mList.get(position - 3).getStudentName());
                ((ViewHolderD) holder).tvWatchLiveTime.setText(mList.get(position - 3).getWatchLiveTime());
                ((ViewHolderD) holder).tvWatchRecordTime.setText(mList.get(position - 3).getWatchRecordTime());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Item_Type.RECYCLERVIEW_ITEM_TYPE_A.ordinal();
        } else if (position == 1) {
            return Item_Type.RECYCLERVIEW_ITEM_TYPE_B.ordinal();
        } else if (position == 2) {
            return Item_Type.RECYCLERVIEW_ITEM_TYPE_C.ordinal();
        } else if (position >= 3) {
            return Item_Type.RECYCLERVIEW_ITEM_TYPE_D.ordinal();
        }
        return -1;
    }

    //返回列表长度
    @Override
    public int getItemCount() {
        return mList == null ? 3 : mList.size() + 3;
    }


    //--------------- item 点击处理 ----------- ItemTypeB
    private OnItemTypeBClickListener onItemTypeBClickListener = null;

    public enum ClickableSubViews {
        TV_WATCH_PLAYBACK,
        TV_SHARE_PLAYBACK
    }

    public interface OnItemTypeBClickListener {
        void onClick(View view, ClickableSubViews mSubViews, int position);
    }


    public void setOnItemTypeBClickListener(OnItemTypeBClickListener onItemTypeBClickListener) {
        this.onItemTypeBClickListener = onItemTypeBClickListener;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.iv_watch_playback:
            case R.id.tv_watch_playback:
                onItemTypeBClickListener.onClick(v, ClickableSubViews.TV_WATCH_PLAYBACK, position);
                break;
            case R.id.iv_share_playback:
            case R.id.tv_share_playback:
                onItemTypeBClickListener.onClick(v, ClickableSubViews.TV_SHARE_PLAYBACK, position);
                break;
            default:
                break;
        }
    }
    //---------------------

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
        ImageView ivSharePlayback;
        TextView tvSharePlayback;

        public ViewHolderB(@NonNull View itemView) {
            super(itemView);
            ivWatchPlayback = (ImageView) itemView.findViewById(R.id.iv_watch_playback);
            tvWatchPlayback = (TextView) itemView.findViewById(R.id.tv_watch_playback);
            ivSharePlayback = (ImageView) itemView.findViewById(R.id.iv_share_playback);
            tvSharePlayback = (TextView) itemView.findViewById(R.id.tv_share_playback);

            ivWatchPlayback.setOnClickListener(RvAdapter.this);
            tvWatchPlayback.setOnClickListener(RvAdapter.this);
            ivSharePlayback.setOnClickListener(RvAdapter.this);
            tvSharePlayback.setOnClickListener(RvAdapter.this);
        }

    }


    class ViewHolderC extends RecyclerView.ViewHolder {
        TextView tvNumberOfLiveViewers;
        TextView tvNumberOfPlaybackViewers;

        public ViewHolderC(@NonNull View itemView) {
            super(itemView);
            tvNumberOfLiveViewers = (TextView) itemView.findViewById(R.id.tv_number_of_live_viewers);
            tvNumberOfPlaybackViewers = (TextView) itemView.findViewById(R.id.tv_number_of_playback_viewers);
        }
    }

    class ViewHolderD extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        TextView tvWatchLiveTime;
        TextView tvWatchRecordTime;

        public ViewHolderD(@NonNull View itemView) {
            super(itemView);
            tvStudentName = (TextView) itemView.findViewById(R.id.tv_student_name);
            tvWatchLiveTime = (TextView) itemView.findViewById(R.id.tv_watch_live_time);
            tvWatchRecordTime = (TextView) itemView.findViewById(R.id.tv_watch_record_time);
        }
    }
}
