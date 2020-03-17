// Generated code from Butter Knife. Do not modify!
package com.example.rayaan.moviefragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailFragment$$ViewBinder<T extends com.example.rayaan.moviefragment.DetailFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230813, "field 'poster'");
    target.poster = finder.castView(view, 2131230813, "field 'poster'");
    view = finder.findRequiredView(source, 2131230808, "field 'title'");
    target.title = finder.castView(view, 2131230808, "field 'title'");
    view = finder.findRequiredView(source, 2131230759, "field 'date'");
    target.date = finder.castView(view, 2131230759, "field 'date'");
    view = finder.findRequiredView(source, 2131230876, "field 'vote_average'");
    target.vote_average = finder.castView(view, 2131230876, "field 'vote_average'");
    view = finder.findRequiredView(source, 2131230809, "field 'overoview'");
    target.overoview = finder.castView(view, 2131230809, "field 'overoview'");
    view = finder.findRequiredView(source, 2131230861, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131230861, "field 'recyclerView'");
    view = finder.findRequiredView(source, 2131230777, "field 'fav'");
    target.fav = finder.castView(view, 2131230777, "field 'fav'");
  }

  @Override public void unbind(T target) {
    target.poster = null;
    target.title = null;
    target.date = null;
    target.vote_average = null;
    target.overoview = null;
    target.recyclerView = null;
    target.fav = null;
  }
}
