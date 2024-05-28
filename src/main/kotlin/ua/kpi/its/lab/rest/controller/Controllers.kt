package ua.kpi.its.lab.rest.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.EducationalInstitutionRequest
import ua.kpi.its.lab.rest.dto.EducationalInstitutionResponse
import ua.kpi.its.lab.rest.svc.EducationalInstitutionService

@RestController
@RequestMapping("/institutions")
class EducationalInstitutionController @Autowired constructor(
    private val institutionService: EducationalInstitutionService
) {
    @GetMapping(path = ["", "/"])
    fun institutions(): List<EducationalInstitutionResponse> = institutionService.read()

    @GetMapping("{id}")
    fun readInstitution(@PathVariable("id") id: Long): ResponseEntity<EducationalInstitutionResponse> {
        return wrapNotFound { institutionService.readById(id) }
    }

    @PostMapping(path = ["", "/"])
    fun createInstitution(@RequestBody institution: EducationalInstitutionRequest): EducationalInstitutionResponse {
        return institutionService.create(institution)
    }

    @PutMapping("{id}")
    fun updateInstitution(
        @PathVariable("id") id: Long,
        @RequestBody institution: EducationalInstitutionRequest
    ): ResponseEntity<EducationalInstitutionResponse> {
        return wrapNotFound { institutionService.updateById(id, institution) }
    }

    @DeleteMapping("{id}")
    fun deleteInstitution(
        @PathVariable("id") id: Long
    ): ResponseEntity<EducationalInstitutionResponse> {
        return wrapNotFound { institutionService.deleteById(id) }
    }

    fun <T> wrapNotFound(call: () -> T): ResponseEntity<T> {
        return try {
            val result = call()
            ResponseEntity.ok(result)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }
}
