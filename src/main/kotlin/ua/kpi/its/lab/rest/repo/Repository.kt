package ua.kpi.its.lab.rest.repo

import org.springframework.data.jpa.repository.JpaRepository
import ua.kpi.its.lab.rest.entity.Discipline
import ua.kpi.its.lab.rest.entity.EducationalInstitution

interface EducationalInstitutionRepository : JpaRepository<EducationalInstitution, Long>

interface DisciplineRepository : JpaRepository<Discipline, Long>
