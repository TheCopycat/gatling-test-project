package com.worldline.formation.gatling.service.repository;

import com.worldline.formation.gatling.service.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by a501768 on 22/07/2016.
 */
@Repository
public class UserRepository implements CrudRepository<User,String> {

    private Map<String,User> users = new ConcurrentHashMap<>();

    @Override
    public <S extends User> S save(S s) {
        users.put(s.getId(),s);
        return s;
    }

    @Override
    public <S extends User> Iterable<S> save(Iterable<S> iterable) {
        List<S> results = new ArrayList<S>();
        for (S u : iterable) {
            results.add(save(u));
        }
        return results;
    }

    @Override
    public User findOne(String s) {
        return users.get(s);
    }

    @Override
    public boolean exists(String s) {
        return users.containsKey(s);
    }

    @Override
    public Iterable<User> findAll() {
        return users.values();
    }

    @Override
    public Iterable<User> findAll(Iterable<String> iterable) {
        List<User> result = new ArrayList<>();
        for (String key : iterable) {
            if (exists(key)) {
                result.add(findOne(key));
            }
        }
        return result;
    }

    @Override
    public long count() {
        return users.size();
    }

    @Override
    public void delete(String s) {
        users.remove(s);
    }

    @Override
    public void delete(User user) {
       delete(user.getId());
    }

    @Override
    public void delete(Iterable<? extends User> iterable) {
        for (User u : iterable) {
            delete(u);
        }
    }

    @Override
    public void deleteAll() {
        users.clear();
    }
}
