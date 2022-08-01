package com.example.CRM.service;

import com.example.CRM.entity.Chart;
import com.example.CRM.entity.Journal;
import com.example.CRM.model.ChartModel;

import java.util.List;

public interface ChartService extends BaseService<Chart> {
    ChartModel addNewChart(ChartModel chartModel);

    ChartModel getChartById(Long id);

    List<ChartModel> getAllChart();

    void deleteChartById(Long id);
}
