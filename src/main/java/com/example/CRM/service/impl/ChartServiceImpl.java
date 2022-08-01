package com.example.CRM.service.impl;

import com.example.CRM.convert.BaseConvert;
import com.example.CRM.entity.Chart;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.ChartModel;
import com.example.CRM.repository.ChartRepository;
import com.example.CRM.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ChartRepository repository;

    @Autowired
    private BaseConvert <ChartModel, Chart> convert;


    @Override
    public ChartModel addNewChart(ChartModel chartModel) {
        Chart chart = new Chart();
        chart.setId(chartModel.getId());
        chart.prePersist();
        repository.save(chart);
        return chartModel;
    }


    @Override
    public ChartModel getChartById(Long id) {
        return convert.convertFromEntity(getById(id));
    }

    @Override
    public List<ChartModel> getAllChart() {
        return getAll()
                .stream()
                .map(convert::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteChartById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Chart save(Chart chart) {
        return repository.save(chart);
    }

    @Override
    public Chart getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("График связанный с идентификатором " + id + "не найдено"));
    }

    @Override
    public List<Chart> getAll() {
        return repository.findAll();
    }
}
