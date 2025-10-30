package com.example.shop_online.ViewModel;

import androidx.lifecycle.*;

import com.example.shop_online.Domain.*;
import com.example.shop_online.Respository.*;

import java.util.*;

// Lớp MainViewModel kế thừa từ ViewModel
// => Dùng để quản lý dữ liệu hiển thị trong MainActivity mà không phụ thuộc vào vòng đời của Activity
public class MainViewModel   extends ViewModel {
    // Tạo đối tượng Repository - nơi lấy dữ liệu thật
    // final nghĩa là biến này chỉ được gán một lần duy nhất
    private final MainRespository respository = new MainRespository();

    // Hàm loadCategory() dùng để lấy danh sách danh mục (Category)
    // Trả về LiveData - để Activity/Fragment có thể "quan sát" dữ liệu này
    // Khi dữ liệu thay đổi (Firebase trả về mới), giao diện sẽ tự động cập nhật
    public LiveData<ArrayList<CategoryModel>> loadCategory(){
        // Gọi phương thức loadCategory() trong Repository và trả kết quả ra ngoài
        return respository.loadCategory();
    }

    public LiveData<ArrayList<BannerModel>> loadBanner(){
        return  respository.loadBanner();
    }
}
