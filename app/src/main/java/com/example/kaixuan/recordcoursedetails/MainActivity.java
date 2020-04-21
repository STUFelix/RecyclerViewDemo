package com.example.kaixuan.recordcoursedetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ItemTypeD> list;
    private ItemTypeA itemTypeAData;
    private static final String TAG ="MainActivity";
    private String[] viewsNumber ;

    private String testName[] = new String[]{"张三","李四","王五","三个字","四个字字","五个字字"};
    private String testTime[] = new String[]{"10分钟","1分钟","100分钟","不足一分钟","6666分钟"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initItemTypeAData("腾讯小学三年一班数学课","2020年2月13日 08:45","45分钟");
        initViewsNumber();
        initData();
        initRecyclerView();
    }

    //初始化数据：课程名称，授课时间，授课时长
    private void initItemTypeAData(String courseName,String TeachingTime,String TeachingDuration){
        itemTypeAData = new ItemTypeA();
        itemTypeAData.setCourseName(courseName);//"腾讯小学三年一班数学课"
        itemTypeAData.setTeachingTime("授课时间："+TeachingTime); // "授课时间：2020年2月13日 07:45"
        itemTypeAData.setTeachingDuration("|  时长："+TeachingDuration); // "|  时长：40分钟"
    }

    //初始化数据： 本次观看直播人数，与查看回放人数
    private void initViewsNumber() {
        viewsNumber = new String[2];
        viewsNumber[0] = "399";
        viewsNumber[1] = "201";
    }

    private void initData(){
        list = new ArrayList<>();
        ItemTypeD itemTypeD = new ItemTypeD();
        itemTypeD.setStudentName("学生姓名");
        itemTypeD.setWatchLiveTime("查看直播时长");
        itemTypeD.setWatchRecordTime("查看回放时长");
        list.add(itemTypeD);
        for (int i =0 ;i<20; i++){
            itemTypeD = new ItemTypeD();
            itemTypeD.setStudentName(testName[i%testName.length]);
            itemTypeD.setWatchLiveTime(testTime[i%testTime.length]);
            itemTypeD.setWatchRecordTime(testTime[i%(testTime.length-1)]);
            list.add(itemTypeD);
        }
    }
    private void initRecyclerView(){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        RvAdapter adapter = new RvAdapter(MainActivity.this,itemTypeAData,viewsNumber,list);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemTypeBClickListener(new RvAdapter.OnItemTypeBClickListener() {
            @Override
            public void onClick(View view, RvAdapter.ClickableSubViews mSubViews, int position) {
                if(mSubViews == RvAdapter.ClickableSubViews.TV_WATCH_PLAYBACK){
                    Toast.makeText(MainActivity.this,"查看回放被点击",Toast.LENGTH_SHORT).show();
                }else if(mSubViews == RvAdapter.ClickableSubViews.TV_SHARE_PLAYBACK){
                    Toast.makeText(MainActivity.this,"分享回放被点击",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
