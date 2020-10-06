package com.example.demo.controller;

import com.example.demo.dto.CalendarDTO;
import com.example.demo.dto.PageCountDTO;
import com.example.demo.dto.exeption.DateNotCorrect;
import com.example.demo.dto.exeption.EntetyWithDateStartAndDateEndNotCreate;
import com.example.demo.dto.result.ResultDTO;
import com.example.demo.dto.result.SuccessResult;
import com.example.demo.service.CalendarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/calendar")
@Api(value = "Api for calendar service")
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    private static final int COUNT = 3;

    @GetMapping("/{email}")
    @ApiOperation(value = "Return list calendars", response = CalendarDTO.class)
    public List<CalendarDTO> allCalendar(@PathVariable("email") String email,
                                         @RequestParam(required = false, name = "page", defaultValue = "0") Integer pageCount) {

        return calendarService.findAllRecord(email, PageRequest.of(
                pageCount,
                COUNT,
                Sort.Direction.DESC,
                "id"));
    }

    @GetMapping("/{email}/all")
    @ApiOperation(value = "Return list calendars", response = CalendarDTO.class)
    public List<CalendarDTO> allCalendar(@PathVariable("email") String email) {

        return calendarService.findAll(email);
    }

    @PostMapping("/{emailuser}")
    @ApiOperation(value = "Create new calendar for user", response = CalendarDTO.class)
    public ResponseEntity<ResultDTO> addCalendar(@PathVariable("emailuser") String email, @RequestBody CalendarDTO calendarDTO) throws ParseException, DateNotCorrect, EntetyWithDateStartAndDateEndNotCreate {
        //Date check.
        if (calendarDTO.getDataEnd().getTime() - calendarDTO.getDateStart().getTime() < 0) {
            throw new DateNotCorrect();
        }
        String e = "gomenyukgleb@gmail.com";
        calendarService.save(calendarDTO.getDateStart(), calendarDTO.getDataEnd(), calendarDTO.getTime(), e);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @GetMapping("count")
    public PageCountDTO count() {
        return PageCountDTO.of(calendarService.count(), COUNT);
    }
}
