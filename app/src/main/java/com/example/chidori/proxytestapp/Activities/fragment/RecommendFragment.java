package com.example.chidori.proxytestapp.Activities.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.chidori.proxytestapp.Activities.ReaderActivity;
import com.example.chidori.proxytestapp.Activities.adapter.RecommendAdapter;
import com.example.chidori.proxytestapp.Activities.entity.Recommend;
import com.example.chidori.proxytestapp.Activities.fragment.banner.LocalImageHolderView;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;

public class RecommendFragment extends Fragment implements OnItemClickListener {

    static String[] urls = {"https://mp.weixin.qq.com/s?__biz=MjM5MDQ4MzU5NQ%3D%3D&chksm=bdcc69478abbe05184b7f61a813aec3b1cb123c1fe9d039be2f3ac23efa86a53738cfbe28d05&idx=5&mid=2658983075&scene=0&sn=adfe06f3f2b87d567af6fb0fec8fdabf&xtrack=1#rd",
            "https://mp.weixin.qq.com/s?__biz=MzIxMjAwNTY1Mg%3D%3D&chksm=8c3e4db7bb49c4a15a8fd1d60fae2d39ba6c78c3ff20f9b772ad5d402bd863591c3063fc30cd&idx=1&mid=2659601356&scene=0&sn=84426dfee58a39d778e5b1a4ad75a564&xtrack=1#rd",
            "https://mp.weixin.qq.com/s?__biz=MzA3MzI4MjgzMw%3D%3D&chksm=871a8a26b06d033081260f7e5d31e929a8c29418a998c36a78f4eb5be4f5e1db49dc09930833&idx=4&mid=2650754648&scene=0&sn=28ce6052b48fa9b3a1824d1669473a2c&xtrack=1#rd",
    "https://mp.weixin.qq.com/s/JytTiBTDYNrpMl9HWKAJIg"
    };

    static int[] covers = {R.mipmap.kejimeixue, R.mipmap.photography, R.mipmap.nlp, R.mipmap.wenziren};

    static String[] rec_dates = {"2017-11-10", "2018-12-26", "2018-12-28", "2018-12-28"};
    static String[] rec_urls = {"https://mp.weixin.qq.com/s/0HnowVbQp-mtaIoflVvi0Q",
            "https://mp.weixin.qq.com/s/ftN0JPchfWadWfOKYcbD2A",
            "https://mp.weixin.qq.com/s/DBNjAgDzMFQl_5L5aNp-sg",
            "https://mp.weixin.qq.com/s/wEMp4qPI14MJTmVTbnFONw"
    };
    static String[] titles = {"四大旗舰对决iPhoneX/三星Note8/华为mate10Pro/小米MIX2全陶瓷版（第十季）上篇丨科技美学",
            "2018年度高端CPU大横评：玩游戏最强的还是它",
            "我的if else代码纯净无暇，一个字也不能简化",
            "Facebook新研究：一个编码器hold住93种语言！跨语言迁移无需修改"
    };

    static String[] rec_sources = {"科技美学", "太平洋电脑网", "机器之心", "机器之心"};

    static String[] rec_desc = {"手机评测", "游戏达人", "代码之道", "新兴科技"};
    public RecommendFragment() {
        // Required empty public constructor
    }

    private ArrayList<Integer> localImages;
    private ConvenientBanner<Integer> convenientBanner;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        convenientBanner = view.findViewById(R.id.convenientBanner);
        recyclerView = view.findViewById(R.id.recommend_recyclerview);
        localImages = new ArrayList<>();
        for (int i = 0; i < covers.length; i++)
            localImages.add(covers[i]);

        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                .setOnItemClickListener(this);
        //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件

        convenientBanner.setScrollDuration(1200);
        convenientBanner.startTurning(2000);

        ArrayList<Recommend> recommends = new ArrayList<>();
        for (int i=0;i<rec_urls.length;i++) {
            recommends.add(new Recommend(titles[i], rec_urls[i], rec_dates[i], rec_sources[i], rec_desc[i]));
        }
        RecommendAdapter adapter = new RecommendAdapter(recommends);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public void onItemClick(int position) {
        String url = urls[position];
        Intent intent = new Intent(getActivity(), ReaderActivity.class);
        intent.putExtra("link", url);
        startActivity(intent);
    }
}
