package com.android.mobile;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.mobile.access.models.Doc;

import java.util.List;

public class ArticleDescriptionFragment extends Fragment {
    public static final String TAG = ArticleDescriptionFragment.class.getSimpleName();

    private TextView description;
    private WebView webView;

    private int selectedPosition;
    private List<Doc> docs;
    private Doc doc;

    public void setDetails(List<Doc> docs, int selectedPosition) {
        this.docs = docs;
        this.selectedPosition = selectedPosition;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_description_fragment, container, false);

        description = (TextView) view.findViewById(R.id.description);
        webView = new WebView(getActivity());
        webView.getSettings().setJavaScriptEnabled(true);

        if (docs != null && docs.size() > 0) {
            doc = docs.get(selectedPosition);
            if (doc.getWebUrl() != null) {
//                description.setText(doc.getLeadParagraph());
                webView.loadUrl(doc.getWebUrl());
            }
        }

        return view;
    }


}
