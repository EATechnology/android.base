/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 6/11/15 10:09 AM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.eatech.base.R;
import org.eatech.base.adapter.PostAdapter;
import org.eatech.base.base.MEndlessRecyclerOnScrollListener;
import org.eatech.base.base.MPostListFragmentBase;
import org.eatech.base.base.MRecyclerItemClickListener;
import org.eatech.base.ga.Events;
import org.eatech.base.helper.Consts;
import org.eatech.base.helper.Utils;
import org.eatech.base.model.PostItem;

import java.util.ArrayList;
import java.util.Date;

public class PostListFragment extends MPostListFragmentBase implements SwipeRefreshLayout.OnRefreshListener
{
    private static String TAG = Consts.APP_NAME + "-" + PostListFragment.class.getSimpleName();

    private int     feedPage    = 1;
    private boolean feedEnds    = false;
    private boolean isConnected = true;

    private String category_title = "All";
    private String category_slug  = "all";

    public PostListFragment()
    {
    }

    public static PostListFragment newInstance(String title, String slug)
    {
        PostListFragment fragment = new PostListFragment();

        Bundle args = new Bundle();
        args.putString("category_title", title);
        args.putString("category_slug", slug);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String title = getArguments().getString("category_title");
            String slug = getArguments().getString("category_slug");

            if (slug != null && title != null) {
                category_title = title;
                category_slug = slug;
            }
        }

        Events.categoryOpen(getActivity(), category_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        mAdapter = new PostAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnTouchListener(__eventListTouch());
        mRecyclerView.setOnScrollListener(__eventListScroll());
        mRecyclerView.addOnItemTouchListener(__eventItemClick());
        mSwipeRefreshLayout.setOnRefreshListener(this);

        initDataset();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                showList(true);
            }
        }, 200);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        Events.screenName(getActivity(), getString(R.string.screen_post_list));
    }

    private MEndlessRecyclerOnScrollListener __eventListScroll()
    {
        return new MEndlessRecyclerOnScrollListener()
        {

            @Override
            public void onLoadMore(int current_page)
            {
                showList(false);
            }
        };
    }

    private MRecyclerItemClickListener __eventItemClick()
    {
        return new MRecyclerItemClickListener(getActivity(), new MRecyclerItemClickListener.OnItemClickListener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                if (mAdapter.mDataset.size() > 0) {

                }
            }
        });
    }

    @Override
    public void onRefresh()
    {
        setRefreshing(false);
    }

    private void initDataset()
    {
        mAdapter.add(new PostItem(R.drawable.placeholder, "Title", "Author", "Description", (new Date()).getTime()));
        mAdapter.add(new PostItem(R.drawable.placeholder, "Title", "Author", "Description", (new Date()).getTime()));
        mAdapter.add(new PostItem(R.drawable.placeholder, "Title", "Author", "Description", (new Date()).getTime()));
        mAdapter.add(new PostItem(R.drawable.placeholder, "Title", "Author", "Description", (new Date()).getTime()));
        mAdapter.add(new PostItem(R.drawable.placeholder, "Title", "Author", "Description", (new Date()).getTime()));
    }

    private void showList(boolean reset_variables)
    {
        setRefreshing(true);

        if (reset_variables) {
            isConnected = Utils.isConnected(getActivity());
            mAdapter.mDataset = new ArrayList<PostItem>();
            feedEnds = false;
            feedPage = 1;
        }

        if (!isConnected) {
            Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
        }

        if (feedEnds) {
            setRefreshing(false);
            Toast.makeText(getActivity(), getString(R.string.empty_result), Toast.LENGTH_SHORT).show();

            return;
        }

        setRefreshing(false);
    }
}
