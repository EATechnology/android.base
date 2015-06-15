/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/10/15 10:24 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import org.eatech.base.R;
import org.eatech.base.helper.App;
import org.eatech.base.helper.Consts;
import org.eatech.base.helper.DateTime;
import org.eatech.base.model.PostItem;
import org.eatech.base.viewholder.PostViewHolder;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>
{
    private static String TAG = Consts.APP_NAME + "-" + PostAdapter.class.getSimpleName();

    public ArrayList<PostItem> mDataset;
    public Context             mContext;
    public boolean             mShowImages;

    public PostAdapter(Context context)
    {
        this.mContext = context;
        this.mShowImages = getSettings().getBoolean(Consts.SETTINGS_SHOW_IMAGES, true);
        this.mDataset = new ArrayList<PostItem>();
    }

    public SharedPreferences getSettings()
    {
        return App.getSettings(mContext);
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View v = null;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.post_list_row, viewGroup, false);

        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostViewHolder viewHolder, final int position)
    {
        PostItem item = mDataset.get(position);

        viewHolder.title.setText(item.getTitle());
        viewHolder.date.setText(DateTime.getTimeAgo(item.getDate(), mContext));
        viewHolder.author.setText(item.getAuthor());
        viewHolder.description.setText(item.getDescription());

        if (mShowImages) {
            viewHolder.image.setVisibility(View.VISIBLE);

            Glide.with(viewHolder.image.getContext())
                    .load(item.getImage())
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .crossFade()
                    .into(viewHolder.image);
        } else {
            viewHolder.image.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount()
    {
        return (mDataset != null) ? mDataset.size() : 0;
    }

    public void add(PostItem postItem)
    {
        mDataset.add(postItem);

        notifyDataSetChanged();
    }
}