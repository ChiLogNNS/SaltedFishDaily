package com.nns.saltedfishdaily.view;

import com.nns.saltedfishdaily.bean.News;

import java.util.List;

public interface NewsContract {
    interface View{
        void setNewsList(List<News.StoriesBean> data);

        void setHeadView(List<News.TopStoriesBean> top_stories);
    }

    interface Presenter{
        void getData();
        void getMoreData();
    }
}
