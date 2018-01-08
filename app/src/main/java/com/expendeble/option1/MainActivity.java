package com.expendeble.option1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.expendeble.R;
import com.expendeble.option1.model.Child;
import com.expendeble.option1.model.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {


   // final static String url="https://raw.githubusercontent.com/karunkumar25/Api_Test/master/test.json";
    final static String url=" https://raw.githubusercontent.com/karunkumar25/Api_Test/master/test2";

    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);

        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        jsonParse();
       setListener();

    }

    private void jsonParse() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        AndroidNetworking.get(url).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {

                ArrayList<Group> list = new ArrayList<Group>();
                ArrayList<Child> ch_list;

                try {
                    Iterator<String> key = response.keys();
                    while (key.hasNext()) {
                        String k = key.next();

                        Group gru = new Group();
                        gru.setName(k);
                        ch_list = new ArrayList<Child>();

                        JSONArray ja = response.getJSONArray(k);

                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            Child ch = new Child();
                            ch.setTop_id(jo.getString("top_id"));
                            ch.setTop_name(jo.getString("top_name"));

                            ch_list.add(ch);

                        } // for loop end
                        gru.setItems(ch_list);
                        list.add(gru);
                    }

                    adapter = new ExpandableListAdapter(MainActivity.this,list);
                    expandableListView.setAdapter(adapter);
                    pDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }



    // Setting different listeners to expandablelistview
    void setListener() {

        // This listener will show toast on group click
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

             //   Toast.makeText(MainActivity.this, "You clicked : " + adapter.getGroup(group_pos), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        expandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup) expandableListView.collapseGroup(previousGroup);
                            previousGroup = groupPosition;
                    }

                });

        // This listener will show toast on child click
        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
              //  Toast.makeText(MainActivity.this, "You clicked : " + adapter.getChild(groupPos, childPos), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}