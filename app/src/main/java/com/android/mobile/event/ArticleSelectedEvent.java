package com.android.mobile.event;

import com.android.mobile.access.models.Doc;

import java.util.List;

public class ArticleSelectedEvent {
    public List<Doc> docs;
    public int selectedPosition;

    public ArticleSelectedEvent(List<Doc> docs, int selectedPosition) {
        this.docs = docs;
        this.selectedPosition = selectedPosition;
    }
}
