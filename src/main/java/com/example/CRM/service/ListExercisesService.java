package com.example.CRM.service;

import com.example.CRM.entity.ListExercises;
import com.example.CRM.model.ListExercisesModel;

import java.util.List;

public interface ListExercisesService{
    ListExercisesModel addNewSchedule(ListExercisesModel scheduleModel);

    ListExercises setInActiveSchedule(ListExercises schedule, Long status);

    ListExercisesModel getScheduleById(Long id);

    List<ListExercisesModel> getAllSchedule();

    ListExercisesModel updateSchedule(ListExercisesModel scheduleModel);

    ListExercisesModel deleteScheduleById(Long id);
}
