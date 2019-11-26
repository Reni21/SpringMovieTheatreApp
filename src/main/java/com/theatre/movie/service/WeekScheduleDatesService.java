package com.theatre.movie.service;

import com.theatre.movie.dto.MenuDateViewDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class WeekScheduleDatesService {

    public List<MenuDateViewDto> getWeekScheduleDates(LocalDate lookupDate) {
        LocalDate now = LocalDate.now();
        return IntStream.range(0, 7)
                .mapToObj(i -> {
                    MenuDateViewDto dto = new MenuDateViewDto(now.plusDays(i));
                    if (dto.getDate().isEqual(lookupDate)) {
                        dto.setActive(true);
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

}
