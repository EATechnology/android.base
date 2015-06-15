/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/11/15 1:44 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Vector;

public class MRecyclerViewAdapter extends RecyclerView.Adapter<MRecyclerViewAdapter.ViewHolder>
{

    int layoutResource = 0;
    List<?>              items;
    IOnItemClickListener onItemClickListener;
    Context              context;

    public static interface IOnItemClickListener
    {
        public <T> void onItemClick(ViewGroup viewGroup, View view, int position, T item);
    }

    public MRecyclerViewAdapter(Context context, int layoutResource, List<?> items)
    {
        this.items = items;
        this.layoutResource = layoutResource;
        this.context = context;
    }

    public MRecyclerViewAdapter(Context context, int layoutResource, Vector<?> items, IOnItemClickListener onItemClickListener)
    {
        this.items = items;
        this.onItemClickListener = onItemClickListener;
        this.layoutResource = layoutResource;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResource, viewGroup, false);
        if (onItemClickListener != null) {
            v.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int position = viewGroup.indexOfChild(v);
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(viewGroup, v, position, items.get(position));
                }
            });
        }
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public <T> T getItem(int i)
    {
        return (T) items.get(i);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View layout;

        public ViewHolder(View layout)
        {
            super(layout);
            this.layout = layout;
        }
    }

    public Context getContext()
    {
        return this.context;
    }
}