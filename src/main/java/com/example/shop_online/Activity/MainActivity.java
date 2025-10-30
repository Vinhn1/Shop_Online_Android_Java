package com.example.shop_online.Activity;

import android.os.Bundle;
import android.view.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.*;
import androidx.recyclerview.widget.*;

import com.example.shop_online.*;
import com.example.shop_online.Adapter.*;
import com.example.shop_online.ViewModel.*;
import com.example.shop_online.databinding.*;

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