package guru.springframework.spring5webfluxrest.controllers;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {
    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> getVendorById(@PathVariable String id){
        return vendorRepository.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createVendor(@RequestBody Publisher<Vendor> vendorPublisher){
        return vendorRepository.saveAll(vendorPublisher).then();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> putVendor(@PathVariable String id, @RequestBody Vendor vendor){
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }
}
