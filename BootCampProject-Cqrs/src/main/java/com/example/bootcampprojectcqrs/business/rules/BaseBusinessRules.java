package com.example.bootcampprojectcqrs.business.rules;

import com.example.bootcampprojectcqrs.core.exceptions.BusinessException;
import com.example.bootcampprojectcqrs.core.services.TranslationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public abstract class BaseBusinessRules {
    protected final TranslationService translationService;

    protected void throwBusinessException(String messageKey) {
        throw new BusinessException(translationService.getMessage(messageKey));
    }
} 