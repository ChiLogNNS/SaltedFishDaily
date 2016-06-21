package com.nns.saltedfishdaily.view;

import com.nns.saltedfishdaily.bean.NewsDetail;

public interface NewsDetailContract {
    interface View {
        void setData(NewsDetail data);
    }

    interface Presenter {
        void getData(String id);
    }
}
