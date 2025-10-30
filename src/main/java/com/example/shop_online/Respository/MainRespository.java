package com.example.shop_online.Respository;

import androidx.annotation.*;
import androidx.lifecycle.*;

import com.example.shop_online.Domain.*;
import com.google.firebase.database.*;

import java.util.*;

// 🔹 Lớp này đóng vai trò Repository trong mô hình MVVM
// Chịu trách nhiệm truy cập dữ liệu từ Firebase và cung cấp cho ViewModel
public class MainRespository {
    // 🔸 Tạo một instance của FirebaseDatabase để truy cập dữ liệu online
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    // 🔸 Phương thức loadCategory() dùng để lấy danh sách Category từ Firebase
    //    và trả về dưới dạng LiveData (để UI có thể "quan sát" và tự cập nhật)
    public LiveData<ArrayList<CategoryModel>> loadCategory(){
        // MutableLiveData cho phép ta cập nhật dữ liệu (setValue)
        MutableLiveData<ArrayList<CategoryModel>> listData = new MutableLiveData<>();

        // 🔹 Tham chiếu đến nhánh "Category" trong Firebase Realtime Database
        DatabaseReference ref = firebaseDatabase.getReference("Category");

        // 🔹 Lắng nghe dữ liệu thay đổi tại node "Category"
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Tạo danh sách tạm để chứa dữ liệu Category lấy về
                ArrayList<CategoryModel> list = new ArrayList<>();

                // Duyệt qua từng "con" của node "Category"
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    // Parse dữ liệu từng node thành đối tượng CategoryModel
                    CategoryModel item = childSnapshot.getValue(CategoryModel.class);
                    // Nếu không null thì thêm vào danh sách
                    if(item != null) list.add(item);
                }
                // Cập nhật dữ liệu vào LiveData → tự động thông báo đến ViewModel/UI
                listData.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Trường hợp đọc dữ liệu bị lỗi (ví dụ: quyền truy cập, mạng yếu,...)
                // Có thể log lỗi hoặc xử lý thông báo cho người dùng ở đây
            }
        });
        // 🔹 Trả về đối tượng LiveData để ViewModel có thể quan sát dữ liệu này
        return listData;
    }
}
