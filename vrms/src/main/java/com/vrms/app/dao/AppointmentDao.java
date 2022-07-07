package com.vrms.app.dao;

import com.vrms.app.entity.Appointment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentDao {
    List<Appointment> listPage(@Param("page") int page,
                               @Param("size") int size,
                               @Param("username") String username);

    int count(String username);

    int save(Appointment appointment);

    int update(Appointment appointment);

    int remove(Integer appid);

    int changeStats(Appointment appointment);
}
