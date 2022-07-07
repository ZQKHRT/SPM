package com.vrms.app.service.impl;

import com.vrms.app.dao.VaccinesDao;
import com.vrms.app.entity.Vaccines;
import com.vrms.app.service.VaccinesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VaccinesImpl implements VaccinesService {
    @Resource
    private VaccinesDao vaccinesDao;

    @Override
    public List<Vaccines> listPage(int page, int size, String vacname) {
        return vaccinesDao.listPage((page-1)*size,size,vacname);
    }

    @Override
    public int count(String vacname) {
        return vaccinesDao.count(vacname);
    }

    @Override
    public boolean save(Vaccines vaccines) {
        vaccines.setCtime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())));
        return vaccinesDao.save(vaccines)>0;
    }

    @Override
    public boolean update(Vaccines vaccines) {
        vaccines.setMtime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())));
        return vaccinesDao.update(vaccines)>0;
    }

    @Override
    public boolean remove(Integer vacid) {
        return vaccinesDao.remove(vacid)>0;
    }

    @Override
    public List<Vaccines> list() {
        return vaccinesDao.list();
    }
}
