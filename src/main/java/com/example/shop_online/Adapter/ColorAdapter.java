package com.example.shop_online.Adapter;

import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.*;

import androidx.annotation.*;
import androidx.appcompat.content.res.*;
import androidx.core.graphics.drawable.*;
import androidx.recyclerview.widget.*;

import com.bumptech.glide.*;
import com.bumptech.glide.request.*;
import com.example.shop_online.*;
import com.example.shop_online.R;
import com.example.shop_online.databinding.*;

import java.util.*;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.Viewholder> {
    ArrayList<String> items;
    Context context;
    int selectedPosition = -1;
    int lastSelectedPositon = -1;

    public ColorAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ColorAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderColorBinding binding = ViewholderColorBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.Viewholder holder, int position) {
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPositon = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(lastSelectedPositon);
                notifyItemChanged(selectedPosition);
            }
        });

        if(selectedPosition == holder.getAdapterPosition()){
            Drawable unwrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.color_selected);
            Glide.with(context)
                    .load(unwrapperDrawable)
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.binding.colorLayout);

        }else {
            Drawable unwrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.color_selected);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrapperDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(items.get(position)));
            Glide.with(context)
                    .load(unwrapperDrawable)
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.binding.colorLayout);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderColorBinding binding;
        public Viewholder(ViewholderColorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
