package com.qph.attendancerecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class AttendanceRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceRecordApplication.class, args);
	}

}
