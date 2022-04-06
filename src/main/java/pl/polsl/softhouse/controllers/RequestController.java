package pl.polsl.softhouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.polsl.softhouse.dto.request.RequestGetDto;
import pl.polsl.softhouse.dto.request.RequestPostDto;
import pl.polsl.softhouse.dto.request.RequestPutDto;
import pl.polsl.softhouse.services.RequestService;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public ResponseEntity<List<RequestGetDto>> getAllRequests() {
        return ResponseEntity.ok().body(requestService.getAllRequests());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RequestGetDto> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok().body(requestService.getRequestById(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequestById(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addRequest(@RequestBody RequestPostDto requestPostDto) {
        long id = requestService.addRequest(requestPostDto);
        URI createdUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(createdUri).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> updateRequest(@PathVariable Long id, @RequestBody RequestPutDto requestPutDto) {
        requestService.updateRequest(id, requestPutDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "accountManagerRequests/{userId}")
    public ResponseEntity<List<RequestGetDto>> getAllAccountManagerRequestsById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(requestService.getAllRequestsByUserId(userId));
    }
}
