/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/7/15 2:45 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.eatech.base.R;
import org.eatech.base.model.CategoryItem;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter extends
        RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder>
{
    List<CategoryItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context        context;

    public NavigationDrawerAdapter(Context context, List<CategoryItem> data)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        CategoryItem current = data.get(position);

        holder.title.setText(current.getTitle());

        Glide.with(holder.icon.getContext())
                .load(current.getIcon())
                .into(holder.icon);
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView icon;
        TextView  title;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            itemView.setClickable(true);

            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}