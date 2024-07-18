package com.kanchan.Mvc.controller;

import com.kanchan.Mvc.dto.ClubDto;
import com.kanchan.Mvc.dto.EventDto;
import com.kanchan.Mvc.models.Event;
import com.kanchan.Mvc.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;
@Autowired
    public EventController(EventService eventService) {

    this.eventService = eventService;
    }

    @GetMapping("/events")
    public String eventList(Model model)
    {
        List<EventDto> events=eventService.findAllEvents();
        model.addAttribute("events",events);
        return "events-list";
    }
    @GetMapping("/events/{eventId}")
    public String ViewEvent(@PathVariable("eventId") long eventId,Model model)
    {
        EventDto eventDto=eventService.findByEventId(eventId);
        model.addAttribute("event",eventDto);
                return "events-detail";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId",clubId);
        model.addAttribute("event",event);
        return "events-create";
    }
    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") long eventId, Model model) {
        EventDto event = eventService.findByEventId(eventId);
        model.addAttribute("event", event);
        return "events-edit";
    }

    @PostMapping("events/{clubId}")
    public String createEvent(@PathVariable("clubId") long clubId, @ModelAttribute("event")EventDto eventDto,
                              BindingResult result,
                              Model model)

    {
        if(result.hasErrors()){
            model.addAttribute("event",eventDto);
            return "clubs-create";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/"+clubId;
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") long eventId,
                             @Valid @ModelAttribute("event") EventDto event,
                             BindingResult result, Model model)
    {
        if (result.hasErrors()){
            model.addAttribute("event",event);
            return "events-edit";
        }
        EventDto eventDto=eventService.findByEventId(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);
        return "redirect:/events";

    }
    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") long eventId){
    eventService.deleteEvent(eventId);
    return "redirect:/events";
    }
}
