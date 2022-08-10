package com.example.CRM.service.impl;

import com.example.CRM.entity.Chart;
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
    public CreateChartModel addNewChart(CreateChartModel chartModel) {
        Chart chart = new Chart();
        chart.setWeekDayEnum(chartModel.getWeekDayEnum());
        chart.setIsActive(chartModel.getIsActive());
        chart.setCreateDate(chartModel.getCreateDate());
        chartRepository.save(chart);
        return chartModel;
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
    public UpdateChartModel updateChart(UpdateChartModel chartModel) {
        if (chartModel == null){
            throw new UserNotFoundException("Созданная информация о пользователе имеет " + "пустое" + "значение");
        } else if (chartModel.getId() == null) {
            throw new InvalidParameterException("Id роли не может иметь пустое значени");
        }

        Chart chart = chartRepository.getById(chartModel.getId());
        if (chart == null) {
            throw new UserNotFoundException
                    ("Роль по id не нанйдено " + chartModel.getId());
        }

        chart.setWeekDayEnum(chartModel.getWeekDayEnum());
        chart.setCreateDate(chartModel.getCreateDate());

        chart = chartRepository.save(chart);

        return chartModel;
    }

    @Override
    public List<ChartModel> getAllChart() {
        return chartRepository.findAll()
                .stream()
                .map(Chart::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteChartById(Long id) {
        chartRepository.deleteById(id);
    }
}
