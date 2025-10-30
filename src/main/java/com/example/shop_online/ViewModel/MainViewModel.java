package com.example.shop_online.ViewModel;

import androidx.lifecycle.*;

import com.example.shop_online.Domain.*;
import com.example.shop_online.Respository.*;

import java.util.*;

public class MainViewModel extends ViewModel {
    private final MainRespository respository = new MainRespository();

    public LiveData<ArrayList<CategoryModel>> loadCategory(){
        return respository.loadCategory();
    }
}
