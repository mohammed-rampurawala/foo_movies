package com.foo.movies.views.splash;


import com.foo.movies.data.Controller;
import com.foo.movies.data.model.ConfigurationResponse;
import com.foo.movies.utils.AppLogger;
import com.foo.movies.views.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 */

public class SplashPresenterImpl<V extends ISplashView> extends BasePresenter<V>
        implements ISplashPresenter<V> {

    @Inject
    public SplashPresenterImpl(Controller controller,
                               CompositeDisposable compositeDisposable) {
        super(controller, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        getCompositeDisposable().
                add(getController()
                        .getApiConfiguration()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ConfigurationResponse>() {
                            @Override
                            public void accept(ConfigurationResponse configurationResponse) throws Exception {
                                AppLogger.e(configurationResponse.toString());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                AppLogger.e(throwable,"Exception While Getting Configuration");
                            }
                        }));
    }
}
