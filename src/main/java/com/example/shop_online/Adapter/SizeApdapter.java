package com.example.shop_online.Adapter;

import android.content.*;
import android.view.*;

import androidx.annotation.*;
import androidx.recyclerview.widget.*;

import com.example.shop_online.*;
import com.example.shop_online.databinding.*;

import java.lang.reflect.*;
import java.util.*;

public class SizeApdapter extends RecyclerView.Adapter<SizeApdapter.Viewholder> {
    ArrayList<String> items;
    Context context;
    int selectedPosition = -1;
    int lastSelectedPosition = -1;

    public SizeApdapter(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public SizeApdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderSizeBinding binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent, false);

        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeApdapter.Viewholder holder, int position) {
        holder.binding.sizeTxt.setText(items.get(position));
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(lastSelectedPosition);
                notifyItemChanged(selectedPosition);
            }
        });

        if(selectedPosition == holder.getAdapterPosition()){
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.size_selected);
            holder.binding.sizeTxt.setTextColor(context.getResources().getColor(R.color.purple));
        }else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.size_unselected);
            holder.binding.sizeTxt.setTextColor(context.getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderSizeBinding binding;
        public Viewholder(ViewholderSizeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
