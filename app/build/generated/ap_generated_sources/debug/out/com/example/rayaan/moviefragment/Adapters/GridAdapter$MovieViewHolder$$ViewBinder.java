// Generated code from Butter Knife. Do not modify!
package com.example.rayaan.moviefragment.Adapters;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GridAdapter$MovieViewHolder$$ViewBinder<T extends com.example.rayaan.moviefragment.Adapters.GridAdapter.MovieViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230814, "field 'poster_img'");
    target.poster_img = finder.castView(view, 2131230814, "field 'poster_img'");
  }

  @Override public void unbind(T target) {
    target.poster_img = null;
  }
}
