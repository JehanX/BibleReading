package com.afc.biblereading;



import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.app.Activity;



public class Tabs extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//    	super.onCreate(savedInstanceState);
//    	
//        setContentView(R.layout.activity_tabs);
//
//        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//
//        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
//
//        mTabHost.addTab(
//                mTabHost.newTabSpec("User Info").setIndicator("User Info", null),
//                First.class, null);
//
//        mTabHost.addTab(
//                mTabHost.newTabSpec("Fami Info").setIndicator("Fami Info", null),
//                Second.class, null);
    	super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_tabs);
        setTitle("�麣������Android�ͻ���");
        
        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        
        
        
        LayoutInflater.from(this).inflate(R.layout.acitvity_first, tabs.getTabContentView(),true);   //�������ļ���TabHost������һ��
     	LayoutInflater.from(this).inflate(R.layout.acitvity_second, tabs.getTabContentView(),true);
      
      
     	tabs.addTab(tabs.newTabSpec("TAB1").setIndicator("���Բ���").setContent(R.id.layout01));  //setContent()ָ��ÿ��Tab������View
     	tabs.addTab(tabs.newTabSpec("TAB2").setIndicator("���Բ���").setContent(R.id.layout02));
        
        
        tabs.setCurrentTab(0);

    }
}