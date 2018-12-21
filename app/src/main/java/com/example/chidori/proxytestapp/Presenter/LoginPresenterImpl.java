package com.example.chidori.proxytestapp.Presenter;


import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Model.LoginModelImpl;

public class LoginPresenterImpl implements Contract.ILoginPresenter {

    //Model
    private LoginModelImpl model;
    //View
    private Contract.ILoginView loginView;

    public LoginPresenterImpl() {
        model = new LoginModelImpl();
    }

    public void attachView(Contract.ILoginView view) {
        loginView = view;
    }
}
