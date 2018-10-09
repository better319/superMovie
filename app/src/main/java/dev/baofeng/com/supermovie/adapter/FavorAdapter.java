package dev.baofeng.com.supermovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.huangyong.playerlib.Params;
import com.huangyong.playerlib.PlayerActivity;

import dev.baofeng.com.supermovie.R;
import dev.baofeng.com.supermovie.domain.MovieInfo;
import dev.baofeng.com.supermovie.holder.FavorHolder;
import dev.baofeng.com.supermovie.holder.HistoryHolder;
import dev.baofeng.com.supermovie.utils.DateTools;
import dev.baofeng.com.supermovie.view.GlobalMsg;
import dev.baofeng.com.supermovie.view.MovieDetailActivity;

/**
 * 观看历史，点击是继续观看，不用进详情页
 * Created by huangyong on 2018/1/26.
 */

public class FavorAdapter extends RecyclerView.Adapter {
    private Context context;
    private MovieInfo info;

    public FavorAdapter(Context context, MovieInfo info) {
        this.context = context;
        this.info = info;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favor,parent,false);
        return new FavorHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String URLImg= info.getData().get(position).getDownimgurl();
        String name = info.getData().get(position).getDownLoadName();

        Glide.with(context).load(URLImg).into(((FavorHolder)holder).itemimg);
        ((FavorHolder)holder).itemtitle.setText(name);
        String progress = info.getData().get(position).getProgress();
        ((FavorHolder) holder).root.setOnClickListener(view -> {
            try {
//                        Intent intent = new Intent(context, DetailActivity.class);
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(GlobalMsg.KEY_POST_IMG, URLImg);
                intent.putExtra(GlobalMsg.KEY_DOWN_URL,info.getData().get(position).getDownLoadUrl());
                intent.putExtra(GlobalMsg.KEY_MOVIE_TITLE, name);
                intent.putExtra(GlobalMsg.KEY_MOVIE_DOWN_ITEM_TITLE, info.getData().get(position).getDowndtitle());
                intent.putExtra(GlobalMsg.KEY_MOVIE_DETAIL,info.getData().get(position).getMvdesc());
                context.startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }

           try {





               Intent intent = new Intent(context, PlayerActivity.class);
               intent.putExtra(GlobalMsg.KEY_POST_IMG, URLImg);
               intent.putExtra(GlobalMsg.KEY_MOVIE_TITLE, name);
               intent.putExtra(Params.MOVIE_PROGRESS, progress);
               intent.putExtra(Params.PROXY_PALY_URL,info.getData().get(position).getDownLoadUrl());
               intent.putExtra(Params.URL_MD5_KEY,info.getData().get(position).getUrlMd5());
               context.startActivity(intent);

           }catch (Exception e){
               e.printStackTrace();
           }
        });
    }

    @Override
    public int getItemCount() {
        return info.getData().size();
    }


    public void clear() {
        if (info.getData().size()>0)
        info.getData().clear();
    }
}
