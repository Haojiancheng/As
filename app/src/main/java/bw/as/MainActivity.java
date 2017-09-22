package bw.as;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import bw.as.fragment.SimpleFragment;
import bw.as.fragment.ZhutiribaoFragment;
import bw.as.fragment.ZuixinribaoFragment;

public class MainActivity extends AppCompatActivity {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int mTabCount = 4;
    private  String[] title = {"最新日报","专栏","热门","主题日报"};
    private  Fragment[]  fragments = {new ZuixinribaoFragment(),new SimpleFragment(),new SimpleFragment(),new ZhutiribaoFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.addLogAdapter(new AndroidLogAdapter());

        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return mTabCount;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
