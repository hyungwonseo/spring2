package dw.wholesale_company.service;

import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProductAll() { return productRepository.findAll(); }

    //제품의 재고가 50개 미만인 제품 정보 얻기
    public List<Product> getProductByInventoryUnder(int num) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(p->p.getInventory() < num)
                .collect(Collectors.toList());
    }

    //제품 중에서 ‘주스’ 제품에 대한 모든 정보를 검색하시오.
    public List<Product> getProductByProductName(String productName) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(product -> product.getProductName().contains(productName))
                    .collect(Collectors.toList());
    }

    //제품 단가가 5,000원 이상 10,000원 이하인 제품에는 무엇이 있는지 검색하시오.
    public List<Product> getProductByPriceRange(int lowPrice, int highPrice) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(product ->
                product.getUnitPrice() >= lowPrice && product.getUnitPrice() <= highPrice)
                .collect(Collectors.toList());
    }

    //제품 제품번호가 1, 2, 4, 7, 11, 20인 제품의 모든 정보를 보이시오.
    public List<Product> getProductByIdWithList(List<Long> idList) {
        List<Product> productList = productRepository.findAll();
//        List<Product> newProducts = new ArrayList<>();
//        for(int i=0; i<productList.size(); i++) {
//            for(int j=0; j< idList.size(); j++) {
//                if (productList.get(i).getProductId() == idList.get(j)) {
//                    newProducts.add(productList.get(i));
//                }
//            }
//        }
//        return newProducts;
        return productList.stream().filter(product -> idList.contains(product.getProductId()))
                .collect(Collectors.toList());
    }
}
