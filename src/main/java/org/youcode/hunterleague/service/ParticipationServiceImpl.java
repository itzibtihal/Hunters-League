package org.youcode.hunterleague.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.Competition;
import org.youcode.hunterleague.domain.Participation;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.domain.enums.Role;
import org.youcode.hunterleague.exception.competition.CompetitionNotFoundException;
import org.youcode.hunterleague.exception.participation.InvalidParticipationException;
import org.youcode.hunterleague.exception.participation.MaxParticipationsReachedException;
import org.youcode.hunterleague.exception.user.UnauthorizedActionException;
import org.youcode.hunterleague.exception.user.UserLicenseExpiredException;
import org.youcode.hunterleague.repository.CompetitionRepository;
import org.youcode.hunterleague.repository.ParticipationRepository;
import org.youcode.hunterleague.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipationServiceImpl {

    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;

    @Autowired
    public ParticipationServiceImpl(CompetitionRepository competitionRepository, UserRepository userRepository, ParticipationRepository participationRepository) {
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
        this.participationRepository = participationRepository;
    }

    public void participateInCompetition(UUID userId, UUID competitionId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);

        if (userOpt.isPresent() && competitionOpt.isPresent()) {
            User user = userOpt.get();
            Competition competition = competitionOpt.get();

            // Check if the user is not a member
            if (!Role.MEMBER.equals(user.getRole())) {
                throw new UnauthorizedActionException("Sorry, you are not a member");
            }

            // Check if the competition date is in the future
            if (competition.getDate().isBefore(LocalDateTime.now())) {
                throw new InvalidParticipationException("Competition date has already passed");
            }

            // Check if the competition has reached the maximum number of participants
            if (competition.getParticipantCount() >= competition.getMaxParticipants()) {
                throw new MaxParticipationsReachedException("Competition has reached the maximum number of participants");
            }

            // Check if the user has a valid license
            if (user.getLicenseExpirationDate() == null || user.getLicenseExpirationDate().isBefore(LocalDateTime.now())) {
                throw new UserLicenseExpiredException(user.getLicenseExpirationDate());
            }

            // Check if the user is already registered for the competition
            boolean alreadyRegistered = participationRepository.existsByUserAndCompetition(user, competition);
            if (alreadyRegistered) {
                throw new InvalidParticipationException("You are already registered for this competition");
            }

            if (Boolean.TRUE.equals(competition.getOpenRegistration())) {
                Participation participation = new Participation();
                participation.setUser(user);
                participation.setCompetition(competition);
                participationRepository.save(participation);
            } else {
                throw new InvalidParticipationException("Registration is closed");
            }
        } else {
            throw new CompetitionNotFoundException("Invalid user or competition ID");
        }
    }
}