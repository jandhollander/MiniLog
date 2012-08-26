package it.inspire.minilog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class LogListActivity extends FragmentActivity
        implements LogListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_list);

        if (findViewById(R.id.log_detail_container) != null) {
            mTwoPane = true;
            ((LogListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.log_list))
                    .setActivateOnItemClick(true);
        }
    }

    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(LogDetailFragment.ARG_ITEM_ID, id);
            LogDetailFragment fragment = new LogDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.log_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, LogDetailActivity.class);
            detailIntent.putExtra(LogDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
