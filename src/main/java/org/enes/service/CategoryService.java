package org.enes.service;

import org.enes.dto.request.CategoryRequestDto;
import org.enes.entity.Category;
import org.enes.exception.ErrorType;
import org.enes.exception.HolidayException;
import org.enes.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //save
    public Category save(CategoryRequestDto categoryRequestDto){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryRequestDto.getParentId());
        if(optionalCategory.isPresent()){
            throw new HolidayException(ErrorType.CATEGORY_ALREADY_EXISTS);
        }
        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .parentId(categoryRequestDto.getParentId())
                .build();
        return categoryRepository.save(category);
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }


}
