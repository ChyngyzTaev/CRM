package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
import com.example.CRM.entity.Schedule;
import com.example.CRM.entity.SubscriptionTypes;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.ScheduleModel;
import com.example.CRM.repository.ScheduleRepository;
import com.example.CRM.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private BaseConvert<ScheduleModel, Schedule> convert;


    @Override
    public ScheduleModel addNewSchedule(ScheduleModel scheduleModel) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleModel.getId());
        schedule.setCreateDate(scheduleModel.getCreateDate());
        schedule.setNameExercise(scheduleModel.getNameExercise());
        repository.save(schedule);
        return scheduleModel;
    }

    @Override
    public Schedule setInActiveUser(Schedule schedule, Long status) {
        schedule.setActive(true);
        return repository.save(schedule);
    }

    @Override
    public ScheduleModel getScheduleById(Long id) {
        return convert.convertFromEntity(getById(id));
    }

    @Override
    public List<ScheduleModel> getAllSchedule() {
        return getAll()
                .stream()
                .map(convert::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleModel updateSchedule(ScheduleModel scheduleModel) {
        if (scheduleModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (scheduleModel.getId() == null) {
            throw new InvalidParameterException("Id информации не может быть пустым");
        }

        Schedule schedule = repository.getById(scheduleModel.getId());
        if (schedule == null) {
            throw new UserNotFoundException
                    ("Информация о пользоваетеле по id не найдена " + scheduleModel.getId());
        }

        schedule.setNameExercise(scheduleModel.getNameExercise());

        schedule = repository.save(schedule);

        return scheduleModel;
    }

    @Override
    public ScheduleModel deleteScheduleById(Long id) {
        Schedule schedule = getById(id);
        Schedule deleteSchedule = setInActiveUser(schedule, -1L);
        return convert.convertFromEntity(deleteSchedule);
    }

    @Override
    public Schedule save(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    public Schedule getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("id связанный с идентификатором " + id + " не найдено"));
    }

    @Override
    public List<Schedule> getAll() {
        return repository.findAll();
    }
}
