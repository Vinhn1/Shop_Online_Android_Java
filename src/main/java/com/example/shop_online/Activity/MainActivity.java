package com.example.shop_online.Activity;

import static com.example.shop_online.R.*;

import android.os.Bundle;
import android.view.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.*;
import androidx.recyclerview.widget.*;
import androidx.viewpager2.widget.*;

import com.example.shop_online.*;
import com.example.shop_online.Adapter.*;
import com.example.shop_online.Domain.*;
import com.example.shop_online.R;
import com.example.shop_online.ViewModel.*;
import com.example.shop_online.databinding.*;
import com.ismaeldivita.chipnavigation.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    // Dùng View Binding để truy cập các View trong layout activity_main.xml
    private ActivityMainBinding binding;

    // ViewModel để quản lý dữ liệu (Category, Product, ...)
    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Bật chế độ Edge-to-Edge (toàn màn hình, không có thanh trắng phía dưới)
        EdgeToEdge.enable(this);
        // Khởi tạo ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         viewModel = new MainViewModel();

        // Gọi hàm khởi tạo danh mục
        initCategory();
        // Gọi hàm khởi tạo Slider
        initSlider();
        initPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        binding.bottomNavigation.setItemSelected(R.id.home, true);
        binding.bottomNavigation.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

            }
        });
    }

    private void initPopular() {
        binding.progressBarPopular.setVisibility(View.VISIBLE);
        viewModel.loadPopular().observeForever(itemsModels -> {
            if(!itemsModels.isEmpty()){
                binding.popularView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                binding.popularView.setAdapter(new PopularAdapter(itemsModels));
                binding.popularView.setNestedScrollingEnabled(true);
            }
            binding.progressBarPopular.setVisibility(View.GONE);
        });
        viewModel.loadPopular();
    }

    // Hàm khởi tạo slider banner
    private void initSlider() {
        // Hiển thị thanh ProgressBar (vòng xoay) trong lúc đang tải dữ liệu banner
        binding.progressBarSlider.setVisibility(View.VISIBLE);
        // Gọi hàm loadBanner() trong ViewModel và quan sát dữ liệu trả về (LiveData)
        viewModel.loadBanner().observeForever(bannerModels -> {
            // Khi dữ liệu banner đã được tải và không rỗng
            if(bannerModels != null && !bannerModels.isEmpty()){
                // Gọi hàm banners() để hiển thị danh sách banner lên ViewPager2
                banners(bannerModels);
                // Ẩn ProgressBar khi dữ liệu đã tải xong
                binding.progressBarSlider.setVisibility(View.GONE);
            }
        });
        // Gọi lại hàm loadBanner() để thực thi việc tải dữ liệu (nếu cần)
        viewModel.loadBanner();
    }

    // Hàm cấu hình và hiển thị banner trên ViewPager2
    private void banners(ArrayList<BannerModel> bannerModels) {
        // Gán adapter cho ViewPager2 để hiển thị danh sách banner
        binding.viewPagerSlider.setAdapter(new SliderAdapter(bannerModels, binding.viewPagerSlider));
        // Cho phép banner tràn ra ngoài phần khung ViewPager2 để tạo hiệu ứng nhìn liền mạch
        binding.viewPagerSlider.setClipToPadding(false);
        binding.viewPagerSlider.setClipChildren(false);
        // Giữ sẵn 3 trang bên cạnh trong bộ nhớ để lướt mượt hơn
        binding.viewPagerSlider.setOffscreenPageLimit(3);
        // Tắt hiệu ứng cuộn vượt giới hạn (giúp trải nghiệm lướt mượt mà hơn)
        binding.viewPagerSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        // Tạo đối tượng PageTransformer để thêm hiệu ứng khi chuyển trang
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        // Thêm khoảng cách giữa các banner
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));

        // Gán transformer này cho ViewPager2 để hiển thị hiệu ứng
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer);
    }

    // Hàm này khởi tạo RecyclerView để hiển thị danh sách Category
    private void initCategory() {
        // Hiển thị ProgressBar khi đang tải dữ liệu từ Firebase
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        // Gọi ViewModel để load danh sách Category (trả về LiveData)
        viewModel.loadCategory().observeForever(categoryModels -> {

            // Thiết lập LayoutManager cho RecyclerView
            // Ở đây ta dùng LinearLayoutManager theo chiều ngang (HORIZONTAL)
            binding.categoryView.setLayoutManager(new LinearLayoutManager(
                    MainActivity.this, LinearLayoutManager.HORIZONTAL, false
            ));

            // Gắn Adapter cho RecyclerView — Adapter sẽ hiển thị danh sách Category
            binding.categoryView.setAdapter(new CateroryAdapter(categoryModels));
            // Cho phép cuộn mượt mà khi RecyclerView nằm trong ScrollView hoặc Nested Layout
            binding.categoryView.setNestedScrollingEnabled(true);

            // Ẩn ProgressBar sau khi dữ liệu đã tải xong
            binding.progressBarCategory.setVisibility(View.GONE);
        });
    }
}