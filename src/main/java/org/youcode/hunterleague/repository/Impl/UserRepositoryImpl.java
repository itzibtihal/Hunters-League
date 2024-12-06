package org.youcode.hunterleague.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.repository.UserSearchRepository;
import org.youcode.hunterleague.service.DTOs.SearchUserDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserSearchRepository {


        @PersistenceContext
        private EntityManager entityManager;

        @Override
        public List<User> findByCriteria(SearchUserDTO searchUserDTO) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> user = query.from(User.class);

            List<Predicate> predicates = new ArrayList<>();

            if (searchUserDTO.getUsername() != null && !searchUserDTO.getUsername().isEmpty()) {
                predicates.add(cb.like(user.get("username"), "%" + searchUserDTO.getUsername() + "%"));
            }
            if (searchUserDTO.getEmail() != null && !searchUserDTO.getEmail().isEmpty()) {
                predicates.add(cb.like(user.get("email"), "%" + searchUserDTO.getEmail() + "%"));
            }

            query.where(cb.and(predicates.toArray(new Predicate[0])));

            return entityManager.createQuery(query).getResultList();
        }

}
