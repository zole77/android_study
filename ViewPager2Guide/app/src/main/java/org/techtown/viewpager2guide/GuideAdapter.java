package org.techtown.viewpager2guide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.GuideViewHolder> {

    Context mContext;
    List<Guide> guideList;

    public GuideAdapter(Context mContext, List<Guide> guideList){
        this.mContext = mContext;
        this.guideList = guideList;
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.guide_image, parent, false);

        return new GuideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder holder, int position) {
        Guide guide = guideList.get(position);
        holder.imageView.setImageResource(guide.getImage());
    }

    @Override
    public int getItemCount() {
        return guideList.size();
    }


    public class GuideViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public GuideViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.guideimage);
        }
    }
}
