package com.example.kaixuan.recordcoursedetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ItemTypeD> list;
    private ItemTypeA itemTypeA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initRv();
    }

    private void initData(){
        itemTypeA = new ItemTypeA();
        itemTypeA.setCourseName("腾讯小学三年一班数学课");
        itemTypeA.setTeachingTime("授课时间：2020年2月13日 07:45");
        itemTypeA.setTeachingDuration("|  时长：40分钟");

        list = new ArrayList<>();
        for (int i =0 ;i<10; i++){
            ItemTypeD itemTypeD = new ItemTypeD();
            itemTypeD.setStudentName("f"+i);
            itemTypeD.setWatchLiveTime("12"+i+"分钟");
            itemTypeD.setWatchRecordTime("12"+i+"分钟");
            list.add(itemTypeD);
        }
    }
    private void initRv(){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        RvAdapter adapter = new RvAdapter(MainActivity.this,itemTypeA,list);
        recyclerView.setAdapter(adapter);

    }
}
