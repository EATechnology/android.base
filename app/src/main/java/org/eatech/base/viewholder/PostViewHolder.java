/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/11/15 2:58 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.eatech.base.R;
import org.eatech.base.helper.Consts;

public class PostViewHolder extends RecyclerView.ViewHolder
{
    private static String TAG = Consts.APP_NAME + "-" + PostViewHolder.class.getSimpleName();

    public final CardView  card;
    public final TextView  title;
    public final TextView  description;
    public final TextView  date;
    public final TextView  author;
    public final ImageView image;

    public PostViewHolder(View v)
    {
        super(v);

        card = (CardView) v.findViewById(R.id.card);

        image = (ImageView) card.findViewById(R.id.image);
        title = (TextView) card.findViewById(R.id.title);
        description = (TextView) card.findViewById(R.id.description);
        date = (TextView) card.findViewById(R.id.date);
        author = (TextView) card.findViewById(R.id.author);
    }
}