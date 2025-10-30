package com.example.shop_online.Adapter;

import android.content.*;
import android.view.*;

import androidx.annotation.*;
import androidx.recyclerview.widget.*;

import com.example.shop_online.*;
import com.example.shop_online.Domain.*;
import com.example.shop_online.databinding.*;

import java.util.*;

// 🔹 Adapter quản lý danh sách Category hiển thị trong RecyclerView
public class CateroryAdapter extends RecyclerView.Adapter<CateroryAdapter.ViewHolder> {
    // Danh sách dữ liệu Category
    private ArrayList<CategoryModel> items;

    // Ngữ cảnh (dùng để lấy resource, inflate layout, ...)
    private Context context;

    // Vị trí item hiện tại được chọn
    private int selectedPosition = -1;

    // Lưu vị trí item được chọn trước đó để update lại giao diện
    private int lastSelectedPosition = -1;

    // 🔹 Constructor nhận danh sách dữ liệu
    public CateroryAdapter(ArrayList<CategoryModel> items) {
        this.items = items;
    }

    // 🔹 Tạo ViewHolder (chỉ gọi khi cần tạo view mới)
    @NonNull
    @Override
    public CateroryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Lưu context
        context = parent.getContext();
        // Dùng ViewBinding để inflate layout item_category.xml (ViewholderCateforyBinding)
        ViewholderCateforyBinding binding = ViewholderCateforyBinding.inflate(LayoutInflater.from(context),parent,false);
        // Trả về ViewHolder chứa layout vừa tạo
        return new ViewHolder(binding);
    }


    // 🔹 Gán dữ liệu cho từng item trong danh sách
    @Override
    public void onBindViewHolder(@NonNull CateroryAdapter.ViewHolder holder, int position) {
        // Gán tên Category cho TextView trong layout
        holder.binding.titleTxt.setText(items.get(position).getTitle());

        // Xử lý sự kiện click vào item
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lưu lại vị trí cũ
                lastSelectedPosition = selectedPosition;
                // Cập nhật vị trí mới
                selectedPosition = position;
                // Thông báo cho RecyclerView biết 2 item thay đổi để cập nhật UI
                notifyItemChanged(lastSelectedPosition);
                notifyItemChanged(selectedPosition);
            }
        });

        // 🔹 Nếu item đang được chọn → đổi màu nền và chữ
        if(selectedPosition == position){
            holder.binding.titleTxt.setBackgroundResource(R.drawable.orange_bg);
            holder.binding.titleTxt.setTextColor(context.getResources().getColor(R.color.white));
        }
        // 🔹 Nếu không được chọn → hiển thị mặc định
        else{
            holder.binding.titleTxt.setBackgroundResource(R.drawable.stroke_bg);
            holder.binding.titleTxt.setTextColor(context.getResources().getColor(R.color.black));
        }
    }

    // 🔹 Trả về số lượng item trong danh sách
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 🔹 ViewHolder đại diện cho từng item trong RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Biến binding giúp truy cập trực tiếp vào các View trong layout item
        ViewholderCateforyBinding binding;
        public ViewHolder(ViewholderCateforyBinding binding) {
            // Khởi tạo ViewHolder với binding
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
