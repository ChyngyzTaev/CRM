package com.example.CRM.service.impl;

import com.example.CRM.entity.ListExercises;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.ListExercisesModel;
import com.example.CRM.repository.ListExercisesRepository;
import com.example.CRM.service.ListExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListExercisesServiceImpl implements ListExercisesService {
    @Autowired
    private ListExercisesRepository repository;


    @Override
    public ListExercisesModel addNewSchedule(ListExercisesModel scheduleModel) {
        ListExercises schedule = new ListExercises();
        schedule.setId(scheduleModel.getId());
        schedule.setNameExercise(scheduleModel.getNameExercise());
        schedule.setWeekDayEnum(scheduleModel.getWeekDayEnum());
        schedule.prePersist();
        schedule.setActive(true);
        repository.save(schedule);
        return scheduleModel;
    }

    @Override
    public ListExercises setInActiveSchedule(ListExercises schedule, Long status) {
        schedule.setActive(true);
        return repository.save(schedule);
    }

    @Override
    public ListExercisesModel getScheduleById(Long id) {
        ListExercises schedule = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Список упражнений связанный с идентификатором " + id + " не найден"));
        return schedule.toModel();
    }

    @Override
    public List<ListExercisesModel> getAllSchedule() {
        return repository
                .findAll()
                .stream()
                .map(ListExercises::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public ListExercisesModel updateSchedule(ListExercisesModel scheduleModel) {
        if (scheduleModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (scheduleModel.getId() == null) {
            throw new InvalidParameterException("Id информации не может быть пустым");
        }

        ListExercises schedule = repository.getById(scheduleModel.getId());
        if (schedule == null) {
            throw new UserNotFoundException
                    ("Информация о пользоваетеле по id не найдена " + scheduleModel.getId());
        }

        schedule.setNameExercise(scheduleModel.getNameExercise());

        schedule = repository.save(schedule);

        return scheduleModel;
    }

    @Override
    public ListExercisesModel deleteScheduleById(Long id) {
        ListExercises schedule = getById(id);
        ListExercises deleteSchedule = setInActiveSchedule(schedule, -1L);
        return deleteSchedule.toModel();
    }


    public ListExercises getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Расписание связанный с идентификатором " + id + " не найдено"));
    }
}
