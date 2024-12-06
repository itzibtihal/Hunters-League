package org.youcode.hunterleague.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.entities.*;
import org.youcode.hunterleague.domain.enums.Difficulty;
import org.youcode.hunterleague.domain.enums.SpeciesType;
import org.youcode.hunterleague.repository.ParticipationRepository;
import org.youcode.hunterleague.service.CompetitionService;
import org.youcode.hunterleague.service.DTOs.ParticipationResultDTO;
import org.youcode.hunterleague.service.DTOs.PodiumDTO;
import org.youcode.hunterleague.service.ParticipationService;
import org.youcode.hunterleague.service.UserService;
import org.youcode.hunterleague.web.exception.InvalidCredentialsException;
import org.youcode.hunterleague.web.exception.competition.RegistrationClosedException;
import org.youcode.hunterleague.web.exception.participation.ParticipationAlreadyExistException;
import org.youcode.hunterleague.web.exception.participation.ParticipationNotFoundException;
import org.youcode.hunterleague.web.exception.user.LicenseExpiredException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;
    private final UserService userService;
    private final CompetitionService competitionService;


    @Override
    public Participation registerUserToCompetition(UUID userId, UUID competitionId) {

        Competition competition = competitionService.findById(competitionId);
        User user = userService.findById(userId);

        if (Boolean.FALSE.equals(competition.getOpenRegistration())) {
            throw new RegistrationClosedException("Registration for this competition is closed.");
        }

        if (user.getLicenseExpirationDate() != null && user.getLicenseExpirationDate().isBefore(LocalDateTime.now())) {
            throw new LicenseExpiredException("User's license has expired and cannot participate.");
        }


        boolean isAlreadyRegistered = participationRepository.existsByUserAndCompetition(user,competition);
        if (isAlreadyRegistered){
            throw new ParticipationAlreadyExistException("User is already registered for this competition.");
        }

        Participation participation = Participation.builder()
                .user(user)
                .competition(competition)
                .score(0.)
                .build();

        return participationRepository.save(participation);
    }

    @Override
    public Participation findById(UUID id) {
        return participationRepository.findById(id)
                .orElseThrow(()->new ParticipationNotFoundException("participation Not found"));
    }

    @Override
    public void updateParticipationScore(Participation participation) {
        double score = 0.0;

        for (Hunt hunt : participation.getHunts()) {
            Species species = hunt.getSpecies();
            SpeciesType speciesType = species.getCategory();
            Difficulty difficulty = species.getDifficulty();

            score += (species.getPoints() + hunt.getWeight()) * speciesType.getValue() * difficulty.getValue();
        }

        participation.setScore(score);
        participationRepository.save(participation);
    }

    @Override
    public List<ParticipationResultDTO> getUserResults(UUID userId) {
        if (userId ==null){
            throw new InvalidCredentialsException("user id can't be null");
        }
        List<Participation> participations = participationRepository.findByUserId(userId);

        return participations.stream()
                .map(participation -> ParticipationResultDTO.builder()
                        .competitionCode(participation.getCompetition().getCode())
                        .speciesType(participation.getHunts().get(0).getSpecies().getCategory())
                        .score(participation.getScore())
                        .build())
                .toList();
    }

    @Override
    public ParticipationResultDTO getUserCompetitionResult(UUID userId, UUID competitionId) {
        Participation participation = participationRepository.findByUserIdAndCompetitionId(userId, competitionId)
                .orElseThrow(() -> new ParticipationNotFoundException("Participation not found for user and competition"));

        return ParticipationResultDTO.builder()
                .competitionCode(participation.getCompetition().getCode())
                .speciesType(participation.getHunts().get(0).getSpecies().getCategory())
                .score(participation.getScore())
                .build();
    }

    @Override
    public List<PodiumDTO> getTopThreeParticipants(UUID competitionId) {
        if (competitionId == null){
            throw new InvalidCredentialsException("competition id cant be null");
        }
        competitionService.findById(competitionId);
        return participationRepository.findTopThreeByCompetition(competitionId);
    }
}
