package com.example.CRM.service;

import com.example.CRM.model.chart.ChartModel;
import com.example.CRM.model.chart.CreateChartModel;
import com.example.CRM.model.chart.UpdateChartModel;

import java.util.List;

public interface ChartService{
    CreateChartModel addNewChart(CreateChartModel chartModel);

    ChartModel getChartById(Long id);

    UpdateChartModel updateChart(UpdateChartModel chartModel);

    List<ChartModel> getAllChart();

    void deleteChartById(Long id);
}
