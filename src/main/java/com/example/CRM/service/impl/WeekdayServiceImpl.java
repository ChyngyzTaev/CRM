package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
import com.example.CRM.entity.Weekday;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.WeekdayModel;
import com.example.CRM.repository.WeekdayRepository;
import com.example.CRM.service.WeekdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class WeekdayServiceImpl implements WeekdayService {
    @Autowired
    private WeekdayRepository repository;

    @Autowired
    private BaseConvert<WeekdayModel, Weekday> convert;

    @Override
    public WeekdayModel addWeekday(WeekdayModel weekdayModel) {
        Weekday weekday = new Weekday();
        weekday.setId(weekdayModel.getId());
        weekday.setMonday(weekdayModel.getMonday());
        weekday.setTuesday(weekdayModel.getTuesday());
        weekday.setWednesday(weekdayModel.getWednesday());
        weekday.setThursday(weekdayModel.getThursday());
        weekday.setFriday(weekdayModel.getFriday());
        weekday.setSaturday(weekdayModel.getSaturday());
        weekday.setSunday(weekdayModel.getSunday());
        weekday.prePersist();
        repository.save(weekday);
        return weekdayModel;
    }

    @Override
    public List<WeekdayModel> getAllWeekday() {
        return getAll()
                .stream()
                .map(convert::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Weekday save(Weekday weekday) {
        return repository.save(weekday);
    }

    @Override
    public Weekday getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("День связанный с идентификатором " + id + "" + " не найдено"));
    }

    @Override
    public List<Weekday> getAll() {
        return repository.findAll();
    }

}
