package com.example.pagingapp.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpace extends RecyclerView.ItemDecoration{
    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpace(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); //item position
        int col = position % spanCount; //item column

        if(includeEdge){
            outRect.left = spacing - col * spacing / spanCount;
            outRect.right = (col + 1) * spacing / spanCount;

            if(position < spanCount){  // top edge part
                outRect.top = spacing;
            }
            outRect.bottom = spacing; //item bottom

        }else{
            outRect.left = col * spacing / spanCount;
            outRect.right = spacing - (col + 1) * spacing / spanCount;
            if(position >= spanCount){
                outRect.top = spacing;
            }
        }
    }
}
