package myapps.mobile.times_now.event;

import java.util.List;

import myapps.mobile.times_now.access.models.Doc;

public class ArticleSelectedEvent {
    public List<Doc> docs;
    public int selectedPosition;

    public ArticleSelectedEvent(List<Doc> docs, int selectedPosition) {
        this.docs = docs;
        this.selectedPosition = selectedPosition;
    }
}
