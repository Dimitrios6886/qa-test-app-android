package com.anydog.qaedu.di.component;

import com.anydog.qaedu.QaEduApplication;
import com.anydog.qaedu.di.builder.ActivityBuilder;
import com.anydog.qaedu.di.module.AppModule;
import com.anydog.qaedu.ui.about.AboutFragment;
import com.anydog.qaedu.ui.feed.bucket.BucketFragment;
import com.anydog.qaedu.ui.feed.products.ProductsFragment;
import com.anydog.qaedu.ui.product.comments.CommentsFragment;
import com.anydog.qaedu.ui.profile.ProfileFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(QaEduApplication app);

    void inject(AboutFragment fragment);

    void inject(ProductsFragment fragment);

    void inject(BucketFragment fragment);

    void inject(CommentsFragment fragment);

    void inject(ProfileFragment fragment);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(android.app.Application application);

        AppComponent build();
    }
}
