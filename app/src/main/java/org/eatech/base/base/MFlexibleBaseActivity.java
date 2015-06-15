/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 6/1/15 5:02 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.eatech.base.R;

import java.util.ArrayList;

public abstract class MFlexibleBaseActivity extends AppCompatActivity
{
    private static final int NUM_OF_ITEMS     = 100;
    private static final int NUM_OF_ITEMS_FEW = 3;

    protected int getActionBarSize()
    {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[] { R.attr.actionBarSize };
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    protected int getScreenHeight()
    {
        return findViewById(android.R.id.content).getHeight();
    }

    public static ArrayList<String> getDummyData()
    {
        return getDummyData(NUM_OF_ITEMS);
    }

    public static ArrayList<String> getDummyData(int num)
    {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            items.add("Item " + i);
        }
        return items;
    }

    protected void setDummyData(ListView listView)
    {
        setDummyData(listView, NUM_OF_ITEMS);
    }

    protected void setDummyDataFew(ListView listView)
    {
        setDummyData(listView, NUM_OF_ITEMS_FEW);
    }

    protected void setDummyData(ListView listView, int num)
    {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getDummyData(num)));
    }

    protected void setDummyDataWithHeader(ListView listView, int headerHeight)
    {
        setDummyDataWithHeader(listView, headerHeight, NUM_OF_ITEMS);
    }

    protected void setDummyDataWithHeader(ListView listView, int headerHeight, int num)
    {
        View headerView = new View(this);
        headerView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, headerHeight));
        headerView.setMinimumHeight(headerHeight);
        // This is required to disable header's list selector effect
        headerView.setClickable(true);
        setDummyDataWithHeader(listView, headerView, num);
    }

    protected void setDummyDataWithHeader(ListView listView, View headerView, int num)
    {
        listView.addHeaderView(headerView);
        setDummyData(listView, num);
    }

    protected void setDummyData(GridView gridView)
    {
        gridView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getDummyData()));
    }

    protected void setDummyData(RecyclerView recyclerView)
    {
        setDummyData(recyclerView, NUM_OF_ITEMS);
    }

    protected void setDummyDataFew(RecyclerView recyclerView)
    {
        setDummyData(recyclerView, NUM_OF_ITEMS_FEW);
    }

    protected void setDummyData(RecyclerView recyclerView, int num)
    {
        recyclerView.setAdapter(new SimpleRecyclerAdapter(this, getDummyData(num)));
    }

    protected void setDummyDataWithHeader(RecyclerView recyclerView, int headerHeight)
    {
        View headerView = new View(this);
        headerView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, headerHeight));
        headerView.setMinimumHeight(headerHeight);
        // This is required to disable header's list selector effect
        headerView.setClickable(true);
        setDummyDataWithHeader(recyclerView, headerView);
    }

    protected void setDummyDataWithHeader(RecyclerView recyclerView, View headerView)
    {
        recyclerView.setAdapter(new SimpleHeaderRecyclerAdapter(this, getDummyData(), headerView));
    }

    public static class SimpleHeaderRecyclerAdapter extends
                                                    RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        private static final int VIEW_TYPE_HEADER = 0;
        private static final int VIEW_TYPE_ITEM   = 1;

        private LayoutInflater    mInflater;
        private ArrayList<String> mItems;
        private View              mHeaderView;

        public SimpleHeaderRecyclerAdapter(Context context, ArrayList<String> items,
                                           View headerView)
        {
            mInflater = LayoutInflater.from(context);
            mItems = items;
            mHeaderView = headerView;
        }

        @Override
        public int getItemCount()
        {
            if (mHeaderView == null) {
                return mItems.size();
            } else {
                return mItems.size() + 1;
            }
        }

        @Override
        public int getItemViewType(int position)
        {
            return (position == 0) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            if (viewType == VIEW_TYPE_HEADER) {
                return new HeaderViewHolder(mHeaderView);
            } else {
                return new ItemViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
        {
            if (viewHolder instanceof ItemViewHolder) {
                ((ItemViewHolder) viewHolder).textView.setText(mItems.get(position - 1));
            }
        }

        static class HeaderViewHolder extends RecyclerView.ViewHolder
        {
            public HeaderViewHolder(View view)
            {
                super(view);
            }
        }

        static class ItemViewHolder extends RecyclerView.ViewHolder
        {
            TextView textView;

            public ItemViewHolder(View view)
            {
                super(view);
                textView = (TextView) view.findViewById(android.R.id.text1);
            }
        }
    }

    public static class SimpleRecyclerAdapter extends
                                              RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder>
    {
        private LayoutInflater    mInflater;
        private ArrayList<String> mItems;

        public SimpleRecyclerAdapter(Context context, ArrayList<String> items)
        {
            mInflater = LayoutInflater.from(context);
            mItems = items;
        }

        @Override
        public int getItemCount()
        {
            return mItems.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new ViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position)
        {
            viewHolder.textView.setText(mItems.get(position));
        }

        static class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textView;

            public ViewHolder(View view)
            {
                super(view);
                textView = (TextView) view.findViewById(android.R.id.text1);
            }
        }
    }
}