package com.example.shop_online.Adapter;

import android.content.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.recyclerview.widget.*;

import com.bumptech.glide.*;
import com.example.shop_online.databinding.*;

import java.util.*;

public class PicListAdapter extends RecyclerView.Adapter<PicListAdapter.Viewholder> {

    private List<String> items;
    private ImageView picMain;
    private Context context;

    public PicListAdapter(List<String> items, ImageView picMain) {
        this.items = items;
        this.picMain = picMain;
    }

    @NonNull
    @Override
    public PicListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderPiclistBinding binding = ViewholderPiclistBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PicListAdapter.Viewholder holder, int position) {
        Glide.with(context)
                .load(items.get(position))
                .into(holder.binding.pic);
        holder.binding.getRoot().setOnClickListener(v -> Glide.with(context)
                .load(items.get(position))
                .into(picMain));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderPiclistBinding binding;
        public Viewholder(ViewholderPiclistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
