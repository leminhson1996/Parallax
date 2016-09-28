package com.example.lemin.parallaxgannha;

import android.database.DataSetObserver;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    ListView listView;
    private int lastTop = -16;

    ArrayAdapter adapter;
    ArrayList<String> items = new ArrayList<String>();

    public void parallax(final View v) {
        final Rect r = new Rect();
        v.getLocalVisibleRect(r);

        if (lastTop != r.top) {
            lastTop = r.top;
            v.post(new Runnable() {
                @Override
                public void run() {
                    v.setY((float) (r.top / 2));
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        items.add("List Item 1");
        items.add("List Item 2");
        items.add("List Item 3");
        items.add("List Item 4");
        items.add("List Item 5");
        items.add("List Item 6");
        items.add("List Item 7");
        items.add("List Item 8");
        items.add("List Item 9");
        items.add("List Item 10");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        View view = getLayoutInflater().inflate(R.layout.header, null, false);
        image = (ImageView) view.findViewById(R.id.image);
        listView.addHeaderView(view);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                parallax(image);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                parallax(image);
            }

        });
    }
}
