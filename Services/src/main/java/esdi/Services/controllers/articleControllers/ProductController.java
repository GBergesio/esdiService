package esdi.Services.controllers.articleControllers;
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

//LO REEMPLACE CON EL DE ABAJO
//    @GetMapping("/pn/{pn}")
//    ResponseEntity<?> getProductByPN(@PathVariable String pn){
//
//        ProductDTO product = productService.getProductByPN(pn);
//
//        if (product == null){
//            return new ResponseEntity<>("No se encontró producto con el codigo ingresado",HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(productService.getProductByPN(pn), HttpStatus.OK);
//    }

    @GetMapping("/{productNumber}")
    ResponseEntity<?> getProductBy(@PathVariable String productNumber){
        return productService.findPN(productNumber);
    }

    @PostMapping()
    ResponseEntity<?> newProduct(@RequestBody ProductDTORequest productDTORequest){
        return productService.createProduct(productDTORequest);
    }



}
