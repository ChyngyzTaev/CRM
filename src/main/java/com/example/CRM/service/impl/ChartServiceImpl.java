package com.example.CRM.service.impl;

import com.example.CRM.entity.Chart;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.ChartModel;
import com.example.CRM.repository.ChartRepository;
import com.example.CRM.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ChartRepository repository;


    @Override
    public ChartModel addNewChart(ChartModel chartModel) {
        Chart chart = new Chart();
        chart.setId(chartModel.getId());
        chart.setWeekDayEnum(chartModel.getWeekDayEnum());
        chart.prePersist();
        repository.save(chart);
        return chartModel;
    }


    @Override
    public ChartModel getChartById(Long id) {
        Chart chart = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        ("График связанный с идентификатором " + id + " не найден"));
        return chart.toModel();
    }

    @Override
    public List<ChartModel> getAllChart() {
        return repository.findAll()
                .stream()
                .map(Chart::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteChartById(Long id) {
        repository.deleteById(id);
    }
}
