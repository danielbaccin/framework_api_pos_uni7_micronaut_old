package com.kenuiworks.frameworkbox.controller;

import com.kenuiworks.frameworkbox.domain.Genre;
import com.kenuiworks.frameworkbox.dto.FrameworkDTO;
import com.kenuiworks.frameworkbox.dto.MonthOfExperienceDTO;
import com.kenuiworks.frameworkbox.exception.FrameworkAlreadyRegisteredException;
import com.kenuiworks.frameworkbox.exception.FrameworkNotFoundException;
import com.kenuiworks.frameworkbox.model.Framework;
import com.kenuiworks.frameworkbox.service.FrameWorkService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api/v1/frameworks")
public class FrameworkController {

    protected final FrameWorkService service;

    public FrameworkController(FrameWorkService service) {
        this.service = service;
    }

    @Get
    public List<Framework> getFrameworks(){
        return service.findAll();
    }

    @Get("/{name}")
    public Framework findByName(String name) throws FrameworkNotFoundException {
        return service.findByName(name);
    }

    @Post
    public HttpResponse<FrameworkDTO> createFramework(@Body @Valid FrameworkDTO frameworkDTO) throws FrameworkAlreadyRegisteredException {
        FrameworkDTO framework = service.createFramework(frameworkDTO);
        return HttpResponse
                .created(framework)
                .headers(headers -> headers.location(location(framework.getName())));
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) throws FrameworkNotFoundException {
        service.deleteById(id);
        return HttpResponse.noContent();
    }

    @Patch("/{id}/increment/month/experience")
    public HttpResponse<FrameworkDTO> updateFramework(@PathVariable Long id , @Body MonthOfExperienceDTO monthOfExperienceDTO) throws FrameworkNotFoundException {
        service.incrementMonthOfExperience(id, monthOfExperienceDTO.getMonthsOfExperience());
        return HttpResponse.accepted();
    }



    protected URI location(String name) {
        return URI.create("/api/v1/frameworks/" + name);
    }

    protected URI location(Framework framework) {
        return location(framework.getName() );
    }

}
