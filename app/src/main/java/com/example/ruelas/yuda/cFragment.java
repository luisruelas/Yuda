package com.example.ruelas.yuda;

import android.view.View;

/**
 * Created by Ruelas on 06/02/2017.
 */
public class cFragment extends android.support.v4.app.Fragment {
    private String title;
    private View content;
    public String getTitle() {
        if (title==null)
            return "no title";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public View getContent() {
        return content;
    }

    public void setContent(View content) {
        this.content = content;
    }
}
