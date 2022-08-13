package com.example.CRM.service;

import com.example.CRM.entity.Chart;
import com.example.CRM.model.chart.ChartModel;
import com.example.CRM.model.chart.CreateChartModel;
import com.example.CRM.model.chart.UpdateChartModel;

import java.util.List;

public interface ChartService{
    ChartModel addNewChart(CreateChartModel chartModel);

    Chart setInActiveSchedule(Chart chart, Long status);

    ChartModel getChartById(Long id);

    boolean updateChart(UpdateChartModel chartModel);

    List<ChartModel> getAllChart();

    ChartModel deleteChartById(Long id);
}
