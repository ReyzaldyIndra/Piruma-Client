package app.piruma_java.base;


public class BasePresenter<T extends BaseView> implements Presenter<T>  {
    public T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

}
