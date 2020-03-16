package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    @Autowired
    DataSource mysqlDataSource;

    public User login(String email, String password) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(mysqlDataSource);

        User user;
        try {
            user = jdbcTemplate.queryForObject("SELECT email, password from user_spring where email = '" + email + "' and password = '" + password + "'", new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    if (resultSet != null) {
                        return new User(resultSet.getString("email"),
                                resultSet.getString("password"));
                    } else return null;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }
//        return jdbcTemplate.queryForObject("SELECT email, password from user_spring where email = '" + email + "' and password = '" + password + "'", new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet resultSet, int i) throws SQLException {
//                if (resultSet != null){
//                    return new User(resultSet.getString("email"),
//                            resultSet.getString("password"));
//                }
//                else return null;
//            }
//        });

}
