package com.nns.saltedfishdaily.model;

public class BaseModel<T> {
    protected OnRequestListener mListener;

    public void setListener(OnRequestListener listener) {
        mListener = listener;
    }

    protected void onRequestSucceedCallback(T t) {
        if (mListener != null) {
            mListener.onSucceed(t);
        }
    }

    protected void onRequestFailedCallback(String msg) {
        if (mListener != null) {
            mListener.onFailed(msg);
        }
    }


    public interface OnRequestListener<T> {
        void onSucceed(T bean);

        void onFailed(String msg);
    }

}
