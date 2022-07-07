package com.vrms.app.service;

import com.vrms.app.entity.Vaccines;

import java.util.List;

public interface VaccinesService {
    List<Vaccines> listPage(int page,
                            int size,
                            String vacname);

    int count(String vacname);

    boolean save(Vaccines vaccines);

    boolean update(Vaccines vaccines);

    boolean remove(Integer vacid);

    List<Vaccines> list();
}
