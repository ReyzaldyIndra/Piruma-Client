package app.piruma_java.base;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

@SuppressLint("Registered")
public abstract class BaseActivity <T extends BasePresenter> extends AppCompatActivity {

    public T presenter;
    public View view;
    public Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupLayout();
        view = getContextView();
        onViewReady();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    protected void setupToolbar(String title) {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    protected void setupToolbar(String title, @DrawableRes int icon) {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void setupToolbar(String title, boolean isBackEnabled) {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(isBackEnabled);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isBackEnabled);
    }

    protected abstract void setupLayout();

    protected abstract void onViewReady();

    protected abstract View getContextView();
}
