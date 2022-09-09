package esdi.Services.controllers;

import esdi.Services.dtos.request.ProductDTORequest;
import esdi.Services.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    ResponseEntity<?> getAllOrders(){
        return new ResponseEntity<>(productService.findAllDTO(), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<?> newProduct(@RequestBody ProductDTORequest productDTORequest){
        return productService.createProduct(productDTORequest);
    }


}
