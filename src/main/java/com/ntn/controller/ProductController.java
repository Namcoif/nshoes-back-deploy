package com.ntn.controller;

import com.ntn.dto.ProductDTO;
import com.ntn.dto.QueryProductDTO;
import com.ntn.entity.Product;
import com.ntn.service.IProductService;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ProductDTO> getListProduct() {
        List<Product> productList = productService.getListProducts();
        List<ProductDTO> productDTOList = modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {
        }.getType());

        return productDTOList;
    }

    @GetMapping("/paging")
//    public Page<ProductDTO> getListProductsPaging(Pageable pageable, @RequestParam(value = "search", required = false) String search) {
//    public ResponseEntity<?> getListProductsPaging(Pageable pageable, @RequestBody(required = false) QueryProductDTO queryProductDTO) {
    public ResponseEntity<?> getListProductsPaging(Pageable pageable, @PathParam(value = "search") QueryProductDTO queryProductDTO) {
        try {
            Page<Product> productPage = productService.getListProductsPaging(pageable, queryProductDTO);
            List<ProductDTO> listProductDTOs = modelMapper.map(productPage.getContent(), new TypeToken<List<ProductDTO>>() {
            }.getType());

            Page<ProductDTO> productDTOS = new PageImpl<>(listProductDTOs, pageable, productPage.getTotalElements());

            return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }


    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createProducts(@RequestBody ProductDTO productDTO) {
        try {
            Product product = modelMapper.map(productDTO, Product.class);

//            for(ProductDTO.sizeProduct pr : productDTO.getProductSize()){
//                product.addSize(pr);
//            }

            productService.createProducts(product);
            JSONObject message = new JSONObject();
            message.put("resultText", "Create Product Successfully!");
            message.put("status", 200);
            return ResponseEntity.status(HttpStatus.OK).body(message.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.toString());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        try {
            Product product = productService.getProductById(id);
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.toString());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateProduct(@RequestParam(name = "id") Integer id, @RequestBody ProductDTO productDTO) {
        try {
//            productDTO.setId(id);
            Product product = modelMapper.map(productDTO, Product.class);

            productService.updateProduct(product);
            JSONObject message = new JSONObject();
            message.put("resultText", "Update Product Successfully!");

            return ResponseEntity.status(HttpStatus.OK).body(message.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.toString());
        }
    }

    @GetMapping(value = "/selling-products")
    public List<ProductDTO> sellingProducts() {
        List<Product> productList = productService.sellingProducts(10);
        List<ProductDTO> productDTOList = modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {
        }.getType());

        return productDTOList;
    }

}
