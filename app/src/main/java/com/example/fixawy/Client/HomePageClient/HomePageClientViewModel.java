package com.example.fixawy.Client.HomePageClient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fixawy.Pojos.User;

import java.util.List;

public class HomePageClientViewModel extends ViewModel {

    //private HomePageReposatory homePageReposatory = new HomePageReposatory();
    private MutableLiveData<List<User>>mutableLiveData = new MutableLiveData<>();
    public LiveData<List<User>> getEmployeeListModelData() {
        return mutableLiveData;
    }


    String jobTitle = "";
    List<User>employees;
    public  HomePageClientViewModel(){
       // mutableLiveData.setValue(homePageReposatory.getEmployeeData(jobTitle,employees));
    }

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
       // homePageReposatory = HomePageReposatory.getInstance();
        //mutableLiveData = homePageReposatory.getEmployeeData(String jobTitle,List<User>users)

    }

}