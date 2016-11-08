package com.android.mobile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.mobile.access.Access;
import com.android.mobile.access.api.ArticleSearchApi;
import com.android.mobile.access.models.Articles;
import com.android.mobile.event.ArticleSelectedEvent;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<Articles> {

    public static final String apiKey = "a8457610b68381085a3fff38d6a36337:6:74255139";

    private FrameLayout articleContainer;
    private EditText searchView;
    private Button serachButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.main_activity);

        articleContainer = (FrameLayout) findViewById(R.id.article_container);
        searchView = (EditText) findViewById(R.id.search_view);
        serachButton = (Button) findViewById(R.id.search_view_button);

        serachButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_view_button) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            String searchTerm = String.valueOf(searchView.getText());
            Access access = Access.getInstance();
            ArticleSearchApi articleSearchApi = access.getArticleSearchApi();
            articleSearchApi.getArticles(searchTerm, apiKey, this);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void success(Articles articles, Response response) {

        ArticleFragment articleFragment = new ArticleFragment();
        articleFragment.setArticles(articles);
        getFragmentManager().beginTransaction()
                .replace(R.id.article_container, articleFragment, ArticleFragment.TAG)
                .commit();
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void onEvent(ArticleSelectedEvent event) {
        ArticleDescriptionFragment articleDescriptionFragment = new ArticleDescriptionFragment();
        articleDescriptionFragment.setDetails(event.docs, event.selectedPosition);
        getFragmentManager().beginTransaction()
                .replace(R.id.article_container, articleDescriptionFragment, ArticleDescriptionFragment.TAG)
                .commit();
    }
}
