package com.example.shop_online.Activity;

import android.content.*;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.*;

import com.example.shop_online.databinding.*;

public class SplashActivity extends AppCompatActivity {

    // Import lớp binding được Android Studio tự động sinh ra từ file layout: activity_splash.xml
    // Nó giúp truy cập trực tiếp các view trong layout mà không cần findViewById()
    private ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ép ứng dụng luôn ở chế độ sáng
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Bật chế độ hiển thị toàn màn hình theo thiết kế mới (Edge-to-Edge layout)
        // Tức là app có thể hiển thị nội dung “tràn” lên vùng status bar, navigation bar
        EdgeToEdge.enable(this);
        // Khởi tạo biến binding bằng cách “inflate” (nạp giao diện) từ file XML activity_splash.xml
        binding = ActivitySplashBinding.inflate(getLayoutInflater());

        // Gắn (hiển thị) giao diện đã nạp ở trên vào màn hình Activity
        setContentView(binding.getRoot());

        // Gán sự kiện click cho nút có id là startBtn (được ánh xạ tự động trong layout)
        // Khi người dùng bấm nút “Start”, sẽ mở sang màn hình MainActivity
        binding.startBtn.setOnClickListener(v ->
                startActivity(new Intent(SplashActivity.this, MainActivity.class)));
    }
}