package com.restapp.springrestclient.services;

import com.restapp.springrestclient.domain.User;

import java.util.List;

public interface ApiService {

    List<User> getUsers(Integer limit);
}
