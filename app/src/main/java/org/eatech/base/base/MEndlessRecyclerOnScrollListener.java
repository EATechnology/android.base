/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/11/15 3:14 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.base;

import android.support.v7.widget.RecyclerView;

public abstract class MEndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener
{
    public static String TAG = "EndlessScrollListener";

    private int     previousTotal    = 0; // The total number of items in the dataset after the last load
    private boolean loading          = true; // True if we are still waiting for the last set of data to load.
    private int     visibleThreshold = 2; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;

    MRecyclerViewPositionHelper mRecyclerViewHelper;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
    {
        super.onScrolled(recyclerView, dx, dy);
        mRecyclerViewHelper = MRecyclerViewPositionHelper.createHelper(recyclerView);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mRecyclerViewHelper.getItemCount();
        firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            // Do something
            currentPage++;

            onLoadMore(currentPage);

            loading = true;
        }
    }

    //Start loading
    public abstract void onLoadMore(int currentPage);
}