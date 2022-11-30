package com.example.CRM.service.impl;

import com.example.CRM.entity.Chart;
import com.example.CRM.exception.ApiFailException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.exception.UserNotFoundException;
import com.example.CRM.model.chart.ChartModel;
import com.example.CRM.model.chart.CreateChartModel;
import com.example.CRM.model.chart.UpdateChartModel;
import com.example.CRM.repository.ChartRepository;
import com.example.CRM.service.ChartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartServiceImpl implements ChartService {
    @Autowired
    private ChartRepository chartRepository;


    @Override
    public ChartModel addNewChart(CreateChartModel createChartModel) {
        validateVariablesForNull(createChartModel);
        Chart chart = createChartModel.toChart();
        chartRepository.save(chart);
        return chart.toModel();
    }

    @Override
    public Chart setInActiveSchedule(Chart chart, Long status) {
        chart.setIsActive(chart.getIsActive());
        return chartRepository.save(chart);
    }


    @Override
    public ChartModel getChartById(Long id) {
        Chart chart = chartRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("График связанный с идентификатором " + id + " не найден"));
        return chart.toModel();
    }

    @Override
    public boolean updateChart(UpdateChartModel chartModel) {
        if (chartModel == null){
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (chartModel.getId() == null) {
            throw new InvalidParameterException("Id роли не может иметь пустое значени");
        }
        Chart chart = chartRepository.getById(chartModel.getId());
        if (chart == null) {
            throw new UserNotFoundException
                    ("График по id не нанйдено " + chartModel.getId());
        }
        chart.setWeekDayEnum(chartModel.getWeekDayEnum());
        chart.setCreateDate(chartModel.getCreateDate());
        chart = chartRepository.save(chart);
        return chart.getId() != null;
    }

    @Override
    public List<ChartModel> getAllChart() {
        return chartRepository.findAll()
                .stream()
                .map(Chart::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public ChartModel deleteChartById(Long id) {
        Chart chart = getById(id);
        Chart deleteChart = setInActiveSchedule(chart, -1L);
        return deleteChart.toModel();
    }

    public Chart getById(Long id) {
        return chartRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException
                                ("Пользоватлеь связанный с идентификатором " + id + " не найдено"));
    }

    public void validateVariablesForNull(CreateChartModel chartModel){
        if (chartModel.getWeekDayEnum() == null)
            throw new ApiFailException(("День недели не заполнен"));
    }
}
