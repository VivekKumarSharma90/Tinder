package com.vivek.android2.tinder;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vivek.android2.tinder.tindercard.FlingCardListener;
import com.vivek.android2.tinder.tindercard.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FlingCardListener.ActionDownInterface {
    //public static Adapter adapter;
    public static MyAppAdapter adapter;
    ArrayList<Data> arrayList;
    SwipeFlingAdapterView swipeFlingAdapterView;
    public static Holder holder;
    TextView like, dislike;

    public static void removeBackground() {


        holder.background.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame);
        swipeFlingAdapterView = (SwipeFlingAdapterView) findViewById(R.id.frame);
        like = (TextView) findViewById(R.id.like);
        dislike = (TextView) findViewById(R.id.dislike);
        arrayList = new ArrayList<Data>();
        arrayList.add(new Data("1"));
        arrayList.add(new Data("2"));
        arrayList.add(new Data("3"));
        arrayList.add(new Data("4"));
        arrayList.add(new Data("5"));
        //adapter = new Adapter(this, R.layout.item, arrayList);
        adapter = new MyAppAdapter(arrayList, this);
        swipeFlingAdapterView.setAdapter(adapter);
        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                arrayList.remove(0);
                adapter.notifyDataSetChanged();
                //Do something on the left!
                //You arrayListso have access to the originarrayList object.
                //If you want to use it just cast it (String) dataObject

            }

            @Override
            public void onRightCardExit(Object dataObject) {

                arrayList.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = swipeFlingAdapterView.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.disliked).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.liked).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                //view.findViewById(R.id.item_swipe_right_indicator).setarrayListpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                //view.findViewById(R.id.item_swipe_left_indicator).setarrayListpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // OptionarrayListly add an OnItemClickListener
        swipeFlingAdapterView.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                View view = swipeFlingAdapterView.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public static class Holder {
        public static FrameLayout background;
        ImageView imageView;
        TextView textView;
    }

    @Override
    public void onActionDownPerform() {

    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Data> parkingList;
        public Context context;

        private MyAppAdapter(List<Data> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                holder = new Holder();
                holder.textView = (TextView) rowView.findViewById(R.id.text_view);
                holder.background = (FrameLayout) rowView.findViewById(R.id.background);
                holder.imageView = (ImageView) rowView.findViewById(R.id.img);
                rowView.setTag(holder);

            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.textView.setText(parkingList.get(position).getDescription() + "");
            holder.imageView.setImageResource(R.drawable.amitabh);
            //Glide.with(MainActivity.this).load(parkingList.get(position).getImagePath()).into(holder.imageView);

            return rowView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automaticarrayListly handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
