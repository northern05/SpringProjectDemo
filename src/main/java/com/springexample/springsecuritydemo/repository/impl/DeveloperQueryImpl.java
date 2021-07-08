package com.springexample.springsecuritydemo.repository.impl;

import com.springexample.springsecuritydemo.dto.DeveloperDTO;
import com.springexample.springsecuritydemo.dto.utils.DeveloperMapping;
import com.springexample.springsecuritydemo.model.enam.SortType;
import com.springexample.springsecuritydemo.model.entity.Developer;
import com.springexample.springsecuritydemo.repository.DeveloperQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DeveloperQueryImpl implements DeveloperQuery {

    private final EntityManager entityManager;
    private final DeveloperMapping developerMapping;

    public DeveloperQueryImpl(EntityManager entityManager, DeveloperMapping developerMapping) {
        this.entityManager = entityManager;
        this.developerMapping = developerMapping;
    }

    @Override
    public DeveloperDTO findByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Developer> criteriaQuery = cb.createQuery(Developer.class);

        Root<Developer> developerRoot = criteriaQuery.from(Developer.class);

        Predicate emailPredicate = cb.equal(developerRoot.get("email"), email);
        criteriaQuery.where(emailPredicate);
       List<DeveloperDTO> query = entityManager.createQuery(criteriaQuery)
               .getResultList().stream()
               .map(developerMapping::mapToDeveloperDTO)
               .collect(Collectors.toList());
        return query.get(0);
    }


    public Page<DeveloperDTO> getDevelopers(Integer page,
                                            Integer pageSize,
                                            Developer.SortField sortField,
                                            SortType sortType,
                                            List<String> firstNamesFilter,
                                            List<String> departmentNamesFilter
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Developer> criteriaQuery = cb.createQuery(Developer.class);

        Root<Developer> developerRoot = criteriaQuery.from(Developer.class);

        List<Predicate> predicates = new ArrayList<>();

        if (firstNamesFilter != null && firstNamesFilter.size() != 0) {
            CriteriaBuilder.In<String> inClause = cb.in(developerRoot.get("firstName"));
            firstNamesFilter.forEach(inClause::value);
            predicates.add(inClause);
        }

        if (departmentNamesFilter != null && departmentNamesFilter.size() != 0) {
            CriteriaBuilder.In<String> inClause = cb.in(developerRoot.get("department"));
            departmentNamesFilter.forEach(inClause::value);
            predicates.add(inClause);
        }

        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));

        if (sortType == null) {
            criteriaQuery.orderBy(cb.desc(developerRoot.get("latName")));
        }

        if (sortType == SortType.ASC) {
            switch (sortField) {
                case email:
                    criteriaQuery.orderBy(cb.asc(developerRoot.get("email")));
                    break;
                case lastName:
                    criteriaQuery.orderBy(cb.asc(developerRoot.get("lastName")));
                    break;
                case firstName:
                    criteriaQuery.orderBy(cb.asc(developerRoot.get("firstName")));
                    break;
                case department:
                    criteriaQuery.orderBy(cb.asc(developerRoot.get("department")));
                    break;
            }
        }

        if (sortType == SortType.DESC) {
            switch (sortField) {
                case email:
                    criteriaQuery.orderBy(cb.desc(developerRoot.get("email")));
                    break;
                case lastName:
                    criteriaQuery.orderBy(cb.desc(developerRoot.get("lastName")));
                    break;
                case firstName:
                    criteriaQuery.orderBy(cb.desc(developerRoot.get("firstName")));
                    break;
                case department:
                    criteriaQuery.orderBy(cb.desc(developerRoot.get("department")));
                    break;
            }
        }

        criteriaQuery.select(developerRoot);

        int totalRows = entityManager
                .createQuery(criteriaQuery).getResultList().size();

        List<DeveloperDTO> resultList = entityManager
                .createQuery(criteriaQuery)
                .setFirstResult(page * pageSize)
                .setMaxResults(pageSize)
                .getResultList().stream().map(developerMapping::mapToDeveloperDTO).collect(Collectors.toList());
        Pageable pageable = PageRequest.of(page, pageSize);

        return new PageImpl<>(resultList, pageable, totalRows);
    }

    public void deleteDeveloper (Long id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<Developer> criteriaDelete = cb.createCriteriaDelete(Developer.class);
        Root<Developer> developerRoot = criteriaDelete.from(Developer.class);
        criteriaDelete.where(cb.greaterThan(developerRoot.get("id"), id));

        entityManager.getTransaction().begin();
        entityManager.createQuery(criteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Developer updateDeveloper (DeveloperDTO developerDTO){
        Developer entity = developerMapping.mapToDeveloperEntity(developerDTO);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Developer> criteriaUpdate = cb.createCriteriaUpdate(Developer.class);
        Root<Developer> developerRoot = criteriaUpdate.from(Developer.class);
        criteriaUpdate.set("firstName", entity.getFirstName());
        criteriaUpdate.set("lastName", entity.getLastName());
        criteriaUpdate.set("email", entity.getEmail());
        criteriaUpdate.set("department", entity.getDepartment());
        criteriaUpdate.where(cb.equal(developerRoot.get("id"), entity.getId()));

        entityManager.getTransaction().begin();
        entityManager.createQuery(criteriaUpdate).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }
}
