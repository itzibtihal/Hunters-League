package org.youcode.hunterleague.service.DTOs;


import java.time.LocalDateTime;


public class CompetitionDTO {

    private String code;

    private String location;

    private LocalDateTime date;

    private Long numberOfParticipants;

    public CompetitionDTO() {
    }

    public CompetitionDTO(String code, String location, LocalDateTime date, Long numberOfParticipants) {
        this.code = code;
        this.location = location;
        this.date = date;
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
}
