package com.example.CRM.service.impl;

import com.example.CRM.entity.ListExercises;
import com.example.CRM.exception.ApiFailException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.ListExercises.CreateListExercisesModel;
import com.example.CRM.model.ListExercises.ListExercisesModel;
import com.example.CRM.model.ListExercises.UpdateListExercisesModel;
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
    private ListExercisesRepository listExercisesRepository;


    @Override
    public ListExercisesModel addNewSchedule(CreateListExercisesModel createListExercisesModel) {
        ListExercises listExercises = createListExercisesModel.toListExercises();
        validateVariablesForNull(createListExercisesModel);
        listExercisesRepository.save(listExercises);
        return listExercises.toModel();
    }

    @Override
    public ListExercises setInActiveSchedule(ListExercises listExercises, Long status) {
        listExercises.setIsActive(listExercises.getIsActive());
        return listExercisesRepository.save(listExercises);
    }

    @Override
    public ListExercisesModel getScheduleById(Long id) {
        ListExercises schedule = listExercisesRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("Список упражнений связанный с идентификатором " + id + " не найден"));
        return schedule.toModel();
    }

    @Override
    public List<ListExercisesModel> getAllSchedule() {
        return listExercisesRepository
                .findAll()
                .stream()
                .map(ListExercises::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public UpdateListExercisesModel updateSchedule(UpdateListExercisesModel scheduleModel) {
        if (scheduleModel == null) {
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (scheduleModel.getId() == null) {
            throw new InvalidParameterException("Id Списков упражнений не может иметь пустое значени");
        }

        ListExercises schedule = listExercisesRepository.getById(scheduleModel.getId());
        if (schedule == null) {
            throw new UserNotFoundException
                    ("Список упражнений по id не найдена " + scheduleModel.getId());
        }

        schedule.setNameExercise(scheduleModel.getNameExercise());
        schedule.setWeekDayEnum(scheduleModel.getWeekDayEnum());
        schedule.setCreateDate(scheduleModel.getCreateDate());

        schedule = listExercisesRepository.save(schedule);

        return scheduleModel;
    }

    @Override
    public ListExercisesModel deleteScheduleById(Long id) {
        ListExercises listExercises = getById(id);
        ListExercises deleteListExercises = setInActiveSchedule(listExercises, -1L);
        return deleteListExercises.toModel();
    }


    public ListExercises getById(Long id) {
        return listExercisesRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Расписание связанный с идентификатором " + id + " не найдено"));
    }

    public void validateVariablesForNull (CreateListExercisesModel createListExercisesModel){
        if (createListExercisesModel.getWeekDayEnum() == null)
            throw new ApiFailException("WeekDay не заполнен");
        if (createListExercisesModel.getNameExercise() == null)
            throw new ApiFailException("NameExercises не заполнен");
    }
}
