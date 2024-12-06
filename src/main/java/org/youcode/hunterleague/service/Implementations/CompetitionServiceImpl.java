package org.youcode.hunterleague.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.entities.Competition;
import org.youcode.hunterleague.repository.CompetitionRepository;
import org.youcode.hunterleague.service.CompetitionService;
import org.youcode.hunterleague.service.DTOs.CompetitionDTO;
import org.youcode.hunterleague.service.DTOs.mapper.CompetitionDTOMapper;
import org.youcode.hunterleague.utils.DateUtils;
import org.youcode.hunterleague.web.exception.InvalidCredentialsException;
import org.youcode.hunterleague.web.exception.competition.CompetitionAlreadyExistException;
import org.youcode.hunterleague.web.exception.competition.CompetitionNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionDTOMapper competitionDTOMapper;

    @Override
    public Competition createCompetition(Competition competition) {
        String code = generateCompetitionCode(competition.getLocation(),competition.getDate());
        competition.setCode(code);
        validateCompetition(competition);

        return competitionRepository.save(competition);
    }

    private void validateCompetition(Competition competition){
        if (competition.getMinParticipants() >= competition.getMaxParticipants()) {
            throw new InvalidCredentialsException("The minimum number of participants must be less than the maximum.");
        }

        findByCode(competition.getCode())
                .ifPresent(c -> {
                    throw new CompetitionAlreadyExistException("competition already exist");
                });

        List<Competition> competitionsInSameWeek = competitionRepository.findCompetitionsInSameWeek(competition.getDate());
        if (!competitionsInSameWeek.isEmpty()) {
            throw new InvalidCredentialsException("A competition already exists in the same week.");
        }
    }

    private String generateCompetitionCode(String location, LocalDateTime date) {
        String formattedDate = DateUtils.formatDate(date);
        return location + "_" + formattedDate;
    }

    @Override
    public Optional<Competition> findByCode(String code) {
        if (code == null){
            throw new InvalidCredentialsException("code can't be null");
        }
        return competitionRepository.findByCode(code);
    }

    @Override
    public Competition findById(UUID id) {
        if (id == null){
            throw new InvalidCredentialsException("id can't be null");
        }
        return competitionRepository.findById(id)
                .orElseThrow(()-> new CompetitionNotFoundException("Competition not found"));
    }

    @Override
    public Page<Competition> findAllCompetitionsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return competitionRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(UUID id) {
        if(id == null){
            throw new InvalidCredentialsException("id can't be null");
        }
        Competition competitionToDelete = findById(id);
        competitionRepository.delete(competitionToDelete);
        return true;
    }

    @Override
    public Competition update(UUID id, Competition competition) {
        Competition competitionToUpdated = findById(id);

        competitionToUpdated.setLocation(competition.getLocation());
        competitionToUpdated.setDate(competition.getDate());
        competitionToUpdated.setSpeciesType(competition.getSpeciesType());
        competitionToUpdated.setMinParticipants(competition.getMinParticipants());
        competitionToUpdated.setMaxParticipants(competition.getMaxParticipants());
        competitionToUpdated.setOpenRegistration(competition.getOpenRegistration());

        String code = generateCompetitionCode(competitionToUpdated.getLocation(),competitionToUpdated.getDate());
        competitionToUpdated.setCode(code);

        validateCompetition(competitionToUpdated);

        return competitionRepository.save(competitionToUpdated);
    }

    @Override
    public CompetitionDTO getCompetitionDetails(UUID id) {
        if (id == null){
            throw new InvalidCredentialsException("id can't be null");
        }
        return competitionRepository.findCompetitionDetailsById(id);
    }

    @Override
    public void closeRegistrationsBeforeCompetition() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24Hours = now.plusHours(24);

        competitionRepository.findByDateBetweenAndOpenRegistrationIsTrue(now,next24Hours)
                .forEach(competition -> {
                    competition.setOpenRegistration(false);
                    competitionRepository.save(competition);
                });
    }
}
