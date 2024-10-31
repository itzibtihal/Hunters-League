package org.youcode.hunterleague.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.Competition;
import org.youcode.hunterleague.exception.competition.InvalidCompetitionDataException;
import org.youcode.hunterleague.repository.CompetitionRepository;
import org.youcode.hunterleague.service.CompetitionService;
import org.youcode.hunterleague.exception.competition.CompetitionNotFoundException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition createCompetition(Competition competition) {
        if (competition.getLocation() == null || competition.getDate() == null) {
            throw new InvalidCompetitionDataException("Location and Date must not be null");
        }

        if (competition.getMinParticipants() == null || competition.getMaxParticipants() == null) {
            throw new InvalidCompetitionDataException("Minimum and Maximum number of participants must be defined");
        }

        if (competition.getMinParticipants() >= competition.getMaxParticipants()) {
            throw new InvalidCompetitionDataException("Minimum number of participants must be less than the maximum number of participants");
        }

        if (competition.getDate().isBefore(LocalDate.now().atStartOfDay())) {
            throw new InvalidCompetitionDataException("Competition date cannot be in the past");
        }

        LocalDate startOfWeek = competition.getDate().toLocalDate().with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = competition.getDate().toLocalDate().with(DayOfWeek.SUNDAY);
        List<Competition> competitionsInWeek = competitionRepository.findAllByDateBetween(startOfWeek.atStartOfDay(), endOfWeek.atTime(LocalTime.MAX));
        if (!competitionsInWeek.isEmpty()) {
            throw new InvalidCompetitionDataException("Only one competition can be created per week");
        }

        String code = competition.getLocation() + "-" + competition.getDate().toLocalDate();
        competition.setCode(code);

        return competitionRepository.save(competition);
    }

    @Override
    public void deleteCompetition(UUID id) {
        if (!competitionRepository.existsById(id)) {
            throw new CompetitionNotFoundException("Competition with ID " + id + " not found.");
        }
        competitionRepository.deleteById(id);
    }

    @Override
    public Competition updateCompetition(UUID id, Competition competitionDetails) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition with ID " + id + " not found."));

        competition.setLocation(competitionDetails.getLocation());
        competition.setDate(competitionDetails.getDate());
        competition.setSpeciesType(competitionDetails.getSpeciesType());
        competition.setMinParticipants(competitionDetails.getMinParticipants());
        competition.setMaxParticipants(competitionDetails.getMaxParticipants());
        competition.setOpenRegistration(competitionDetails.getOpenRegistration());

        String code = competitionDetails.getLocation() + "-" + competitionDetails.getDate().toLocalDate();
        competition.setCode(code);

        return competitionRepository.save(competition);
    }

    @Override
    public Competition getCompetitionDetails(UUID id) {
        return competitionRepository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition with ID " + id + " not found."));
    }

    @Override
    public Page<Competition> getAllCompetitions(Pageable pageable) {
        return competitionRepository.findAll(pageable); // Use the repository to fetch paginated data
    }

    @Override
    public Page<Competition> getCompetitionsByLocation(String location, int page, int size) {
        return competitionRepository.findByLocation(location, PageRequest.of(page, size));
    }

    @Override
    public Page<Competition> getCompetitionsByOpenRegistration(boolean openRegistration, int page, int size) {
        return competitionRepository.findByOpenRegistration(openRegistration, PageRequest.of(page, size));
    }
}
