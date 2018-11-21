package app.piruma_java.base;

public interface Presenter<V extends BaseView>  {
    void attachView(V view);

    void detachView();
}
