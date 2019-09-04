package com.llzw.apigate.service;

import com.llzw.apigate.message.error.RestApiException;
import com.llzw.apigate.message.error.RestEntityNotFoundException;
import com.llzw.apigate.persistence.dao.ProductRepository;
import com.llzw.apigate.persistence.entity.Product;
import com.llzw.apigate.persistence.entity.User;
import com.llzw.apigate.util.Utils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

  @Setter(onMethod_ = @Autowired)
  private ProductRepository productRepository;

  @Value("${spring.data.rest.base-path}")
  private String apiBasePath;

  public Product create(ProductCreateDto dto, User seller) {
    String introduction = dto.getIntroduction();
    Product product = new Product();
    product.setValid(true);
    BeanUtils.copyProperties(dto, product, Utils.getNullPropertyNames(dto));
    return productRepository.save(product);
  }

  public Product update(ProductCreateDto dto, Long id, User seller) throws RestApiException {
    Product product = productRepository.findByIdAndSeller(id, seller)
        .orElseThrow(
            () -> new RestEntityNotFoundException(
                String
                    .format("Product <%s> does not exist or you do not have access to this entity",
                        id)
            )
        );
    BeanUtils.copyProperties(dto, product, Utils.getNullPropertyNames(dto));
    return productRepository.save(product);
  }


  public Optional<Product> findById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    return product;
  }


  public List<Product> search(Pageable pageable, ProductSearchDto dto) {
    String nameQueryString = dto.getName();
    String introductionQueryString = dto.getName();
    String global = dto.getGlobal();
    List<Product> result;
    if (global != null) {
      result = productRepository.searchByNameOrIntroductionWithCustomQuery(global, pageable);
      if (!dto.isValid()) {
        result = result.stream().filter(Product::isValid).collect(Collectors.toList());
      }
    } else if (nameQueryString != null || introductionQueryString != null) {
      Product example = new Product();
      BeanUtils.copyProperties(dto, example, Utils.getNullPropertyNames(dto));
      result = productRepository.searchByExample(example, pageable);
    } else {
      result = productRepository.findAllByValid(dto.isValid(), pageable);
    }
    return result;
  }


  public Product save(Product product) {
    return productRepository.save(product);
  }
}
