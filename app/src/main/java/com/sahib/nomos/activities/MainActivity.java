package com.sahib.nomos.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.sahib.nomos.R;
import com.sahib.nomos.Rest.CryptoService;
import com.sahib.nomos.adapters.AssetsAdapter;
import com.sahib.nomos.fragments.AssetsFragment;
import com.sahib.nomos.fragments.OneFragment;
import com.sahib.nomos.fragments.ThreeFragment;
import com.sahib.nomos.fragments.TwoFragment;
import com.sahib.nomos.models.AssetsResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://rest.coinapi.io/";
    private static Retrofit retrofit = null;
    private List<AssetsResponse> assets = new ArrayList<>();
    private final static String API_KEY = "7571DD19-2FEF-40B9-B460-C0DE32075E38";

    private RecyclerView recyclerView;
    private AssetsAdapter assetsAdapter = null;
    private Gson gson = null;
    private Toolbar mActionBarToolbar;
    private ActionBar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tab_favourite,
            R.drawable.ic_tab_call,
            R.drawable.ic_tab_contacts
    };

    //Tabs
    public android.app.ActionBar.Tab currencyTab = null;
    public android.app.ActionBar.Tab alertsTab = null;
    public android.app.ActionBar.Tab settingsTab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Nomos");

        //recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //connectAndGetApiData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFrag(new AssetsFragment(), "Assets");
        adapter.addFrag(new OneFragment(), "Assets");
        adapter.addFrag(new TwoFragment(), "Wallet");
        adapter.addFrag(new ThreeFragment(), "Alerts");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(android.support.v4.app.FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
