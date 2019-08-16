package info.movies.rxjavasearch.view;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.movies.rxjavasearch.R;
import info.movies.rxjavasearch.adapter.MoviesAdapterFilterable;
import info.movies.rxjavasearch.network.ApiClient;
import info.movies.rxjavasearch.network.ApiService;
import info.movies.rxjavasearch.network.model.Movie;
import info.movies.rxjavasearch.network.model.MovieWrapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LocalSearchActivity extends AppCompatActivity implements MoviesAdapterFilterable.MoviesAdapterListener {

    private static final String TAG = LocalSearchActivity.class.getSimpleName();
    @BindView(R.id.input_search)
    EditText inputSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiService apiService;
    private MoviesAdapterFilterable mAdapter;
    private List<Movie> moviesList = new ArrayList<>();
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_search);
        unbinder = ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdapter = new MoviesAdapterFilterable(this, moviesList, this);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);


        whiteNotificationBar(recyclerView);

        apiService = ApiClient.getClient().create(ApiService.class);

        disposable.add(RxTextView.textChangeEvents(inputSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchMovies()));
        fetchMovies();
    }

    private DisposableObserver<TextViewTextChangeEvent> searchMovies() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text());
                mAdapter.getFilter().filter(textViewTextChangeEvent.text());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }


    // fetch Movies
    private void fetchMovies() {

        disposable.add(
                apiService
                        .getMovie()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MovieWrapper>() {


                    @Override
                    public void onSuccess(MovieWrapper movieWrapper) {
                        Log.e("onSuccess", movieWrapper.toString());
                        moviesList.clear();
                        moviesList.addAll(movieWrapper.getMovie());
                        mAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.getMessage());
                    }
                }));

    }

    @Override
    protected void onDestroy() {
        disposable.clear();
        unbinder.unbind();
        super.onDestroy();
    }


    @Override
    public void onMovieSelected(Movie movie) {
        // callback here
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
