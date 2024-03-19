package com.example.bmicalculator.database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.bmicalculator.models.User;
import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user_info")
    List<User> getAllUser();

    @Query("SELECT * FROM user_info ORDER BY dateTime DESC LIMIT 1")
    User getLatestUser();

}
