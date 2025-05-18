package com.example.bootcampprojectcqrs.application.businessrules;

import com.example.bootcampprojectcqrs.application.exceptions.BusinessException;
import com.example.bootcampprojectcqrs.application.services.TranslationService;
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