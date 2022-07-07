package com.vrms.app.service;

import com.vrms.app.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    int count(String username);

    List<Appointment> listPage(int page, int size, String username);

    boolean save(Appointment appointment);

    boolean update(Appointment appointment);

    boolean remove(Integer appid);

    boolean changeStats(Appointment appointment);
}
