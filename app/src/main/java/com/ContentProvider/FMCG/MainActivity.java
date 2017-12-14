package com.ContentProvider.FMCG;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.contentprovider.FMCG.R;

public class MainActivity extends AppCompatActivity {
    /**
     * This is our example content observer.
     */
    private ArticleObserver articleObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create your sync account
        AccountGeneral.createSyncAccount(this);

        // Perform a manual sync by calling this:
        SyncAdapter.performSync();


        // Setup example content observer
        articleObserver = new ArticleObserver();

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Register the observer at the start of our activity
       /* getContentResolver().registerContentObserver(
                ArticleContract.Articles.CONTENT_URI, // Uri to observe (our articles)
                true, // Observe its descendants
                articleObserver); // The observer*/
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (articleObserver != null) {
            // Unregister the observer at the stop of our activity
            getContentResolver().unregisterContentObserver(articleObserver);
        }
    }

    private void refreshArticles() {
        Log.i(getClass().getName(), "Articles data has changed!");
    }


    /**
     * Example content observer for observing article data changes.
     */
    private final class ArticleObserver extends ContentObserver {
        private ArticleObserver() {
            // Ensure callbacks happen on the UI thread
            super(new Handler(Looper.getMainLooper()));
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            // Handle your data changes here!!!
            refreshArticles();
        }
    }
}
