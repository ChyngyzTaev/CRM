package com.example.CRM.service;

import com.example.CRM.entity.ListExercises;
import com.example.CRM.model.ListExercises.CreateListExercisesModel;
import com.example.CRM.model.ListExercises.ListExercisesModel;
import com.example.CRM.model.ListExercises.UpdateListExercisesModel;

import java.util.List;

public interface ListExercisesService{
    ListExercisesModel addNewSchedule(CreateListExercisesModel createListExercisesModel);

    ListExercises setInActiveSchedule(ListExercises listExercises, Long status);

    ListExercisesModel getScheduleById(Long id);

    List<ListExercisesModel> getAllSchedule();

    UpdateListExercisesModel updateSchedule(UpdateListExercisesModel scheduleModel);

    ListExercisesModel deleteScheduleById(Long id);
}
