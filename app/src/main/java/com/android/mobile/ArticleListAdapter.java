package com.android.mobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.mobile.access.models.Articles;
import com.android.mobile.access.models.Doc;
import com.android.mobile.access.models.Response;

import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private static Articles articles;
    private static Context context;
    private Response response;
    private static List<Doc> docs;
    private static Doc doc;

    public ArticleListAdapter(Articles articles, Context context) {
        this.context = context;
        this.articles = articles;
        if (articles.getResponse() != null) {
            response = articles.getResponse();
            if ( response.getDocs() != null) {
                docs = response.getDocs();
            }
        }
    }

    @Override
    public ArticleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView articleHeading;

        public ViewHolder(View itemView) {
            super(itemView);

            articleHeading = (TextView) itemView.findViewById(R.id.article_heading);

            articleHeading.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            WebView webView = new WebView(context);
            if (docs != null && docs.size() > 0) {
                doc = docs.get(getAdapterPosition());
                if (doc.getWebUrl() != null) {
                    webView.loadUrl(doc.getWebUrl());
                }
            }
//            int selectedPositon = getAdapterPosition();
//            EventBus.getDefault().post(new ArticleSelectedEvent(docs, selectedPositon));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String heading = docs.get(position).getHeadline().getMain();

        holder.articleHeading.setText(heading);
    }

    @Override
    public int getItemCount() {
        return docs.size();
    }
}
