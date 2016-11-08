package myapps.mobile.times_now;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myapps.mobile.times_now.access.models.Articles;
import myapps.mobile.times_now.presentation.HorizontalDivider;

public class ArticleFragment extends Fragment{
    public static final String TAG = ArticleFragment.class.getSimpleName();

    private RecyclerView articleList;
    private LinearLayoutManager linearLayoutManager;
    private Articles articles;
    private ArticleListAdapter articleListAdapter;

    public void setArticles(Articles articles) {
        this.articles = articles;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment_layout, container, false);

        articleList = (RecyclerView) view.findViewById(R.id.article_list);
        HorizontalDivider divider = new HorizontalDivider(getActivity());
        divider.setColor(getResources().getColor(R.color.gray_300));
        articleList.addItemDecoration(divider);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        articleList.setLayoutManager(linearLayoutManager);

        articleListAdapter = new ArticleListAdapter(articles, getActivity());
        articleList.setAdapter(articleListAdapter);
        return view;
    }

}
