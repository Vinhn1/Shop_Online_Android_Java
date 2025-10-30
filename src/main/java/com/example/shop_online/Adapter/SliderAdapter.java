package com.example.shop_online.Adapter;

import android.content.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.recyclerview.widget.*;
import androidx.viewpager2.widget.*;

import com.bumptech.glide.*;
import com.example.shop_online.*;
import com.example.shop_online.Domain.*;
import com.example.shop_online.R;

import java.util.*;

/**
 * Adapter dùng cho ViewPager2 để hiển thị danh sách các banner quảng cáo (ảnh trượt)
 */
public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    // Danh sách các banner (mỗi banner chứa URL ảnh)
    private ArrayList<BannerModel> sliderItems;
    // Dùng để điều khiển ViewPager2 (để tạo hiệu ứng trượt liên tục)
    private ViewPager2 viewPager2;
    // Context để dùng cho Glide và inflate layout
    private Context context;
    /**
     * Runnable này được dùng để thêm lại toàn bộ danh sách ảnh
     * => tạo hiệu ứng "vòng lặp vô hạn" khi trượt đến gần cuối
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Thêm lại danh sách ảnh vào cuối list
            sliderItems.addAll(sliderItems);
            // Cập nhật RecyclerView để hiển thị thêm ảnh
            notifyDataSetChanged();
        }
    };

    /**
     * Constructor nhận danh sách banner và ViewPager2 để adapter có thể tương tác
     */
    public SliderAdapter(ArrayList<BannerModel> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    /**
     * Tạo ra ViewHolder (đại diện cho từng item/slide)
     */
    @NonNull
    @Override
    public SliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Lấy context từ View cha
        context = parent.getContext();
        // Gắn layout "slider_item.xml" cho từng item
        return new SliderViewHolder(LayoutInflater.from(context).inflate(R.layout.slider_item, parent, false));
    }

    /**
     * Gắn dữ liệu (ảnh banner) vào ViewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {
        // Hiển thị ảnh tương ứng với vị trí
        holder.setImage(sliderItems.get(position));
        // Nếu đang ở gần cuối danh sách (vị trí = size - 2)
        // thì post Runnable để thêm dữ liệu -> tạo hiệu ứng trượt vô hạn
        if(position == sliderItems.size() - 2){
            viewPager2.post(runnable);
        }
    }

    /**
     * Trả về số lượng phần tử hiện tại trong danh sách
     */
    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    /**
     * ViewHolder chứa ImageView hiển thị ảnh banner
     */
    public class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ tới ImageView trong layout slider_item.xml
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        /**
         * Hàm load ảnh từ URL bằng thư viện Glide và hiển thị lên ImageView
         */
        void setImage(BannerModel bannerModel){
            Glide.with(context)
                    .load(bannerModel.getUrl())
                    .into(imageView);
        }
    }
}
