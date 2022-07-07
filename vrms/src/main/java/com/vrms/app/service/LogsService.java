package com.vrms.app.service;

import com.vrms.app.entity.Logs;

import java.util.List;

public interface LogsService {
    List<Logs> listPage(int page,
                        int size);

    int count();

    boolean save(Logs logs);
}
