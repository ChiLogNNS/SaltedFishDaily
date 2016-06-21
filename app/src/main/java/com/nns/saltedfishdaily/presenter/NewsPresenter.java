package com.nns.saltedfishdaily.presenter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nns.saltedfishdaily.R;
import com.nns.saltedfishdaily.bean.News;
import com.nns.saltedfishdaily.model.BaseModel;
import com.nns.saltedfishdaily.model.NewsModel;
import com.nns.saltedfishdaily.utils.DateUtil;
import com.nns.saltedfishdaily.view.NewsContract;
import com.nns.saltedfishdaily.view.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class NewsPresenter implements BaseModel.OnRequestListener<News>, NewsContract.Presenter {
    private NewsContract.View mView;
    private final NewsModel mModel;
    private String date;
    private boolean isLoadingData = false;

    public NewsPresenter(NewsContract.View view) {
        mView = view;
        mModel = new NewsModel();
        mModel.setListener(this);
    }

    @Override
    public void getData() {
        if (isLoadingData) {
            return;
        }
        isLoadingData = true;
        mModel.getNewsLatest();
    }

    @Override
    public void getMoreData() {
        if (isLoadingData) {
            return;
        }
        isLoadingData = true;
        date = DateUtil.getPrevDate(date);
        mModel.getMoreData(date);
    }

    @Override
    public void onSucceed(News bean) {
        date = bean.getDate();
        mView.setNewsList(bean.getStories());
        if (bean.getTop_stories() != null) {
            mView.setHeadView(bean.getTop_stories());
        }
        isLoadingData = false;
    }

    @Override
    public void onFailed(String msg) {

    }

    public static class NewsListAdapter extends RecyclerView.Adapter<NewsFragment.ViewHolder> {
        public static final int TYPE_HEADER = 0;
        public static final int TYPE_NORMAL = 1;

        private View headView;
        private OnItemClickListener mListener;
        private List<News.StoriesBean> mData;

        @Override
        public NewsFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (headView != null && viewType == TYPE_HEADER) {
                return new NewsFragment.ViewHolder(headView);
            }
            View view = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.item_news_view, parent, false);
            NewsFragment.ViewHolder viewHolder = new NewsFragment.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(NewsFragment.ViewHolder holder, int position) {
            if (getItemViewType(position) == TYPE_HEADER) {
                return;
            }
            final News.StoriesBean bean = mData.get(getRealPosition(position));
            holder.tvTitle.setText(bean.getTitle());
            Glide.with(holder.ivImage.getContext()).load(bean.getImages().get(0)).into(holder.ivImage);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(0, bean.getId());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            if (headView == null) {
                return mData != null ? mData.size() : 0;
            } else {
                return mData != null ? mData.size() + 1 : 0;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (headView != null && position == 0) {
                return TYPE_HEADER;
            }
            return TYPE_NORMAL;
        }

        public int getRealPosition(int position) {
            if (headView == null) {
                return position;
            } else {
                return position - 1;
            }
        }

        public void setData(List<News.StoriesBean> data) {
            if (mData == null) {
                mData = new ArrayList<>();
            }
            mData.addAll(data);
            notifyDataSetChanged();
        }

        public void setHeadView(View headView) {
            this.headView = headView;
            notifyItemInserted(0);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mListener = listener;
        }

        public interface OnItemClickListener {
            void onItemClick(int position, long newsId);
        }
    }

    public static class BannerAdapter extends PagerAdapter {
        private List<News.TopStoriesBean> mData;
        private SparseArray<ImageView> mImageViewArray;

        @Override
        public int getCount() {
            return mData != null ? mData.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv;
            if (mImageViewArray.get(position) == null) {
                iv = new ImageView(container.getContext());
                iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                mImageViewArray.put(position, iv);
                Glide.with(container.getContext()).load(mData.get(position).getImage()).centerCrop().crossFade().into(iv);
            } else {
                iv = mImageViewArray.get(position);
            }
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView iv = mImageViewArray.get(position);
            if (iv != null) {
                container.removeView(iv);
            }
        }

        public void setData(List<News.TopStoriesBean> data) {
            mData = data;
            mImageViewArray = new SparseArray<>();
            notifyDataSetChanged();
        }
    }
}
