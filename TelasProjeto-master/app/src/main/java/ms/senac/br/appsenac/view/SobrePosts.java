package ms.senac.br.appsenac.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.adapter.AdapterSobrePosts;

public class SobrePosts extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_posts);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPagerSobrePosts);
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutSobrePosts);

        viewPager.setAdapter(new AdapterSobrePosts(this, getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(this,R.color.colorBranco);
        tabLayout.setTabTextColors(cor,cor);

    }


}
