package com.nns.saltedfishdaily.view;

public interface SplashContract {
    interface View {
        void showImage(String url);

        void showAuthor(String author);

        void goToMain();
    }

    interface Presenter {
        void getStartImage();
    }
}
