package esdi.Services.controllers;

import esdi.Services.mappers.ProductMapper;
import esdi.Services.repositories.ProductRepository;
import esdi.Services.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    ResponseEntity<?> getAllOrders(){
        return new ResponseEntity<>(productService.findAllDTO(), HttpStatus.OK);
    }


}
