package com.android.hq.ganktoutiao.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hq.ganktoutiao.R;
import com.android.hq.ganktoutiao.ui.adapter.PagerAdapter;
import com.android.hq.ganktoutiao.ui.fragment.AboutFragment;
import com.android.hq.ganktoutiao.ui.fragment.MainFragment;
import com.android.hq.ganktoutiao.ui.fragment.PresentFragment;
import com.android.hq.ganktoutiao.ui.fragment.SearchFragment;
import com.android.hq.ganktoutiao.ui.view.SizeObserverLinearLayout;
import com.android.hq.ganktoutiao.ui.view.ViewPagerEx;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends Activity{

    private SizeObserverLinearLayout mRootView;
    private ViewPagerEx mViewPager;
    private PagerAdapter mPagerAdapter;
    private ViewGroup mToolBar;
    private Unbinder mUnbinder;

    @BindView(R.id.home_img)
    ImageView mHomeImg;
    @BindView(R.id.search_img)
    ImageView mSearchImg;
    @BindView(R.id.present_img)
    ImageView mPresentImg;
    @BindView(R.id.about_img)
    ImageView mAboutImg;

    @BindView(R.id.home_tv)
    TextView mHomeTv;
    @BindView(R.id.search_tv)
    TextView mSearchTv;
    @BindView(R.id.present_tv)
    TextView mPresentTv;
    @BindView(R.id.about_tv)
    TextView mAboutTv;

    @BindColor(R.color.main_page_toolbar_select_text_color)
    int mToolBarSelectColor;
    @BindColor(R.color.main_page_toolbar_unselect_text_color)
    int mToolBarUnSelectColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        mRootView = (SizeObserverLinearLayout) findViewById(R.id.root_view);
        mViewPager = (ViewPagerEx) findViewById(R.id.view_pager_main);
        mToolBar = (ViewGroup) findViewById(R.id.tool_bar);
        mPagerAdapter = new PagerAdapter(this);

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCanScroll(false);
        mViewPager.setOffscreenPageLimit(3);

        mPagerAdapter.addPage(MainFragment.class, null);
        mPagerAdapter.addPage(SearchFragment.class, null);
        mPagerAdapter.addPage(PresentFragment.class, null);
        mPagerAdapter.addPage(AboutFragment.class, null);

        resetButton();

        mViewPager.setCurrentItem(0);
        mHomeImg.setImageResource(R.drawable.tab_home);
        mHomeTv.setTextColor(mToolBarSelectColor);

        mRootView.setOnSizeChangeListener(new SizeObserverLinearLayout.OnSizeWillChangeListener() {
            @Override
            public void onSizeWillChanged(int w, int h) {
                int heightDiff = mRootView.getRootView().getMeasuredHeight() - h;
                if (heightDiff > 100) {
                    mToolBar.setVisibility(View.GONE);
                } else {
                    mToolBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.page1,R.id.search,R.id.present,R.id.about})
    public void onClick(View view) {
        resetButton();
        switch (view.getId()){
            case R.id.page1:
                mViewPager.setCurrentItem(0);
                mHomeImg.setImageResource(R.drawable.tab_home);
                mHomeTv.setTextColor(mToolBarSelectColor);
                break;
            case R.id.search:
                mViewPager.setCurrentItem(1);
                mSearchImg.setImageResource(R.drawable.tab_explore);
                mSearchTv.setTextColor(mToolBarSelectColor);
                break;
            case R.id.present:
                mViewPager.setCurrentItem(2);
                mPresentImg.setImageResource(R.drawable.tab_recommend);
                mPresentTv.setTextColor(mToolBarSelectColor);
                break;
            case R.id.about:
                mViewPager.setCurrentItem(3);
                mAboutImg.setImageResource(R.drawable.tab_profile);
                mAboutTv.setTextColor(mToolBarSelectColor);
                break;
        }
    }

    private void resetButton(){
        mHomeImg.setImageResource(R.drawable.tab_home_normal);
        mSearchImg.setImageResource(R.drawable.tab_explore_normal);
        mPresentImg.setImageResource(R.drawable.tab_recommend_normal);
        mAboutImg.setImageResource(R.drawable.tab_profile_normal);

        mHomeTv.setTextColor(mToolBarUnSelectColor);
        mSearchTv.setTextColor(mToolBarUnSelectColor);
        mPresentTv.setTextColor(mToolBarUnSelectColor);
        mAboutTv.setTextColor(mToolBarUnSelectColor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
